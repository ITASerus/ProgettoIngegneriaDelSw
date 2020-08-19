/*
 * Progetto Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Applicativo Desktop realizzato da Ernesto De Crecchio - Matricola N86001595
 */

/**
 *
 * @author ernestodecrecchio
 */

import Controller.LogInDialogController;
import Singleton.LoggedUserSingleton;

import org.junit.*;
import static org.junit.Assert.*;

public class LogInControllerTest {
    private LogInDialogController logInController;
    
    @Before
    public void setUp() {
        logInController = new LogInDialogController();
    }
    
    
    @Test
    public void testLogInWithWrongData() {
        String usernameOracolo = "wrongdata";
        String passwordOracolo = "wrongdata";
        
        //Check if the result is what expected
        assertEquals(logInController.logInUser(usernameOracolo, passwordOracolo), -1);
        
        //Check if there isn't any logged user
        assertNull("Login with wrong data", LoggedUserSingleton.getIstance().getLoggedUser());
    }
    
    @Test
    public void testLogInWithUserData() {
        String usernameOracolo = "user";
        String passwordOracolo = "password";
        
        //Check if the result is what expected
        assertEquals(logInController.logInUser(usernameOracolo, passwordOracolo), 0);
        
        //Check if there isn't any logged user
        assertNull("Login with User data without privileges", LoggedUserSingleton.getIstance().getLoggedUser());
    }
    
    @Test
    public void testLogInWithAdminData() {
        String usernameOracolo = "admin";
        String passwordOracolo = "password";
        
        //Check if the result is what expected
        assertEquals(logInController.logInUser(usernameOracolo, passwordOracolo), 1);
        
        //Check if there is a logged user
        assertNotNull("Login with Admin data", LoggedUserSingleton.getIstance().getLoggedUser());
    }
}
