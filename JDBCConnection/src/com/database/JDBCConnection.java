package com.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCConnection {
	
	private static Connection createConnection() throws SQLException {
		Driver driver = new org.postgresql.Driver();
		DriverManager.registerDriver(driver);
		Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcconnection", "vinay", "password");
		return conn;
	}
	
	private static void Insert(Integer id, String firstName, String lastName, String email) throws SQLException{
		System.out.println("Insert is called");
		Connection conn = createConnection();
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO USERS(USER_ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES(?, ?, ?, ?)");
		pstmt.setInt(1, id);
		pstmt.setString(2, firstName);
		pstmt.setString(3, lastName);
		pstmt.setString(4, email);
		pstmt.execute();
	}
	
	public static void Update(Integer id, String firstName, String lastName, String email) throws SQLException{
		System.out.println("Update is called");
		Connection conn = createConnection();
		PreparedStatement pstmt = conn.prepareStatement("UPDATE USERS SET USER_ID = ?, FIRST_NAME = ?, LAST_NAME = ? WHERE EMAIL = ?");
		pstmt.setInt(1, id);
		pstmt.setString(2, firstName);
		pstmt.setString(3, lastName);
		pstmt.setString(4, email);
		pstmt.execute();
	}
	
	public static void Delete(String email) throws SQLException{
		System.out.println("Delete is called");
		Connection conn = createConnection();
		PreparedStatement pstmt = conn.prepareStatement("DELETE FROM USERS WHERE EMAIL = ?");
		pstmt.setString(1, email);
		pstmt.execute();
	}
	
	public static void Display(String email) throws SQLException{
		System.out.println("Display is called");
		Connection conn = createConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ?");
		pstmt.setString(1, email);
		ResultSet result = pstmt.executeQuery();
		while(result.next()) {
			Integer id = result.getInt("USER_ID");
			String firstName = result.getString("FIRST_NAME");
			String lastName = result.getString("LAST_NAME");
			String searchEmail = result.getString("EMAIL");
			System.out.println("userId: "+id+"; firstName: "+firstName+"; lastName: "+lastName+"; email: "+searchEmail);
		}
	}
	
	
	public static void main(String[] args) throws SQLException{
		
		
		boolean iterate = true;
	
		do {
			System.out.println("Please select one option: \n 1. Insert \n 2. Update \n 3. Delete \n 4. Display");
			System.out.print("Enter:");
			Scanner sc1 = new Scanner(System.in);
			String reply1 = sc1.next();
			
			switch(reply1) {
				case "Insert":
					System.out.print("id: ");
					Integer id1 = sc1.nextInt();
					System.out.print("firstName: ");
					String firstName1 = sc1.next();
					System.out.print("lastName: ");
					String lastName1 = sc1.next();
					System.out.print("email: ");
					String email1 = sc1.next();
					Insert(id1, firstName1, lastName1, email1);
					break;
				case "Update":
					System.out.print("id: ");
					Integer id2 = sc1.nextInt();
					System.out.print("email: ");
					String email2 = sc1.next();
					System.out.print("firstName: ");
					String firstName2 = sc1.next();
					System.out.print("lastName: ");
					String lastName2 = sc1.next();
					Update(id2, firstName2, lastName2, email2);
					break;
				case "Delete":
					System.out.print("email: ");
					String email3 = sc1.next();
					Delete(email3);
					break;
				case "Display":
					System.out.print("email: ");
					String email4 = sc1.next();
					Display(email4);
					break;
				default:
					System.out.println("Please enter any of the above options");
			}
			
			
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Would you like to perform another action: ");
			String reply2 = sc2.next();
			
			if(reply2.equals("yes")) {
				iterate = true;
			}
			else {
				iterate = false;
			}
		
		}while(iterate);
		System.out.println("Dengey");
	}
	

}
