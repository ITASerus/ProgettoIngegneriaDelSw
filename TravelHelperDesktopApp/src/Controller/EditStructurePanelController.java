/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package Controller;

// Data Access Object
import DAO.DAOFactory;
import DAO.StructureDAO;

// Map Management
import Helper.JXMapViewer.FancyWaypointRenderer;
import Helper.JXMapViewer.MyWaypoint;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

// Model
import Model.Structure;

// View
import View.MainFrameView;

//Java & Swing
import java.awt.Color;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author ernestodecrecchio
 */
public class EditStructurePanelController {
    private StructureDAO structureDAO;
    
    public EditStructurePanelController() {
        structureDAO = DAOFactory.getStructureDAO("AWSElasticBeanstalk");
    }
    
    public void setInfoPanel(MainFrameView parent, Structure structure) {
        parent.setInfoStructurePanel(structure);
    }
    
    public boolean editStructure(Long structureID, String name, String place, String category, String price, String webSite, String contacts, String description) {
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
                
        HttpResponse<String> response = structureDAO.editStructure(structureID, json);   
        
        if(response.statusCode() == 200) {
            return true;
        } else {
            return false;
        }    
    }
    
    public boolean deleteStructure(Structure structure) {
        HttpResponse<String> response = structureDAO.deleteStructure(structure.getId());
        
        if(response.statusCode() == 200) {
            return true;
        } else {
            return false;
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
}
