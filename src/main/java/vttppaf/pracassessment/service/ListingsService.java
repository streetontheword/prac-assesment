package vttppaf.pracassessment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttppaf.pracassessment.models.Bookings;
import vttppaf.pracassessment.models.Listing;
import vttppaf.pracassessment.repository.ListingsRepository;
import vttppaf.pracassessment.repository.Queries;

@Service
public class ListingsService {

    @Autowired
    ListingsRepository accomRepo; 


    public List<String> getCountry(){
        return accomRepo.getCountry(); 

    }

    public List<Listing> getListing(String countryName, double minPrice, double maxPrice, int accommodate){
        System.out.println("service");
        
        return accomRepo.getListings(countryName, maxPrice, minPrice, accommodate);
        
    }


    public Listing getListById(String id){
        return accomRepo.getListingById(id);
    }

    public Integer getVacancy(String id){
        return accomRepo.getVaccancy(id);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public boolean saveReservation(String resv_id, String name, String email, Date date, int duration, String acc_id, int vacancy) throws Exception{
        if (!accomRepo.makeBooking(resv_id, name, email, date, duration, acc_id)) {
            throw new Exception("did not save booking");
        } if (!accomRepo.updateVacancy(vacancy, acc_id)){
            throw new Exception("vaccancy is not updated");
        }
        return true;
    }




    // public boolean updateVacancy(int vacancy,String acc_id){
    //     return accomRepo.updateVacancy(vacancy, acc_id);
    // }
    

    
}
