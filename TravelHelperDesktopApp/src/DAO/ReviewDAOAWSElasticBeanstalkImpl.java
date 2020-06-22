/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package DAO;

// Java
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author ernestodecrecchio
 */
public class ReviewDAOAWSElasticBeanstalkImpl implements ReviewDAO {
    private static final String GET_NUM_REVIEWS = "http://Travelapplication-dev.eba-ixtj8ubn.eu-central-1.elasticbeanstalk.com/reviews/getNum";

    public Integer getNum() {
        Integer num = -1;
        
        HttpClient client = HttpClient.newHttpClient(); // SHUTDOWN?
        HttpRequest requestNumOfStructures = HttpRequest.newBuilder().uri(URI.create(GET_NUM_REVIEWS)).build();
        
        try{
            HttpResponse<String> responseNumOfReviews = client.send(requestNumOfStructures, HttpResponse.BodyHandlers.ofString());

            num = Integer.parseInt(responseNumOfReviews.body());
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException i) {
            i.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        return num;
    }
}
