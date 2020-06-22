/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package Controller;

// Data Access Object
import DAO.AccessDAO;
import DAO.DAOFactory;
import DAO.UserDAO;

// Model
import Model.User;

// Singleton
import Singleton.LoggedUserSingleton;

// Map Management
import java.util.Map;

/**
 *
 * @author ernestodecrecchio
 */
public class AccessPanelController {
    private static AccessDAO accessDAO;
    private static UserDAO userDAO;
    
    public AccessPanelController() {
        accessDAO = DAOFactory.getAccessDAO("AWSElasticBeanstalk");
        userDAO = DAOFactory.getUserDAO("AWSElasticBeanstalk");
    }
    
    public int logInUser(String usernameOrEmail, String password) {     
        String response = accessDAO.logIn(usernameOrEmail, password);
        
        if (response != null) {
            User loggedUser = userDAO.getByUsernameOrEmail(usernameOrEmail);
            
            for(Object curr : loggedUser.getRoles()) {
                Map<Long,String> conv = (Map<Long, String>)curr;
                
                if(conv.containsValue("ROLE_ADMIN")) {
                    
                    //Create singleton of logged user
                    LoggedUserSingleton user = LoggedUserSingleton.getIstance();
                    user.setLoggedUser(loggedUser, response);
                   
                    return 1;
                } 
            }
            
            return 0; // User logged but it hasn't admin priviledges      
        } else { // Wrong data
            return -1;
        }
    }
}
