package bmf.services.model;

import java.sql.Connection;

import bmf.services.dao.Database;
import bmf.services.dao.Userdao;
import bmf.services.dto.User;

public class Usermanager {

public User GetUsers(String Email)throws Exception {
	User feeds = null;
	try {
	Database database= new Database();
	Connection connection = database.Get_Connection();
	Userdao user= new Userdao();
	feeds=user.GetFeeds(connection,Email);
	}
	catch (Exception e) {
	throw e;
	}
	return feeds;
	}

public String RegisterUser(String Firstname,String Lastname,String Email,String Password)throws Exception{
	String result="";
	try {
		Database database= new Database();
		Connection connection = database.Get_Connection();
		Userdao user= new Userdao();
		result=user.RegisterFeeds(connection,Firstname,Lastname,Email.toLowerCase(),Password);
		}
		catch (Exception e) {
		throw e;
		}
	return result;
	}
}

