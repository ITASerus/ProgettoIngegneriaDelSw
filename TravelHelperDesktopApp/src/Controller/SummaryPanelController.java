/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package Controller;

//Data Access Object
import DAO.DAOFactory;
import DAO.ReviewDAO;
import DAO.StructureDAO;
import DAO.UserDAO;

//Google GSON
import com.google.gson.JsonArray;

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
    
    public Integer getStructureNumber() {
        return structure.getNum();
    }
    
    public Integer getUserNumber() {
        return user.getNum();
    }
    
    public Integer getReviewNumber() {
        return review.getNum();
    }
    
    public JsonArray getInfo() {
        return structure.getInfo();
    }
}
