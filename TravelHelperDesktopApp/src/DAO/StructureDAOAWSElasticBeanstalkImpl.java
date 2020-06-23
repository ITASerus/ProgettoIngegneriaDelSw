/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package DAO;

// Google Gson
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

// Java
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

// Model
import Model.Structure;

// Apache
import org.apache.http.HttpHeaders;

// Singleton
import Singleton.LoggedUserSingleton;

/**
 *
 * @author ernestodecrecchio
 */
public class StructureDAOAWSElasticBeanstalkImpl implements StructureDAO {
    private static final String GET_NUM_STRUCTURES = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getNum";
    private static final String CREATE_STRUCTURE = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/create";
    private static final String DELETE_STRUCTURE = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id=";
    private static final String UPDATE_STRUCTURE = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id=";
    private static final String GET_ALL_STRUCTURES = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getAll";
    private static final String GET_BY_STRID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id=";
    private static final String GET_BY_STR_NAME= "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/name=";
    private static final String GET_BY_REV_STRID = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/id=3/getReviews";
    private static final String GET_INFO = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/getInfo";
    private static final String GET_BY_FILTER ="http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/structures/search/";
    
    @Override
    public Integer getNum() {
        Integer num = -1;
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestNumOfStructures = HttpRequest.newBuilder().uri(URI.create(GET_NUM_STRUCTURES)).build();
        
        try{
            HttpResponse<String> responseNumOfStructures = client.send(requestNumOfStructures, HttpResponse.BodyHandlers.ofString());
           
            num = Integer.parseInt(responseNumOfStructures.body());     
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        return num;
    }
    
    @Override
    public Structure getByID(Integer id) {       
        Structure structure = new Structure();
        
        String ENDPOINT_URL = GET_BY_STRID + id;
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestStructure = HttpRequest.newBuilder().uri(URI.create(ENDPOINT_URL)).build();
        
        try{
            HttpResponse<String> responseStructure = client.send(requestStructure, HttpResponse.BodyHandlers.ofString());
           
            Gson gson = new Gson();
            structure = gson.fromJson(responseStructure.body(), Structure.class); // Convert json text to Structure class        
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        }

        return structure;
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
    public JsonArray getByFilter(String name, String place, String category, String contacts, String webSite, Integer lowerPrice, Integer upperPrice, String avgPoints) {
        JsonArray results = null;
            
        String ENDPOINT_URL = GET_BY_FILTER + (name == null ? "name=null" : "name="+name)
                                            + (place == null ? "&place=null" : "&place="+place)
                                            + (category == null ? "&category=null" : "&category="+category)
                                            + (contacts == null ? "&contacts=null" : "&contacts="+contacts)                                          
                                            + (webSite == null ? "&webSite=null" : "&webSite="+webSite)
                                            + (lowerPrice == null ? "&lowerPrice=-1" : "&lowerPrice="+lowerPrice)
                                            + (upperPrice == null ? "&upperPrice=-1" : "&upperPrice="+upperPrice)
                                            + (avgPoints == null ? "&avgPoints=null" : "&avgPoints="+avgPoints);
        
        ENDPOINT_URL = ENDPOINT_URL.replace(" ", "%20");
        System.out.println(ENDPOINT_URL);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestStructuresDetail = HttpRequest.newBuilder().uri(URI.create(ENDPOINT_URL)).build();
       
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

        //System.out.print(LoggedUserSingleton.getIstance().getLoggedUser().getToken());
        
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(CREATE_STRUCTURE))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header 
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + LoggedUserSingleton.getIstance().getLoggedUser().getToken())
                .build();
        
        HttpResponse<String> response = null;
        
        System.out.print(request);
        
        try {
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException i) {
            i.printStackTrace();
        } finally {
            return response;
        }
    }
  
    @Override
    public HttpResponse<String> editStructure(Long structureID, String body) {
        String ENDPOINT_URL = UPDATE_STRUCTURE + structureID;
         
        final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build(); //Per la connessione del client al server

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT_URL))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + LoggedUserSingleton.getIstance().getLoggedUser().getToken())
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        
        try {
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException i) {
            i.printStackTrace();
        } finally {
            return response;
        }
    }
    
    @Override
    public HttpResponse<String> deleteStructure(Long id) {
        String ENDPOINT_URL = DELETE_STRUCTURE + id;
        
        System.out.println(ENDPOINT_URL);
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestDeleteStructure = HttpRequest.newBuilder().uri(URI.create(ENDPOINT_URL))
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + LoggedUserSingleton.getIstance().getLoggedUser().getToken())
                .DELETE().build();
        HttpResponse<String> response = null;
        
        try{
            response = client.send(requestDeleteStructure, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } finally {
            return response;
        }
    }
    
    @Override
    public HashMap<String,Double> getCoordinates(String place) {
        HashMap<String, Double> coordinates = new HashMap();
        
        String key = "QLRWkvF8XwkjXISHbeArUig6dSOw4dav";
        String placeConv = place.replace(" ", "%20");
        
        String URL = "https://www.mapquestapi.com/geocoding/v1/address?key=" + key + "&inFormat=kvp&outFormat=json&location=" + placeConv;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestInfoPlace = HttpRequest.newBuilder().uri(URI.create(URL)).build();
       
        try{
           HttpResponse<String> response = client.send(requestInfoPlace, HttpResponse.BodyHandlers.ofString());
            
           System.out.println(response.body());
           
           Gson gson = new Gson();
           JsonObject r = gson.fromJson(response.body(), JsonObject.class); // Convert json text to JsonArray      

           JsonArray results = r.get("results").getAsJsonArray();
           
           JsonObject zero = results.get(0).getAsJsonObject();
           JsonArray locations = zero.get("locations").getAsJsonArray();
           JsonObject uno = locations.get(0).getAsJsonObject();
           JsonObject latlng = uno.getAsJsonObject("latLng");
     
           coordinates = new Gson().fromJson(latlng.toString(), HashMap.class);

           return coordinates;
         

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } finally {
            return coordinates;
        }
    }
    
}
