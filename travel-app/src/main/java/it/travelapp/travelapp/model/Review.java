package it.travelapp.travelapp.model;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import it.travelapp.travelapp.repository.ReviewRepository;
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

    @NotBlank
    private String description;

    @NotBlank
    private Integer points;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date date;

    //Foreign Keys
    @ManyToOne(fetch = FetchType.EAGER) //or lazy?
    @JoinColumn(name = "structureID", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("reviews")
    @JsonIgnore
    private Structure structure;

    @ManyToOne(fetch = FetchType.EAGER) //or lazy?
    @JoinColumn(name = "userID", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("reviews")
    @JsonIgnore
    private User user;

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

    public Structure getStructure() {
        return this.structure;
    }

    public User getUser() {
        return this.user;
    }
}
