/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ernestodecrecchio
 */
public class Structure {
    
    private Long id;
    private String name;
    private String place;
    private String category;
    private Integer price;
    private String webSite;
    private String contacts;
    private String tag;
    private String description;
    private String image;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Integer getPrice() { return price;}
    public void setPrice(Integer price) { this.price = price; }

    public String getWebSite() { return webSite; }
    public void setWebSite(String webSite) { this.webSite = webSite; }

    public String getContacts() { return contacts; }
    public void setContacts(String contacts) { this.contacts = contacts; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image;}
    public void setImage(String image) { this.image = image; }
}
