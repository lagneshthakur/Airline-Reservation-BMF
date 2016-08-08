package bmf.services.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bmf.services.dao.Database;
import bmf.services.dto.Flight;

public class Flightmanager {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public ArrayList<Flight> GetFlights()throws Exception {
		ArrayList<Flight> flights= new ArrayList<Flight>();
		try {
		Database database= new Database();
		Connection connection = database.Get_Connection();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM flights");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
		Flight flight = new Flight();
		flight.setId(rs.getInt("id"));
		flight.setAirline_name(rs.getString("airline_name"));
		flight.setFrom_location(rs.getString("from_location"));
		flight.setTo_location(rs.getString("to_location"));
		flight.setPrice(rs.getInt("price"));
		Date date_departure = formatter.parse(rs.getString("departure_time"));
		flight.setDeparture_time(date_departure);
		Date date_arrival = formatter.parse(rs.getString("arrival_time"));
		flight.setArrival_time(date_arrival);
		double diff = date_arrival.getTime() - date_departure.getTime();
		double duration = diff/(1000*60*60);
		flight.setDuration(duration);
		flights.add(flight);
		}
		}
		catch (Exception e) {
		throw e;
		}
		return flights;
		}
	
	public ArrayList<Flight> GetFlightsbyFilter(String from_location, String to_location)throws Exception {
		ArrayList<Flight> flights= new ArrayList<Flight>();
		try {
		Database database= new Database();
		Connection connection = database.Get_Connection();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM flights WHERE from_location ='"+from_location+"' AND to_location='"+to_location+"'");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
		Flight flight = new Flight();
		flight.setId(rs.getInt("id"));
		flight.setAirline_name(rs.getString("airline_name"));
		flight.setFrom_location(rs.getString("from_location"));
		flight.setTo_location(rs.getString("to_location"));
		flight.setPrice(rs.getInt("price"));
		Date date_departure = formatter.parse(rs.getString("departure_time"));
		flight.setDeparture_time(date_departure);
		Date date_arrival = formatter.parse(rs.getString("arrival_time"));
		flight.setArrival_time(date_arrival);
		double diff = date_arrival.getTime() - date_departure.getTime();
		double duration = diff/(1000*60*60);
		flight.setDuration(duration);
		flights.add(flight);
		}
		}
		catch (Exception e) {
		throw e;
		}
		return flights;
		}
	
	public String AddFlight(String airline_name,String from_location,String to_location,String departure_time,String arrival_time, int price)throws Exception {
		String result;
		try {
		Database database= new Database();
		Connection connection = database.Get_Connection();
		Date date_departure = formatter.parse(departure_time);
		Date date_arrival = formatter.parse(arrival_time);
		double diff = date_arrival.getTime() - date_departure.getTime();
		double dbl_duration = diff/(1000*60*60);
		int hours = (int)dbl_duration;
		double dbl_minutes = dbl_duration-(int)dbl_duration;
		double temp = dbl_minutes*60;
		int minutes = (int)temp;
		String duration = hours +":"+minutes;
		PreparedStatement ps = connection.prepareStatement("INSERT INTO flights values (nextval('flights_id_seq'),'"+airline_name+"','"+from_location+"','"+to_location+"','"+departure_time+"','"+arrival_time+"','"+duration+"','"+price+"')");
		ps.executeUpdate();
		result="true";
		}
		catch (Exception e) {
			result="false";
			throw e;
		}
		return result;
		}
	
}
