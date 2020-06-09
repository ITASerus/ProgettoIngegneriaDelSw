/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import Model.User;
import com.google.gson.Gson;

/**
 *
 * @author ernestodecrecchio
 */
public class UserDAOAWSElasticBeanstalkImpl implements UserDAO {
    private static final String GET_NUM_USERS = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/users/getNum";
    private static final String GET_BY_USERNAME_EMAIL = "http://localhost:5000/users/usernameOrEmail/";

    public Integer getNum() {
        Integer num = -1;
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestNumOfStructures = HttpRequest.newBuilder().uri(URI.create(GET_NUM_USERS)).build();
        
        try{
            HttpResponse<String> responseNumOfUsers = client.send(requestNumOfStructures, HttpResponse.BodyHandlers.ofString());

            num = Integer.parseInt(responseNumOfUsers.body());
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        return num;
    }
    
    public User getByUsernameOrEmail(String usernameOrEmail) {
        User user = new User();
        
        String ENDPOINT_URL = GET_BY_USERNAME_EMAIL  + "username=" + usernameOrEmail + "&email=" + usernameOrEmail;
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestUser = HttpRequest.newBuilder().uri(URI.create(ENDPOINT_URL)).build();
        
        try{
            HttpResponse<String> responseUser = client.send(requestUser, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            user = gson.fromJson(responseUser.body(), User.class); // Convert json text to User      
    
            return user;         
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
