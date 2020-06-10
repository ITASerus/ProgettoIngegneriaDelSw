/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AccessDAO;
import DAO.DAOFactory;
import DAO.UserDAO;
import Model.User;
import Singleton.LoggedUserSingleton;
import java.util.Map;

/**
 *
 * @author ernestodecrecchio
 */
public class LogInDialogController {
    private static AccessDAO accessDAO;
    private static UserDAO userDAO;
    
    public LogInDialogController() {
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
