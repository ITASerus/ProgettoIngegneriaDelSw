/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOFactory;
import DAO.StructureDAO;
import Model.Structure;
import View.MainFrameView;
import java.net.http.HttpResponse;

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
}
