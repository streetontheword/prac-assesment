package vttppaf.pracassessment.repository;

public class Queries {

 /*SELECT vacancy
FROM acc_occupancy
WHERE acc_id = 10066928; */

// public static final String SQL_SELECT_VACCANCY ="""
//         select vacancy from acc_occupancy 
//         where acc_id = 10066928       
//         """;

public static final String SQL_SELECT_VACCANCY = "select * from acc_occupancy where acc_id = ?";

/*insert into reservations
(resv_id, name, email, arrival_date, duration, acc_id)
values 
("124", "joanna", "Joannacheng22@gmail.com", "2024-01-01", 3, "10108388");
 */

public static final String SQL_ADD_RESERVATIONS="""
         insert into reservations 
        (resv_id, name, email, arrival_date, duration, acc_id)
        values (?,?,?,?,?,?)

        """;

/*update acc_occupancy 
set vacancy = 100
where acc_id = "10006546"; */
public static final String SQL_UPDATE_VACANCY ="""
                
        update acc_occupancy 
         set vacancy = ?
         where acc_id = ?
        
         """;       
    
}


