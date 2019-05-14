package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import models.UserBean;
import utils.Conn;

public class UserDAO {
	
	private List<UserBean> users = new ArrayList<>();
	
	
	private String insertUser = "insert into user (password, email, firstname, lastname)" +
			"values (?, ?, ?, ?)";
	
	private String getUsers = "select * from user";
	
	public UserDAO() {
		
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getUsers);
				ResultSet rs = preparedStatement.executeQuery()) 
			{
				while (rs.next()) {
					
					String password = rs.getString("password");
					String email = rs.getString("email");
					String firstName = rs.getString("firstname");
					String lastName = rs.getString("lastname");
					
					UserBean user = new UserBean(password, email, firstName, lastName);
					users.add(user);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
	}
	
	
	public boolean addUser(String password, String email, String firstName, String lastName) {
		
		
		for (UserBean user : users) {
			if (user.getEmail().equals(email)) {
				return false;
			}
		}
		
		UserBean user = new UserBean(password, email, firstName, lastName);
		users.add(user);
		
		try (Connection conn = Conn.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(insertUser)) 
		{
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public boolean validUser(String email, String password) {
		
		for (UserBean user : users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				return true;
				
			}
		}
		return false;
		
	}
	
	public UserBean getUserByEmail(String email) {
		for (UserBean user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
	
	public int getUserIdByEmail(String email) {
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getUsers);
				ResultSet rs = preparedStatement.executeQuery()) 
		{
			while (rs.next()) {
				
				String email1 = rs.getString("email");
				int uid = rs.getInt("uid");
				if (email1.equals(email)) return uid;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return -1;
		
	}
	
	

}
