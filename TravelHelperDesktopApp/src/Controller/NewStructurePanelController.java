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
    
    public void setInfoStructurePanel(MainFrameView parent, Structure structure) { //Changes the panel (View) showing the structureInfo or the summary of all structures
        if (structure.getId() != null) {         
            System.out.println("id " + structure.getId());
            parent.setInfoStructurePanel(structure); //Shows the infos of the  structure just added
        } else {
            parent.setInfoStructurePanel(null); //If the structure doesn't found, shows the summary of the structures (NEVER WILL HAPPEN)
        }        
    }
}
