package vttppaf.pracassessment.controllers;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttppaf.pracassessment.models.Accomodations;
import vttppaf.pracassessment.models.Bookings;
import vttppaf.pracassessment.models.Listing;
import vttppaf.pracassessment.service.ListingsService;

@Controller
@RequestMapping(path="/")
public class ListingsController {

@Autowired
ListingsService accomSvc; 

    @GetMapping
    public String landingPage(Model m ){

        List<String> listOfCountry = accomSvc.getCountry();
        m.addAttribute("accoms", new Accomodations());
        m.addAttribute("listOfCountries", listOfCountry);
        return "view1";
    }


    @GetMapping(path="/search")
    public String postSearch(@Valid @ModelAttribute("accoms") Accomodations accoms, BindingResult result, Model m, HttpSession sess){
        if (result.hasErrors()){
            System.out.println("error");
            return "view1";
        }
        String countryName = accoms.getCountry();
        int numberOfPerson = accoms.getNumberOfPerson();
        double minPrice = accoms.getMinPrice();
        double maxPrice = accoms.getMaxPrice();

    
        List<Listing> list = accomSvc.getListing(countryName, minPrice, maxPrice, numberOfPerson);
        
        for (Listing l : list){
        String url = l.getImage();
        if(url.equals(null)){
            return "https://www.google.com/url?sa=i&url=https%3A%2F%2Fstock.adobe.com%2Fsearch%3Fk%3Dplaceholder%2Bicon&psig=AOvVaw2dVJrngUjcpj_NxoI2PcNL&ust=1706183338815000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCLDYhrH69YMDFQAAAAAdAAAAABAD";
        }
        }

        m.addAttribute("listing", list);
        m.addAttribute("accoms", accoms);
        sess.setAttribute("accoms", accoms);
        sess.setAttribute("listing", list);

        return "view2";

    }

    @GetMapping(path="/listing/{listing_id}")
    public String getListing(@PathVariable("listing_id") String id, Model m, HttpSession sess){
        
        sess.setAttribute("id", id);
        Bookings book = new Bookings(); 
        Listing list = accomSvc.getListById(id);
        System.out.println(list);
        m.addAttribute("onelisting", list);
        m.addAttribute("booking", book);
        return"view3";

    }


    @PostMapping(path="/booking")
    public String postBooking(@ModelAttribute Bookings booking, String id, HttpSession sess, Model m) throws Exception{
       
       
        int durationOfStay = booking.getDays(); 
        System.out.println("duration of stay from form" + durationOfStay);

        String acc_id = (String)sess.getAttribute("id");
        System.out.println("acc_id>>>>" + acc_id);

        Integer vacancy = accomSvc.getVacancy(acc_id);
        System.out.println("vaccancy from sql" + vacancy);

        if(vacancy > durationOfStay){
           
            String accId = (String)sess.getAttribute("id");
            String resv_id = UUID.randomUUID().toString().substring(0, 8);
            booking.setResv_id(resv_id);
            String userName = booking.getUserName();
            String email = booking.getEmail();
            Date dateOfArrival = booking.getDateOfArrival(); 
            int durationOfStayy = booking.getDays(); 
            int newVaccancy = vacancy - durationOfStay; 
            accomSvc.saveReservation(resv_id, userName, email, dateOfArrival, durationOfStayy, acc_id, newVaccancy);
            m.addAttribute("booking", booking);
            return "view4";

        }
        System.out.println("no availability");
        return "view3";
    }







    
}
