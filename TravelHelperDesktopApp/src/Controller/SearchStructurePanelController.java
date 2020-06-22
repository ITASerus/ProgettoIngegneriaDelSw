/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package Controller;

//Data Access Object
import DAO.DAOFactory;
import DAO.StructureDAO;

//Google GSON
import com.google.gson.JsonArray;

import Model.Structure;

/**
 *
 * @author ernestodecrecchio
 */
public class SearchStructurePanelController {
    private StructureDAO structure;
    
    public SearchStructurePanelController() {
        structure = DAOFactory.getStructureDAO("AWSElasticBeanstalk");
    }
    
    public JsonArray getByFilter(String name, String place, String contacts, String category, String webSite, Integer lowerPrice, Integer upperPrice, String avgPoints) {
        return structure.getByFilter(name, place, contacts, category, webSite, lowerPrice, upperPrice, avgPoints);
    }
    
    public Structure getSelectedStructure(Integer id) {
        return structure.getByID(id);
    }
}
