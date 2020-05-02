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

/**
 *
 * @author ernestodecrecchio
 */
public class NewStructurePanelController {
    private StructureDAO structureDAO;
    
    public NewStructurePanelController() {
        structureDAO = DAOFactory.getStructureDAO("AWSElasticBeanstalk");
    }
    
    public HttpResponse<String> sendPOST(String name, String place, String category, String price, String webSite, String contacts, String description) {
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
                
                System.out.println(json);
                
                System.out.println(response);
                
                return response;
    }
    
    public void changePanel(MainFrameView parent, String responseBody) { //Changes the panel (View) showing the structureInfo or the summary of all structures
        Gson gson = new Gson();
        
        Structure structure = gson.fromJson(responseBody, Structure.class);
        if (structure.getId() != null) {         
            System.out.println("id " + structure.getId());
            parent.setInfoStructurePanel(structure); //Shows the infos of the  structure just added
        } else {
            parent.setInfoStructurePanel(null); //If the structure doesn't found, shows the summary of the structures (NEVER WILL HAPPEN)
        }        
    }
}
