/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.net.http.HttpResponse;

/**
 *
 * @author ernestodecrecchio
 */

/**
 * L'interfaccia DAO per le diverse implementazioni di StructureDAO. Definisce le operazioni CRUD.
 */
public interface StructureDAO {
    
    public String getNum();
    
    public JsonArray getInfo();
    
    public HttpResponse<String> newStructure(String body);
    
    public JsonArray getByFilter(String name, String place, String contacts, String category, String webSite, Double lowerPrice, Double upperPrice, String avgPoints);
}
