package bmf.services.webServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import bmf.services.model.Usermanager;
import bmf.services.dto.User;

@Path("/LoginService")
public class UserService {

@GET
@Path("/GetUsers/{Email}/{Password}")
@Produces("application/json")
public String feed(@PathParam("Email") String Email,
	    @PathParam("Password") String Password)
{
String result = "";
String email,password;
	try 
	{
	User user = null;
	Usermanager UserManager= new Usermanager();
	user = UserManager.GetUsers(Email.toLowerCase());
	email =user.getEmail();
	//Searching the user by Email	
		if (email != null) {
		//Email found means User exists
			System.out.println("User found");
			password = user.getPassword();
				if ( password.equalsIgnoreCase(Password))
				{
					System.out.println("Password Match");
					if(user.getIs_active()){
						System.out.println("User is activated");
						result = "{\"User\" : \"True\", \"Password\" : \"True\", \"is_active\" : \"True\" }";
					}
					else{
						System.out.println("User not activated");
						result = "{\"User\" : \"True\", \"Password\" : \"True\", \"is_active\" : \"False\" }";
					}
					
				}
				else
				{
					System.out.println("Password Not Match");
					result = "{\"User\" : \"True\", \"Password\" : \"False\", \"is_active\" : \"False\" }";
				}
			
		}
		else
		{
			System.out.println("User not found");
			result = "{\"User\" : \"False\", \"Password\" : \"False\", \"is_active\" : \"False\"}";
		}
	}
	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
return result;
}


@GET
@Path("/PasswordRecovery/{Email}/")
@Produces("application/json")
public String ForgotPassword(@PathParam("Email") String Email)
{
String result = "";
String email,password;
	try 
	{
	User user = null;
	Usermanager UserManager= new Usermanager();
	user = UserManager.GetUsers(Email.toLowerCase());
	email =user.getEmail();
	//Searching the user by Email	
		if (email != null) {
		//Email found means User exists
			System.out.println("User found");
			password = user.getPassword();
			mailAPI.forgotPasswordMail("lagneshthakur@gmail.com",password);
			result = "{\"User\" : \"True\"}";
		}
		else
		{
			System.out.println("User not found");
			result = "{\"User\" : \"False\"}";
		}
	}
	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
return result;
}


}