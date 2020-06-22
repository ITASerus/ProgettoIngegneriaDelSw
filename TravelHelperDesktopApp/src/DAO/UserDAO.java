/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
/**
 *
 * @author ernestodecrecchio
 */
public interface UserDAO {
    
    public Integer getNum();
    
    public User getByUsernameOrEmail(String usernameOrEmail);
}
