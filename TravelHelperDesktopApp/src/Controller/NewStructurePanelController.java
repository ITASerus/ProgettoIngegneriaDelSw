/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package Controller;

// Data Access Object
import DAO.DAOFactory;
import DAO.StructureDAO;

// Model
import Model.Structure;

// Goolge Gson
import com.google.gson.Gson;

// View
import View.MainFrameView;

// Map Management
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.viewer.WaypointPainter;
import Helper.JXMapViewer.FancyWaypointRenderer;
import Helper.JXMapViewer.MyWaypoint;

// Java & Swing
import java.awt.Color;
import java.net.http.HttpResponse;
import javax.swing.event.MouseInputListener;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.io.File;

// Amazon AWS S3
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import Helper.AWS.AWSS3Service;

/**
 *
 * @author ernestodecrecchio
 */
public class NewStructurePanelController {
    private static  StructureDAO structureDAO;

    
    public NewStructurePanelController() {
        structureDAO = DAOFactory.getStructureDAO("AWSElasticBeanstalk");
    }
    
    public Structure createNewStructure(String name, String place, String category, String price, String webSite, String contacts, String description, String imagePath) {
        // json formatted data
        String json = new StringBuilder()
                .append("{")
                
                .append("\"name\":")
                .append("\"" + name + "\"") 
                
                .append(",\"place\":")
                .append(place.isBlank() ? "null" : "\"" + place + "\"")
                
                .append(",\"category\":")
                .append(category.equals("---") ? "null" : "\"" + category + "\"")
                
                .append(",\"price\":")
                .append(price.isBlank() ? "null" : "\"" + price + "\"")
               
                .append(",\"webSite\":")
                .append(webSite.isBlank() ? "null" : "\"" + webSite + "\"")
                
                .append(",\"contacts\":")
                .append(contacts.isBlank() ? "null" : "\"" + contacts + "\"")
                
                .append(",\"description\":")
                .append(description.isBlank() ? "null" : "\"" + description + "\"")              
                                
                .append("}").toString();
                
        HttpResponse<String> response = structureDAO.newStructure(json);  
           
        if (response.statusCode() == 200) {
            
            // Image management
            Gson gson = new Gson();
            Structure structure = gson.fromJson(response.body(), Structure.class); // Convert json text to Structure
        
            if(!imagePath.isBlank()) {
                final AWSCredentials credentials = new BasicAWSCredentials("CENSURATO", "CENSURATO");
        
                //set-up the client
                AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
        
                AWSS3Service awsService = new AWSS3Service(s3client);
 
                //setting bucket of the app
                String bucketName = "travelappimages";
 
                //uploading object
                awsService.putObject(
                    bucketName, 
                    "StructuresImage/" + structure.getId() + "/" + structure.getId() + ".jpg",
                    new File(imagePath)
                );
            
                json = new StringBuilder()
                    .append("{")
                    
                    .append("\"name\":")
                    .append("\"" + name + "\"") 
                    
                    .append(",\"image\":")
                    .append("\"" + "https://travelappimages.s3.eu-central-1.amazonaws.com/StructuresImage/" + structure.getId() + "/" + structure.getId() + ".jpg" + "\"") 
                    
                    .append("}").toString();
                    
                structureDAO.editStructure(structure.getId(), json);
                structure.setImage("https://travelappimages.s3.eu-central-1.amazonaws.com/StructuresImage/" + structure.getId()  + "/" + structure.getId() + ".jpg" );
            }
            
            return structure;
        }
        else {    
            System.out.print(response.body());           
            return null; 
        }
    }
    
    public JXMapViewer getMapOfPlace(String place) {
        HashMap<String,Double> coordinates = structureDAO.getCoordinates(place);
        
        JXMapViewer mapViewer = new JXMapViewer();

        // Add interactions
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);

        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        // Create a TileFactoryInfo for OpenStreetMap
        //TileFactoryInfo info = new OSMTileFactoryInfo();
        //DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        
        TileFactoryInfo veInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        DefaultTileFactory tileFactory = new DefaultTileFactory(veInfo);
        
        mapViewer.setTileFactory(tileFactory);

        // Use 8 threads in parallel to load the tiles
        tileFactory.setThreadPoolSize(8);

        // Set the focus
        GeoPosition position = new GeoPosition(coordinates.get("lat"), coordinates.get("lng"));
       
        // Create waypoints from the geo-positions
        Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>(Arrays.asList(
        new MyWaypoint("", Color.YELLOW, position)));
                
        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
        waypointPainter.setWaypoints(waypoints);
        waypointPainter.setRenderer(new FancyWaypointRenderer());

        mapViewer.setOverlayPainter(waypointPainter);
        
        mapViewer.setZoom(3);
        mapViewer.setAddressLocation(position);
        
        mapViewer.setVisible(true);
        
        return mapViewer;
    }
   
    
    
    public void setInfoStructurePanel(MainFrameView parent, Structure structure) { //Changes the panel (View) showing the structureInfo or the summary of all structures
        if (structure.getId() != null) {         
            //System.out.println("id " + structure.getId());
            parent.setInfoStructurePanel(structure); //Shows the infos of the  structure just added
        } else {
            parent.setInfoStructurePanel(null); //If the structure doesn't found, shows the summary of the structures (NEVER WILL HAPPEN)
        }        
    }
}
