package it.travelapp.travelapp.model;

import java.util.Date;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"registrationDate"}, allowGetters = true)
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique=true)
    private String email;

    @NotBlank
    @Column(unique=true)
    private String username;

    @NotBlank
    private String password;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date registrationDate;

    private String realName;
    private String realSurname;
    private Boolean viewRealName;
    private String role;
    private String image;

    //Foreign Keys
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER) //or lazy?
    @JsonIgnoreProperties("user")
    @JsonIgnore
    private Set<Review> reviews;

    // Getter and Setters Methods

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) { this.realName = realName; }

    public String getRealSurname() {
        return realSurname;
    }
    public void setRealSurname(String realSurname) {
        this.realSurname = realSurname;
    }

    public Boolean getViewRealName() {
        return viewRealName;
    }
    public void setViewRealName(Boolean viewRealName) {
        this.viewRealName = viewRealName;
    }

    public String getRole() { return role; }
    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() { return image;}
    public void setImage(String image) {this.image = image; }

    public Set<Review> getReviews() { return this.reviews; }
}