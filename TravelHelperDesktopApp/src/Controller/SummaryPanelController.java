/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

//Data Access Object
import DAO.DAOFactory;
import DAO.ReviewDAO;
import DAO.StructureDAO;
import DAO.UserDAO;

//Google GSON
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javax.swing.JPanel;

/**
 *
 * @author ernestodecrecchio
 */
public class SummaryPanelController {
    private StructureDAO structure;
    private ReviewDAO review;
    private UserDAO user;
    
    public SummaryPanelController() {
        structure = DAOFactory.getStructureDAO("AWSElasticBeanstalk");
        review = DAOFactory.getReviewDAO("AWSElasticBeanstalk");
        user = DAOFactory.getUserDAO("AWSElasticBeanstalk");
    }
    
    public String getStructureNumber() {
        return structure.getNum();
    }
    
    public String getUserNumber() {
        return user.getNum();
    }
    
    public String getReviewNumber() {
        return review.getNum();
    }
    
    public JsonArray getInfo() {
        return structure.getInfo();
    }
}
