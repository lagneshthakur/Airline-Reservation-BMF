package bmf.services.webServices;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import bmf.services.dto.Flight;
import bmf.services.model.Flightmanager;
import bmf.services.model.Seatmanager;

@Path("/FlightService")
public class FlightService {

	@GET
	@Path("/GetFlights")
	@Produces("application/json")
	public String getFlights()
	{
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try 
		{
		System.out.println("Getting flights");
		Flightmanager FlightManager= new Flightmanager();
		flights = FlightManager.GetFlights();		
		}
		catch (Exception e)
		{
		System.out.println("Exception Error"); //Console 
		}
		String flightsjson = new Gson().toJson(flights);
		
		return flightsjson;
	}
	
	@GET
	@Path("/GetFlights/{from_location}/{to_location}")
	@Produces("application/json")
	public String getFlightsbyFilter(@PathParam("from_location") String from_location,
		    @PathParam("to_location") String to_location)
	{
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try 
		{
		System.out.println("Getting flights");
		Flightmanager FlightManager= new Flightmanager();
		flights = FlightManager.GetFlightsbyFilter(from_location,to_location);		
		}
		catch (Exception e)
		{
		System.out.println("Exception Error"); //Console 
		}
		String flightsjson = new Gson().toJson(flights);
		
		return flightsjson;
	}
	
	@GET
	@Path("/AddFlight/{airline_name}/{from_location}/{to_location}/{departure_time}/{arrival_time}/{price}")
	@Produces("application/json")
	public String AddFlight(@PathParam("airline_name") String airline_name,@PathParam("from_location") String from_location,
		    @PathParam("to_location") String to_location, @PathParam("departure_time") String departure_time,@PathParam("arrival_time") String arrival_time,@PathParam("price") int price)
	{ String result="AddFlight";
	departure_time = departure_time.replace('+', ' ');
	arrival_time = arrival_time.replace('+', ' ');
		try 
		{
		System.out.println("Adding flight");
		Flightmanager FlightManager= new Flightmanager();
		result = FlightManager.AddFlight(airline_name,from_location,to_location, departure_time, arrival_time,price);		
		Seatmanager SeatManager = new Seatmanager();
		SeatManager.generateSeats(airline_name,from_location,to_location, departure_time);
		}
		catch (Exception e)
		{
		System.out.println("Exception Error"); //Console 
		}
		
		return result;
	}
}
