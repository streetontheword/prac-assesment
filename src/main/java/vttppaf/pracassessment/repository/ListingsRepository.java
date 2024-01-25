package vttppaf.pracassessment.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttppaf.pracassessment.models.Bookings;
import vttppaf.pracassessment.models.Listing;

@Repository
public class ListingsRepository {

@Autowired
private MongoTemplate mongoTemp; 

@Autowired 
private JdbcTemplate template; 




   /* 
   db.listings.aggregate([
{
    $group: {
        _id: "$address.country"
    } 
} 
]);*/ 


public List<String>  getCountry(){

    GroupOperation groupByCountry = Aggregation.group("address.country");
    Aggregation pipeline = Aggregation.newAggregation(groupByCountry);
    AggregationResults<Document> results = mongoTemp.aggregate(pipeline, "listings", Document.class);
    List<String> listOfCounts = new ArrayList<>();
    for (Document document : results) {
        String countryName = document.getString("_id");
   
        listOfCounts.add(countryName);
    }
    System.out.println("list of country>>>>" + listOfCounts);
    return listOfCounts; 


}

/* db.listings.find({
     "address.country": "Australia",
      "price": {$gte: 1, $lte:1000},
       "accommodates": 1,
   
       
        
},
{ name: 1, price: 1, accommodate: 1, "images.picture_url": 1, "address.country": 1, "address.street": 1, "address.suburb": 1}
)
   */
//or 

/*
 db.listings.aggregate([
{
    $match :{
        "address.country": "Australia",
        "price": {$gte: 1, $lte:1000},
        "accommodates": 1    
    }
},
{
    $project: {
        "name": 1, "price": 1, "accommodates": 1,
        image: "$images.picture_url",
        country: "$address.country"
    }
},
{
    $sort: {price: 1}
}
]);
 */

 public List<Listing> getListings(String countryName, double maxPrice, double minPrice, int Accommodates){

    Query query = Query.query(Criteria.where("address.country").is(countryName)
   									 .and("price").gte(minPrice).lte(maxPrice)
									 .and("accommodates").is(Accommodates));
	query.fields().include("name","price","accommodates","images.picture_url","address.country","address.street","address.suburbs","description");
	List<Document> results = mongoTemp.find(query, Document.class,"listings");
  
	List<Listing> listOfListings = new ArrayList<>(); 
	
	for (Document document : results) {
        
		Listing list = new Listing(); 
		list.set_id(document.getString("_id"));
		list.setName(document.getString("name"));
		list.setPrice(document.getDouble("price"));
		list.setAccommodates(document.getInteger("accommodates"));
        list.setDescription(document.getString("description"));
       
        //for url 
		Document url = (Document) document.get("images");
		String url_string = url.getString("picture_url");
		list.setImage(url_string);

        //for country
        Document country = (Document) document.get("address");
        String country_name = country.getString("country");
        String country_street = country.getString("street");
        list.setAddress(country_name);
        list.setAddress(country_street);
        listOfListings.add(list);
        
        	
	}
    return listOfListings; 
 }

 /* db.listings.aggregate([
    {
        $match: {_id: "30479760"}
    },
    {
        $unwind: "$amenities"
    }
]); */
 
    public Listing getListingById(String id){

        Query query = Query.query(Criteria.where("_id").is(id));
        Document result = mongoTemp.findOne(query, Document.class,"listings");
        // System.out.println(result);
        Listing list = new Listing(); 
        list.set_id(result.getString("_id"));
        list.setDescription(result.getString("description"));
        list.setPrice(result.getDouble("price"));

        List<String> amenities = result.getList("amenities", String.class);
        list.setAmenities(amenities);
        Document address = (Document) result.get("address");
        String stringAdd = address.getString("street");
        list.setAddress(stringAdd);
        

        Document url = (Document) result.get("images");
		String url_string = url.getString("picture_url");
		list.setImage(url_string);
        

        return list;

        

        
    }
    
       public Integer getVaccancy(String id ){
        System.out.println("THIS IS THE ID>>>>>>" + id);

        SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_VACCANCY, id);
        
        System.out.println("this is still running ");
        while (rs.next()) {
            int vacancyValue = rs.getInt("vacancy");
            System.out.println("Vacancy: " + vacancyValue);
            return vacancyValue; // Return the first found value and exit the method
        }
    
        // No result found
        // System.out.println("No result found for ID: " + id);
        return null;
    }


    //resv_id, name, email, arrival_date, duration, acc_id
    public boolean makeBooking(String resv_id, String name, String email, Date date, int duration, String acc_id ){
       return template.update(Queries.SQL_ADD_RESERVATIONS, resv_id, name, email, date, duration, acc_id) > 0;
    }

    public boolean updateVacancy(int vacancy,String acc_id){

        System.out.println("UPDATING VACCANCY>>>>" + vacancy);

        if(template.update(Queries.SQL_UPDATE_VACANCY, vacancy, acc_id) >0){
        
        return true;
        }
        return false; 
    }
       }
    






    




