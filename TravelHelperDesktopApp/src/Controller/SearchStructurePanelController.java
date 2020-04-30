/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Data Access Object
import DAO.DAOFactory;
import DAO.StructureDAO;

//Google GSON
import com.google.gson.JsonArray;

/**
 *
 * @author ernestodecrecchio
 */
public class SearchStructurePanelController {
    private StructureDAO structure;
    
    public SearchStructurePanelController() {
        structure = DAOFactory.getStructureDAO("AWSElasticBeanstalk");
    }
    
    public JsonArray getByFilter(String name, String place, String contacts, String category, String webSite, Double lowerPrice, Double upperPrice, String avgPoints) {
        return structure.getByFilter(name, place, contacts, category, webSite, lowerPrice, upperPrice, avgPoints);
    }
}
