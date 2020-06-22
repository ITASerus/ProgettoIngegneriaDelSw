/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Model.User;

/**
 *
 * @author ernestodecrecchio
 */
public class LoggedUserSingleton {
    private static LoggedUserSingleton instance = null;

    User loggedUser;
    
    private LoggedUserSingleton() {}
    
    public static LoggedUserSingleton getIstance() {
        if(instance==null)
            synchronized(LoggedUserSingleton.class) {
                if( instance == null )
                    instance = new LoggedUserSingleton();
            }
        return instance;
    }
    
    public User getLoggedUser() {
        return loggedUser;
    }
    
    public void setLoggedUser(User loggedUser, String token) {
        this.loggedUser = loggedUser;
        this.loggedUser.setToken(token);
    }
}
