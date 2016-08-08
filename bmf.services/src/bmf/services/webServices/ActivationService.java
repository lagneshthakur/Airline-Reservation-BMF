package bmf.services.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import bmf.services.dao.Database;

@Path("/ActivationService")
public class ActivationService {
	@GET
	@Path("/Activate/{Key}")
	@Produces("application/json")
	public String feed(@PathParam("Key") String Key)
	{ 	int user_id=0;
	Database database= new Database();
	Connection connection = database.Get_Connection();
		try {
				PreparedStatement ps = connection.prepareStatement("SELECT user_id,activation_key FROM activation where activation_key ='"+Key+"'");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					user_id = rs.getInt("user_id");
				break;
				}
			}
			catch (Exception e) {
			}
		
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE users SET is_active = 'true' where id = '" + user_id +"'");
			ps.executeUpdate();
		}
		catch (Exception e) {
		}
		return "Done";
	}

}
