/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package DAO;

// Java
import java.net.http.HttpResponse;

/**
 *
 * @author ernestodecrecchio
 */
public interface AccessDAO {
    public HttpResponse<String> signUp(String firstName, String lastName, String email, String username, String password);
    
    public String logIn(String usernameOrEmail, String password);
}
