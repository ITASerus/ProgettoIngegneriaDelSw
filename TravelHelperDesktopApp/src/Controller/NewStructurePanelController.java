/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOFactory;
import DAO.StructureDAO;
import Model.Structure;
import com.google.gson.Gson;
import java.net.http.HttpResponse;
import View.MainFrameView;
import javax.swing.event.MouseInputListener;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
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
import java.awt.Color;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.transfer.TransferManager;

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
                
        Gson gson = new Gson();
        Structure structure = gson.fromJson(response.body(), Structure.class); // Convert json text to Structure
        
        //---- S3 Management ----
        
        if(!imagePath.isBlank()) {
            final AWSCredentials credentials = new BasicAWSCredentials(
              "AKIA573JGNGLCBRFBHGV", 
              "547iI8dzMOR/wnCLUh36BFjWAqj+nCJVdujELqN+"
            );
        
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
              structure.getId() + "/nuovo.jpg",
              new File(imagePath)
            );
            
            ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix("prova/").withDelimiter("/");
            ListObjectsV2Result listing = s3client.listObjectsV2(req);
            
            /*System.out.println("\ncartelle:");
            for (String commonPrefix : listing.getCommonPrefixes()) {
                System.out.println(commonPrefix);
            }*/
            
            
            System.out.println("Chiavi:");
            for (S3ObjectSummary summary: listing.getObjectSummaries()) {
                System.out.println(summary.getKey());
            }
            System.out.println("Links:");
            for (S3ObjectSummary summary: listing.getObjectSummaries()) {
                System.out.println("https://travelappimages.s3.eu-central-1.amazonaws.com/" + summary.getKey());
            }
            
            /*//listing objects     
            ObjectListing objectListing = awsService.listObjects(bucketName);
            for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
                System.out.println(os.getKey());
            }*/
        }
        
        return structure;
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
        new MyWaypoint("Si trova quì!", Color.YELLOW, position)));
                
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
