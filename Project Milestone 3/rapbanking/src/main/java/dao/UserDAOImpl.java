package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.postgresql.util.PSQLException;

import model.User;
import utilities.ConnectionUtility;

public class UserDAOImpl implements UserDAO{

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM USERS";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public List<User> getCustomers() {
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM USERS where UserRole='customer'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public void addUser(User user) {
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "insert into users values (?,?,?,?,?,now());";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPass());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getUserRole());
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteUser(int id) {
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "DELETE FROM USERS WHERE UserId=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public User login(String username, String password) {
		User user = null;
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM USERS where Username=? and Pass=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			try {
				rs.next();
				user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
			} catch (PSQLException e) {
				user = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}

	

	public User getUser(String username) {
		User user = null;
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "SELECT * FROM USERS where Username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			try {
				rs.next();
				user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
			} catch (PSQLException e) {
				user = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return user;
	}

	public User register(User user) {
		addUser(user);
		addCustomer(user.getUsername());
		return null;
	}

	public void addCustomer(String username) {
		try {
			Connection conn = ConnectionUtility.getConnection();
			String sql = "insert into customer values (?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	
}
