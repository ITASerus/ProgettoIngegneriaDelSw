package it.travelapp.travelapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.io.Serializable;

@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"date"}, allowGetters = true)
public class Review implements Serializable{
    @Id
    private Long id;

    @NotBlank
    private String description;

    @NotBlank
    private Integer points;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date date;

    private Long userID;
    private Long structureID;

    // Getter and Setters Methods

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String email) {
        this.description = description;
    }

    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) { this.date = date; }

    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) { this.userID = userID; }

    public Long getStructureID() {
        return structureID;
    }
    public void setStructureID(Long structureID) { this.structureID = structureID; }
}
