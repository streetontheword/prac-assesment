package vttppaf.pracassessment.models;

import java.util.List;

public class Listing {

    private String name; 
    private String _id; 
    private String description; 
    private String address;
    private String image; 
    private double price; 
    private int accommodates; 
    private List<String> amenities;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public List<String> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
    public int getAccommodates() {
        return accommodates;
    }
    public void setAccommodates(int accommodates) {
        this.accommodates = accommodates;
    } 


    
   
    
    public Listing(String name, String _id, String description, String address, String image, double price,
            int accommodates, List<String> amenities) {
        this.name = name;
        this._id = _id;
        this.description = description;
        this.address = address;
        this.image = image;
        this.price = price;
        this.accommodates = accommodates;
        this.amenities = amenities;
    }

    public Listing() {
    }

    @Override
    public String toString() {
        return "Listing [name=" + name + ", _id=" + _id + ", description=" + description + ", address=" + address
                + ", image=" + image + ", price=" + price + ", accommodates=" + accommodates + ", amenities="
                + amenities + "]";
    }
    
    
    
    
    
    
}
