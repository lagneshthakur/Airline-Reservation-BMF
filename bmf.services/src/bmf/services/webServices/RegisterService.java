package bmf.services.webServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import bmf.services.model.Usermanager;

@Path("/RegisterService")
public class RegisterService {

@GET
@Path("/RegisterUsers/{Firstname}/{Lastname}/{Email}/{Password}")
@Produces("application/json")
public String feed(@PathParam("Firstname") String Firstname,
	    @PathParam("Lastname") String Lastname,@PathParam("Email") String Email,
	    @PathParam("Password") String Password)
{
String result = "";
	try 
	{
	Usermanager UserManager= new Usermanager();
	result = UserManager.RegisterUser(Firstname,Lastname,Email,Password);
	}
	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
return result;
}

}