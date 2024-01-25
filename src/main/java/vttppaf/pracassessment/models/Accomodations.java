package vttppaf.pracassessment.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


public class Accomodations {


    private String country; 

    
    
    @Min(value = 1, message = "Number of persons must be at least 1")
    @Max(value = 10, message = "Number of persons must not exceed 10")
    private int numberOfPerson; 

    @Min(value = 1, message = "Number must be at least 1")
    private double minPrice;

    @Max(value = 10000, message = "Number of persons must not exceed 10000")
    private double maxPrice; 


  
    public int getNumberOfPerson() {
        return numberOfPerson;
    }
    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }
   
    public double getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
    public double getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
   

    public Accomodations(String country,
            @Min(value = 1, message = "Number of persons must be at least 1") @Max(value = 10, message = "Number of persons must not exceed 10") int numberOfPerson,
            @Min(value = 1, message = "Number must be at least 1") double minPrice,
            @Max(value = 10000, message = "Number of persons must not exceed 10000") double maxPrice) {
        this.country = country;
 
        this.numberOfPerson = numberOfPerson;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    public Accomodations() {
    }


    @Override
    public String toString() {
        return "Accomodations [name=" + ", numberOfPerson=" + numberOfPerson + ", minPrice=" + minPrice
                + ", maxPrice=" + maxPrice + "]";
    }
    

  

    


    
    
}
