/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Structure;
import com.google.gson.JsonArray;
import java.net.http.HttpResponse;
import java.util.HashMap;

/**
 *
 * @author ernestodecrecchio
 */

/**
 * L'interfaccia DAO per le diverse implementazioni di StructureDAO. Definisce le operazioni CRUD.
 */
public interface StructureDAO {
    
    public Integer getNum();
    
    public Structure getByID(Integer id);
    
    public JsonArray getInfo();
    
    public JsonArray getByFilter(String name, String place, String category, String contacts, String webSite, Integer lowerPrice, Integer upperPrice, String avgPoints);

    public HttpResponse<String> newStructure(String body);
    
    public HttpResponse<String> editStructure(Long structureID, String body);
    
    public HttpResponse<String> deleteStructure(Long id);
    
    public HashMap<String,Double> getCoordinates(String place);
}
