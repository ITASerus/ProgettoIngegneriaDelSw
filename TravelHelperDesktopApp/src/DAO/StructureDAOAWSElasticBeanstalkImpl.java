/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author ernestodecrecchio
 */
public class StructureDAOAWSElasticBeanstalkImpl implements StructureDAO {
    private static final String GET_NUM_STRUCTURES = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getNum";
    private static final String CREATE_STRUCTURE = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/create";
    private static final String DELETE_STRUCTURE = "http://localhost:5000/structures/id=";
    private static final String UPDATE_STRUCTURE = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id=";
    private static final String GET_ALL_STRUCTURES = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getAll";
    private static final String GET_BY_STRID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id=";
    private static final String GET_BY_STR_NAME= "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/name=";
    private static final String GET_BY_REV_STRID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id=3/getReviews";
    private static final String GET_INFO = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getInfo";
    private static final String GET_BY_FILTER ="http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/search/";
    
    @Override
    public String getNum() {
        String num = "";
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestNumOfStructures = HttpRequest.newBuilder()
                .uri(URI.create(GET_NUM_STRUCTURES))
                .build();
        
        try{
            HttpResponse<String> responseNumOfStructures = client.send(requestNumOfStructures, HttpResponse.BodyHandlers.ofString());
           
            num = responseNumOfStructures.body();     
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        
        return num;
    }
    
    @Override
    public JsonArray getInfo() {
       JsonArray results = null;
       
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest requestStructuresDetail = HttpRequest.newBuilder().uri(URI.create(GET_INFO)).build();
       
       try{
           HttpResponse<String> responseStructuresDetail = client.send(requestStructuresDetail, HttpResponse.BodyHandlers.ofString());
            
           Gson gson = new Gson();
            
           results = gson.fromJson(responseStructuresDetail.body(), JsonArray.class); // Convert json text to JsonArray      
          
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } 
       
       return results;
    }
    
    @Override
    public HttpResponse<String> newStructure(String body) {
        final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build(); //Per la connessione del client al server

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(CREATE_STRUCTURE))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        
        HttpResponse<String> response = null;
        
        try {
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            //System.err.print(e);
        }catch (InterruptedException i) {
            //System.err.print(i);
        }
        finally {
            return response;
        }
  
    }

    @Override
    public JsonArray getByFilter(String name, String place, String contacts, String category, String webSite, Double lowerPrice, Double upperPrice, String avgPoints) {
       JsonArray results = null;
            
       String endpointURL = GET_BY_FILTER;  
       endpointURL = endpointURL + (name == null ? "name=null" : "name="+name);
       endpointURL = endpointURL + (place == null ? "&place=null" : "&place="+place);
       endpointURL = endpointURL + (contacts == null ? "&contacts=null" : "&contacts="+contacts);
       endpointURL = endpointURL + (category == null ? "&category=null" : "&category="+category);
       endpointURL = endpointURL + (webSite == null ? "&webSite=null" : "&webSite="+webSite);
       endpointURL = endpointURL + (lowerPrice == null ? "&lowerPrice=-1" : "&lowerPrice="+lowerPrice);
       endpointURL = endpointURL + (upperPrice == null ? "&upperPrice=-1" : "&upperPrice="+upperPrice);
       endpointURL = endpointURL + (avgPoints == null ? "&avgPoints=null" : "&avgPoints="+avgPoints);
       
       System.out.println(endpointURL);
       
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest requestStructuresDetail = HttpRequest.newBuilder().uri(URI.create(endpointURL)).build();
       
       try{
           HttpResponse<String> responseStructuresDetail = client.send(requestStructuresDetail, HttpResponse.BodyHandlers.ofString());
            
           Gson gson = new Gson();
            
           results = gson.fromJson(responseStructuresDetail.body(), JsonArray.class); // Convert json text to JsonArray      
          
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } 
       
       return results;
    }

}
