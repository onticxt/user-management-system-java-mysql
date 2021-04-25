package userManageMent2;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class User {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	mMethods();
    }

    public static void mMethods() {
	Scanner in = new Scanner(System.in);

	String uName;
	String password;
	String cUName;
	String cP;
	String checkC;
	String nUName;
	String nPassword;

	System.out.println("Enter 1 to login or Enter 2 to create new login info");
	checkC = in.nextLine();

	if (checkC.equals("2")) {
	    DB_Connection2 obj_DB_Connection = new DB_Connection2();
	    Connection connection = obj_DB_Connection.get_connection();
	    PreparedStatement ps = null;
	    System.out.println("Enter User Name: ");
	    nUName = in.nextLine();
	    System.out.println("Enter Password");
	    nPassword = in.nextLine();

	    try {
		String query = "insert into login(username,password) values (?,?)";
		ps = connection.prepareStatement(query);
		ps.setString(1, nUName);
		ps.setString(2, nPassword);
		ps.executeUpdate();
		System.out.println("User inserted into database successfully");
	    } catch (Exception e) {
		System.out.println(e);
	    }
	    User uS = new User();
		uS.mMethods();
	} else if (checkC.equals("1")) {

	    System.out.println("Welcome To User Manager");
	    System.out.println("Enter Username: ");

	    uName = in.nextLine();

	    System.out.println("Enter Password");
	    password = in.nextLine();

	    DB_Connection2 obj_DB_Connection = new DB_Connection2();
	    Connection connection = obj_DB_Connection.get_connection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
		String query = "select * from login";
		ps = connection.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
//		    System.out.println(rs.getString("username"));
//		    System.out.println(rs.getString("password"));
		    
		    if ((uName.equals(rs.getString("username")) && (password.equals(rs.getString("password"))))) {
			System.out.println("Secsesfully Loged In");
			userManagement uM = new userManagement(in);
			break;
		    } 
		    

		}
		
			System.out.println("Incorrect Username or Password");
			System.out.println("Try Again");
			User uS = new User();
			uS.mMethods();
			
		    
		
	 } 
	    
	    catch (Exception e) {
		System.out.println(e);
	    }

	}
    }
}
