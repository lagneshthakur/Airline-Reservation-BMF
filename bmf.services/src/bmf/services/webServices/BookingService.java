package bmf.services.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import bmf.services.dao.Database;

@Path("/BookingService")
public class BookingService {
	@GET
	@Path("/Book/{username}/{flight_id}/{seats}")
	@Produces("application/json")
	public String bookseat(@PathParam("username") String username,@PathParam("flight_id") int flight_id,@PathParam("seats") String seats)
	{ 	
	Database database= new Database();
	Connection connection = database.Get_Connection();
		try {
				PreparedStatement ps = connection.prepareStatement("select * from seats where flight_id="+flight_id+" order by id ");
				ResultSet rs = ps.executeQuery();
				String[] seats_array = seats.split(",");
				int l = seats_array.length;
				int seat_id[]=new int[35];
				int i=0;
				int booked_seats[] = new int[35];
				while(rs.next())
				{
						seat_id[i]=rs.getInt("id");
						i++;
				}
				for(i=0;i<l;i++)
				{
					int bookseat=seat_id[Integer.parseInt(seats_array[i])-1];
					//To be used to generate the ticket
					booked_seats[i]=bookseat;
					ps = connection.prepareStatement("update seats set is_available=false where id="+bookseat);
					ps.executeUpdate();
				}
				String booked_seats_string="{";
				for(i=0;i<35;i++){
					if(booked_seats[i]==0)
						break;
					booked_seats_string+=booked_seats[i]+",";
				}
				int length = booked_seats_string.length() - 1;
				booked_seats_string = booked_seats_string.substring(0, length);
				booked_seats_string+="}";
				//now user user_id, booked_seats[], flight_id to generate a ticket
				ps = connection.prepareStatement("insert into tickets values (nextval('tickets_id_seq'),"+flight_id+",'"+username+"','"+booked_seats_string+"')");
				ps.executeUpdate();
				
			}
			catch (Exception e) {
				System.out.println(e);
			}

		return "true";
	}
	
	
	
	@GET
	@Path("/GetBooked/{flight_id}")
	@Produces("application/json")
	public String book(@PathParam("flight_id") int flight_id)
	{ 	
	Database database= new Database();
	Connection connection = database.Get_Connection();
	String seat_ids="{";
	String x="";
	try {
				PreparedStatement ps = connection.prepareStatement("SELECT * from seats where is_available=false and flight_id='"+flight_id+"'");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					x = rs.getString("id");
					seat_ids += '"' +x+'"'+":\"t\",";
					seat_ids += " ";
				}
				int l = seat_ids.length();
				if(l!=1)
				seat_ids = seat_ids.substring(0, l-2);
				seat_ids += "}";
			}
			catch (Exception e) {
			}
		
		try {
			PreparedStatement ps = connection.prepareStatement("");
			ps.executeUpdate();
		}
		catch (Exception e) {
		}
		return seat_ids.toString();
	}

}
