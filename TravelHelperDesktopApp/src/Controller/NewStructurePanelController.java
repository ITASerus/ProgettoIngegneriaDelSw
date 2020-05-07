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
import com.google.gson.JsonArray;
import javax.swing.event.MouseInputListener;
import java.util.HashMap;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

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
import Helper.JXMapViewer.MultiplyComposite;
import Helper.JXMapViewer.MyWaypoint;
import java.awt.Color;

/**
 *
 * @author ernestodecrecchio
 */
public class NewStructurePanelController {
    private StructureDAO structureDAO;
    
    public NewStructurePanelController() {
        structureDAO = DAOFactory.getStructureDAO("AWSElasticBeanstalk");
    }
    
    public Structure createNewStructure(String name, String place, String category, String price, String webSite, String contacts, String description) {
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
            System.out.println("id " + structure.getId());
            parent.setInfoStructurePanel(structure); //Shows the infos of the  structure just added
        } else {
            parent.setInfoStructurePanel(null); //If the structure doesn't found, shows the summary of the structures (NEVER WILL HAPPEN)
        }        
    }
}
