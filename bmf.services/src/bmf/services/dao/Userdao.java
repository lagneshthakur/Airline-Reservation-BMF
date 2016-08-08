package bmf.services.dao;
import bmf.services.dto.User;
import bmf.services.webServices.mailAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class Userdao 
{
	public User GetFeeds(Connection connection, String Email) throws Exception
	{
	User newuser = new User();
	try
	{
	PreparedStatement ps = connection.prepareStatement("SELECT id,firstname,lastname,email,password,is_active FROM users where email = '" + Email +"'");
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		Integer x=1;
	x=Integer.parseInt(rs.getString("id"));
	newuser.setId(x);
	newuser.setFirstName(rs.getString("firstname"));
	newuser.setLastName(rs.getString("lastname"));
	newuser.setEmail(rs.getString("email"));
	newuser.setPassword(rs.getString("password"));
	Boolean y = (rs.getBoolean("is_active"));
	newuser.setIs_active(y);
	}
	return newuser;
	}
	catch(Exception e)
	{
	throw e;
	}
	}

	public String RegisterFeeds(Connection connection,String Firstname,String Lastname,String Email,String Password) throws Exception
	{
		User newuser = new User();
	try
	{
	PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES (nextval('serial'),'"+Firstname+"','"+Lastname+"','"+Email+"','"+Password+"');");
	ps.execute();
	// get the added user to get ID
	ps = connection.prepareStatement("SELECT id,firstname,lastname,email,password FROM users ORDER BY id DESC");
	ResultSet rs = ps.executeQuery();
	while(rs.next())
	{
		Integer x=1;
	x=Integer.parseInt(rs.getString("id"));
	newuser.setId(x);
	newuser.setFirstName(rs.getString("firstname"));
	newuser.setLastName(rs.getString("lastname"));
	newuser.setEmail(rs.getString("email"));
	newuser.setPassword(rs.getString("password"));
	break;
	}
	Random random = new Random();
	// Add the random number to the activation table for added user
	int id = newuser.getId();
	int activation_number = random.nextInt();
	ps = connection.prepareStatement("INSERT INTO activation VALUES ('"+id+"','"+activation_number+"');");
	ps.execute();
	
	mailAPI.sendRegisterMail("lagneshthakur@gmail.com",activation_number);
	return "{\"User\" : \"Registered\"}";
	}
	catch(org.postgresql.util.PSQLException e)
	{
	System.out.println(e);
	return "{\"User\" : \"Exists\"}";	
	}
	}
	
}
