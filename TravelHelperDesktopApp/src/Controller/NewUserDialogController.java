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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 *
 * @author ernestodecrecchio
 */
public class NewUserDialogController {
    private static AccessDAO accessDAO;
    private static UserDAO userDAO;
    
    public NewUserDialogController() {
        accessDAO = DAOFactory.getAccessDAO("AWSElasticBeanstalk");
        userDAO = DAOFactory.getUserDAO("AWSElasticBeanstalk");   
    }
    
    public int createNewUser(String firstName, String lastName, String email, String username, String password) {     
        HttpResponse<String> response = accessDAO.signIn(firstName, lastName, email, username, password);
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(response.body(), JsonObject.class); // Convert json text to JsonArray               
        
        if(response.statusCode() == 400 ) {
            if (object.get("message").getAsString().equals("Username is already taken!")) {
                return 1;
            } else if (object.get("message").getAsString().equals("Username is already taken!")) {
                return 2; //Email Address already in use!
            } else {
                return 3;
            }
        } else { // == 201
            return 0;
        }
    }
}
