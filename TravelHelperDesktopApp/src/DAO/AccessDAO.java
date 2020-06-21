/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.google.gson.JsonObject;
import java.net.http.HttpResponse;

/**
 *
 * @author ernestodecrecchio
 */
public interface AccessDAO {
    public HttpResponse<String> signIn(String firstName, String lastName, String email, String username, String password);
    
    public String logIn(String usernameOrEmail, String password);
}
