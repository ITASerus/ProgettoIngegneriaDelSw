/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author ernestodecrecchio
 */
public class Review {
    private Long id;
    private String description;
    private Integer points;
    private Date date;
    private Long userID;
    private Long structureID;

    // Getter and Setters Methods

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String email) { this.description = description; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Long getUserID() { return userID; }
    public void setUserID(Long userID) { this.userID = userID; }

    public Long getStructureID() { return structureID; }
    public void setStructureID(Long structureID) { this.structureID = structureID; }
}
