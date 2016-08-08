package bmf.services.dto;

import java.util.Date;

public class Flight {

	   private int id;
	   private int price;
	   private String airline_name;
	   private String from_location;
	   private String to_location;
	   private Date departure_time;
	   private Date arrival_time;
	   private double duration;

	   public Flight(){}
	   
	   public Flight(int id,int price, String airline_name, String from_location, String to_location, Date departure_time, Date arrival_time, double duration){
	      this.id = id;
	      this.price = price;
	      this.airline_name = airline_name;
	      this.from_location = from_location;
	      this.to_location = to_location;
	      this.departure_time = departure_time;
	      this.arrival_time = arrival_time; 
	      this.duration= duration;
	   }
		   public int getId() {
			      return id;
		   		}

		   public void setId(int id) {
		      this.id = id;
	   			}
		   public String getAirline_name() {
			      return airline_name;
		   		}

		   public void setAirline_name(String airline_name) {
		      this.airline_name = airline_name;
	   			}
		   public String getFrom_location() {
			      return from_location;
		   		}

		   public void setFrom_location(String from_location) {
		      this.from_location = from_location;
	   			}
		   public String getTo_location() {
			      return to_location;
		   		}

		   public void setTo_location(String to_location) {
		      this.to_location = to_location;
	   			}
		   public Date getDeparture_time() {
			      return departure_time;
		   		}

		   public void setDeparture_time(Date departure_time) {
		      this.departure_time = departure_time;
	   			}
		   public Date getArrival_time() {
			      return arrival_time;
		   		}

		   public void setArrival_time(Date arrival_time) {
		      this.arrival_time = arrival_time;
	   			}
		   public double getDuration() {
			      return duration;
		   		}

		   public void setDuration(double duration) {
		      this.duration = duration;
	   			}
		   public double getPrice() {
			      return price;
		   		}

		   public void setPrice(int price) {
		      this.price = price;
	   			}
}

