package vttppaf.pracassessment;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;


import vttppaf.pracassessment.repository.ListingsRepository;


@SpringBootApplication
public class PracAssessmentApplication implements CommandLineRunner {

	@Autowired
	MongoTemplate mongoTemp;
	@Autowired 
	ListingsRepository accomRepo; 
	@Autowired
	JdbcTemplate template; 

	public static void main(String[] args) {
		SpringApplication.run(PracAssessmentApplication.class, args);
	}


	
	

	/*
	 * db.listings.aggregate([
	 * {
	 * $group: {
	 * _id: "$address.country"
	 * }
	 * }
	 * ]);
	 */

	@Override
	public void run(String... args) throws Exception {
		// SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_VACCANCY);
		// while(rs.next()){
		// 	int vacancyValue = rs.getInt("vacancy");

		// 	System.out.println(vacancyValue);
		// }
		
		


		// GroupOperation groupByCountry = Aggregation.group("address.country");
		// Aggregation pipeline = Aggregation.newAggregation(groupByCountry);
		// AggregationResults<Document> results = mongoTemp.aggregate(pipeline, "listings", Document.class);
		// for (Document document : results) {
		// 	System.out.println(document);
		// }


		// Query query = Query.query( Criteria.where("description").regex("looking","i"));
		// List<Document> result = mongoTemp.find(query, Document.class, "listings");
		// System.out.println(result);


		// accomRepo.getCountry();


/*db.listings.aggregate([
{
    $match :{
        "address.country": "Australia"
    }
},
{
    $project: {
        "name": 1, "price": 1, 
        image: "$images.picture_url",
        country: "$address.country"
    }
},
{
    $sort: {price: 1}
}
]);

*/
	// MatchOperation matchCountry= Aggregation.match(Criteria.where("address.country").is("Australia"));
	// ProjectionOperation projectFields = Aggregation.project("name", "price", "accommodates")
	// 											   .and("images.picture_url").as("image")
	// 											   .and("address.country").as("country");
	// SortOperation sortByTitle = Aggregation.sort(Sort.by(Direction.ASC,"price"));
	// Aggregation pipeline = Aggregation.newAggregation(matchCountry,projectFields, sortByTitle);
	// AggregationResults<Document> results = mongoTemp.aggregate(pipeline, "listings", Document.class);		
				
	// for (Document document : results) {
	// 	System.out.println(document);
	
	// }


	/* db.listings.find({
		"address.country": "Australia",
		 "price": {$gte: 1, $lte:1000},
		  "accommodates": 1
		   
   },
   { name: 1, price: 1, accommodates: 1, "images.picture_url": 1, "address.country": 1}
   )
   */

   
//    Query query = Query.query(Criteria.where("address.country").is("Australia")
//    									 .and("price").gte(1).lte(1000)
// 									 .and("accommodates").is(1));
// 	query.fields().include("name","price","accommodates","images.picture_url","address.country");
// 	List<Document> results = mongoTemp.find(query, Document.class,"listings");
   
	
	
// 	for (Document document : results) {
// 		System.out.println(document);
		
// 		String id = document.getString("_id");
// 		String name = document.getString("name");
// 		int accoms = document.getInteger("accommodates");



// Document url = (Document) document.get("images");
// String url_string = url.getString("picture_url");
// System.out.println("url" + url_string);
// //for country
// Document country = (Document) document.get("address");
// String country_name = country.getString("country");
// System.out.println("country" + country_name);




// 	}

 		

}
}