package bmf.services.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database{

	private String URL = "jdbc:postgresql://localhost:5432/BMF";
	private String USER = "postgres";
	private String PASS = "postgres";
	public Database(){
		
	}
	
	public Connection Get_Connection(){
		try {
			Class.forName(org.postgresql.Driver.class.getName());
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
			}
		Connection conn=null;
		try{
		conn= DriverManager.getConnection(URL, USER, PASS);
		}
		catch (Exception e){
			System.out.println("Error: unable to get connection!");
			   System.exit(1);
		}
		return conn;
		}
	}