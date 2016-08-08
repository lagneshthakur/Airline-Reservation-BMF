package bmf.services.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bmf.services.dao.Database;
import bmf.services.dto.Flight;

public class Seatmanager {
	public void generateSeats(String airline_name,String from_location,String to_location,String departure_time)throws Exception {
		int flight_id=0;
		try {
			Database database= new Database();
			Connection connection = database.Get_Connection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM flights WHERE from_location ='"+from_location+"' AND to_location='"+to_location+"' AND departure_time='"+departure_time+"' AND airline_name='"+airline_name+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
			flight_id = (rs.getInt("id"));
			}
			for(int i=0;i<35;i++)
			{
			ps = connection.prepareStatement("INSERT into seats values (nextval('seats_id_seq'),"+flight_id+")");
			ps.executeUpdate();
			}
		}
		catch (Exception e) {
		throw e;
		}
		}
	
}
