package userManageMent2;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userManagement {
    private String name;
    private String age;
    private String choice;
    private String id;
    private String email;
    String cEmail;

    public userManagement(Scanner in) {
	subClass sB = new subClass(name, age, choice, in);
    }

    public class subClass {

	public subClass(String name, String age, String choice, Scanner in) {
	    System.out.println("*****************************************");
	    System.out.println("************Menu Options***********");
	    System.out.println("*****************************************");

	    System.out.println("1 - Add User");
	    System.out.println("2 - Delete User");
	    System.out.println("3 - Update User");
	    System.out.println("4 - List User");
	    System.out.println("5 - Quit");

	    choice = in.nextLine();
	    if (choice.equals("1")) {
//		addUser(id, in, name, age);
		addData(in);
	    } else if (choice.equals("2")) {
		delete_data(in);
	    } else if (choice.equals("3")) {
		update_data(in);
	    } else if (choice.equals("4")) {
		read_data(in);
	    } else if (choice.equals("5")) {
		System.out.println("Good Bye Dingis");
		System.exit(0);
	    }
	}

	public void addData(Scanner in) {
	    DB_Connection obj_DB_Connection = new DB_Connection();
	    Connection connection = obj_DB_Connection.get_connection();
	    PreparedStatement ps = null;
	    System.out.println("Enter Name: ");
	    name = in.nextLine();
	    System.out.println("Enter Age: ");
	    age = in.nextLine();
	    System.out.println("Enter Email: ");
	    email = in.nextLine();
	    String[] array = new String[3];
	    String passwordSet = "ABCDEFGHIJKLMNOPQRSTUWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%";
	    char[] password = new char[5];
	    for (int i = 0; i < 5; i++) {
		int rand = (int) (Math.random() * passwordSet.length());
		password[i] = passwordSet.charAt(rand);

	    }
	    id = new String(password);
	    try {
		String query = "insert into users(user_id,user_name,user_age,user_email) values (?,?,?,?)";
		ps = connection.prepareStatement(query);
		ps.setString(1, id);
		ps.setString(2, name);
		ps.setString(3, age);
		ps.setString(4, email);
//			System.out.println(ps);
		ps.executeUpdate();
		System.out.println("User inserted into database successfully");
	    } catch (Exception e) {
		System.out.println(e);
	    }
	    subClass sB = new subClass(name, age, choice, in);
	}

	public void delete_data(Scanner in) {
	    DB_Connection obj_DB_Connection = new DB_Connection();
	    Connection connection = obj_DB_Connection.get_connection();
	    PreparedStatement ps = null;
	    System.out.println("Enter The Email Of The User You Would Like To Delete: ");
	    cEmail = in.nextLine();
	    try {
		String query = "delete from users where user_email = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, cEmail);
		ps.executeUpdate();
		System.out.println("User Deleted");
	    } catch (Exception e) {
		System.out.println(e);
	    }
	    subClass sB = new subClass(name, age, choice, in);

	}

	public void update_data(Scanner in) {
	    DB_Connection obj_DB_Connection = new DB_Connection();
	    Connection connection = obj_DB_Connection.get_connection();
	    PreparedStatement ps = null;

	    System.out.println("Enter The Email Of The User You Would Like To Update: ");
	    cEmail = in.nextLine();
	    System.out.println("Enter Name: ");
	    name = in.nextLine();
	    System.out.println("Enter Age: ");
	    age = in.nextLine();
	    System.out.println("Enter Email: ");
	    email = in.nextLine();
	    
	    try {
		String query = "update users set user_name=?, user_age=?, user_email=? where user_email=?";
		ps = connection.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, age);
		ps.setString(3, email);
		ps.setString(4, cEmail);
		System.out.println(ps);
		
		ps.executeUpdate();
		System.out.println("User Updated");
	    } catch (Exception e) {
		System.out.println(e);
	    }
	    
	    subClass sB = new subClass(name, age, choice, in);
	}

	public void read_data(Scanner in) {
	    DB_Connection obj_DB_Connection = new DB_Connection();
	    Connection connection = obj_DB_Connection.get_connection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
		String query = "select * from users";
		ps = connection.prepareStatement(query);
		// ps.setString(1, sl_no);
		System.out.println(ps);
		rs = ps.executeQuery();
		while (rs.next()) {
		    System.out.println("Name: " + rs.getString("user_name"));
		    System.out.println("Age: " + rs.getString("user_age"));
		    System.out.println("ID: " + rs.getString("user_id"));
		    System.out.println("Email: " + rs.getString("user_email"));
		    System.out.println("---------------");
		}
	    } catch (Exception e) {
		System.out.println(e);
	    }
	    subClass sB = new subClass(name, age, choice, in);
	}
    }
}
