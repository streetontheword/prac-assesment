package vttppaf.pracassessment.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Bookings {

    private String resv_id;
    private String userName; 
    private String email; 
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfArrival; 
    private int days;
    private String acc_id;


   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDateOfArrival() {
        return dateOfArrival;
    }
    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
    
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    } 

   
    public Bookings() {
    }
    

    
    public Bookings(String userName, String email, Date dateOfArrival, int days, String acc_id) {
        this.userName = userName;
        this.email = email;
        this.dateOfArrival = dateOfArrival;
        this.days = days;
        this.acc_id = acc_id;
    }
    
    @Override
    public String toString() {
        return "Bookings [userName=" + userName + ", email=" + email + ", dateOfArrival=" + dateOfArrival + ", days="
                + days + ", acc_id=" + acc_id + "]";
    }
    public String getAcc_id() {
        return acc_id;
    }
    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }
    public String getResv_id() {
        return resv_id;
    }
    public void setResv_id(String resv_id) {
        this.resv_id = resv_id;
    }
   
   
    
    

    
}
