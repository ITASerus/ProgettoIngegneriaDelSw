/*
 * Progetto di Ingegneria del Software A.A 2019-2020
 * CdL Informatica - Università di Napoli Federico II
 * Realizzato da Ernesto De Crecchio - N86001596
 */

package Model;

/**
 *
 * @author ernestodecrecchio
 */
public class User {
     
    private Long id;
    private String username;
    private String email;
    private String password;
    private String createdAt;
    private String updatedAt;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;
    
    private Object[] roles;
    
    private String token;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCreatedAt() { return createdAt;}
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    
    public Object[] getRoles() { return roles; }
    public void setRoles(Object[] role) { this.roles = role; }
    
     public String getToken() { return token;}
    public void setToken(String token) { this.token = token; }
}
