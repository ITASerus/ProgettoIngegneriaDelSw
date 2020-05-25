package it.travelapp.travelapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "structure")
@EntityListeners(AuditingEntityListener.class)
public class Structure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String place;
    private String category;
    private Integer price;
    private String webSite;
    private String contacts;
    private String tag;
    private String description;

    @Lob
    @Column(length=100000)
    private String image;

    //Foreign Keys
    @OneToMany(mappedBy = "structure", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER) //or lazy?
    @JsonIgnoreProperties("structure")
    @JsonIgnore
    private Set<Review> reviews;


    //Getter and Setter methods

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getWebSite() {
        return webSite;
    }
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getContacts() {
        return contacts;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() { return image;}
    public void setImage(String image) {this.image = image; }

    public Set<Review> getReviews() { return this.reviews; }
}