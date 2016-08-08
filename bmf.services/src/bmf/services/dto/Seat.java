package bmf.services.dto;

public class Seat {
	private int id;
	private int flight_id;
	private boolean is_available;
	
	public Seat(){}
	   
	   public Seat(int id,int flight_id,boolean is_available){
	      this.id = id;
	      this.flight_id = flight_id;
	      this.is_available = is_available;
	   }
	   
	   public int getId() {
		      return id;
	   		}

	   public void setId(int id) {
	      this.id = id;
			}
	   public int getflight_Id() {
		      return flight_id;
	   		}

	   public void setflight_Id(int flight_id) {
	      this.flight_id = flight_id;
			}
	   public boolean getis_available() {
		      return is_available;
	   		}

	   public void setis_available(boolean is_available) {
	      this.is_available = is_available;
			}
}
