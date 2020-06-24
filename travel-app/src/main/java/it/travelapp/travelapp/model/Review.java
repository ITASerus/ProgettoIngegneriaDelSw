package it.travelapp.travelapp.model;

import java.util.Date;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"date"}, allowGetters = true)
public class Review implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer points;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date date;

    //Foreign Keys
    @ManyToOne(fetch = FetchType.EAGER) //or lazy?
    @JoinColumn(name = "structureID", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnoreProperties("reviews")
    @JsonIgnore
    private Structure structure;

    @ManyToOne(fetch = FetchType.EAGER) //or lazy?
    @JoinColumn(name = "userID", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonIgnoreProperties("reviews")
    @JsonIgnore
    private User user;

    @Column(name = "structureID", nullable=false)
    private Long structureID;

    @Column(name = "userID", nullable=false)
    private Long userID;

    // Getter and Setters Methods
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Long getStructureID() { return this.structureID; }
    public void setStructure(Long structureID) { this.structureID = structureID; }

    public Long getUserID() { return this.userID; }
    public void setUserID(Long userID) { this.userID = userID; }

    public Structure getStructure() { return this.structure; }
    public void setStructure(Structure structure) { this.structure = structure; }

    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }
}
