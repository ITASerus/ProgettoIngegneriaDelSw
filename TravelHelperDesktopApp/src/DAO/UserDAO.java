/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package DAO;

// Model
import Model.User;

/**
 *
 * @author ernestodecrecchio
 */
public interface UserDAO {
    
    public Integer getNum();
    
    public User getByUsernameOrEmail(String usernameOrEmail);
}
