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

/**
 *
 * @author ernestodecrecchio
 */
public class UserDAOAWSElasticBeanstalkImpl implements UserDAO {
    private static final String GET_NUM_USERS = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/users/getNum";

    public String getNum() {
        String num = "";
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestNumOfStructures = HttpRequest.newBuilder().uri(URI.create(GET_NUM_USERS)).build();
        
        try{
            HttpResponse<String> responseNumOfUsers = client.send(requestNumOfStructures, HttpResponse.BodyHandlers.ofString());

            num = responseNumOfUsers.body();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        
        return num;
    }
}
