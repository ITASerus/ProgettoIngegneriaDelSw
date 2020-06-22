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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author ernestodecrecchio
 */
public class AccessDAOAWSElasticBeanstalkImpl implements AccessDAO {
    private static final String SIGN_IN = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/api/auth/signin";
    private static final String SIGN_UP = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/api/auth/signup";
    
    @Override
    public HttpResponse<String> signIn(String firstName, String lastName, String email, String username, String password) {
        String body = new StringBuilder()
                .append("{")
                
                .append("\"firstName\": ")
                .append("\"" + firstName + "\"") 
                
                .append(",\"lastName\":")
                .append("\"" + lastName + "\"") 
                
                .append(",\"gender\": ")
                .append("\"" + "N" + "\"") 
                
                .append(",\"image\": null")
                
                .append(",\"username\": ")
                .append("\"" + username + "\"") 
                
                .append(",\"email\": ")
                .append("\"" + email + "\"") 
                
                .append(",\"password\": ")
                .append("\"" + password + "\"") 
                
                .append(",\"role\": ")
                .append("\"" + "ROLE_ADMIN" + "\"") 
                
                .append("}").toString();
        
        System.out.print(body);
        
        final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build(); //Per la connessione del client al server

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(SIGN_UP))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = null;
            
        try {
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());       
        System.out.print(response.statusCode());
        System.out.print(response.body());
        
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException i) {
            i.printStackTrace();
        } finally {
            /*Gson gson = new Gson();
            JsonObject object = gson.fromJson(response.body(), JsonObject.class); // Convert json text to JsonArray      
                
            return object;*/
            
            return response;
        }
    }
    
    @Override
    public String logIn(String usernameOrEmail, String password) {
        String body = new StringBuilder()
                .append("{")
                
                .append("\"usernameOrEmail\":")
                .append("\"" + usernameOrEmail + "\"") 
                
                .append(",\"password\":")
                .append("\"" + password + "\"")
               
                .append("}").toString();
        
        final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build(); //Per la connessione del client al server

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(SIGN_IN))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = null;
        
        try {
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException i) {
            i.printStackTrace();
        } finally {
            if (response.statusCode() == 200) { // Accesso effettuato con successo      
                
                Gson gson = new Gson();
                JsonObject object = gson.fromJson(response.body(), JsonObject.class); // Convert json text to JsonArray      
           
                return object.get("accessToken").getAsString();    
            } else { //Credenziali errate
                return null;
            }            
        }
    }
}
