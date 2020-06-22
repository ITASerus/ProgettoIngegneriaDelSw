/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package Singleton;

// Model
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
