package model;

import java.util.Date;

public class User {
	private String Username;
	private String Pass;
	private String LastName;
	private String FirstName;
	private String UserRole;
	private Date DayBegin;
	
	
	
	public User(String username, String pass, String lastName, String firstName, String userRole) {
		Username = username;
		Pass = pass;
		LastName = lastName;
		FirstName = firstName;
		UserRole = userRole;
	}
	
	
	public User(String username, String pass, String lastName, String firstName, String userRole, Date dayBegin) {
		super();
		Username = username;
		Pass = pass;
		LastName = lastName;
		FirstName = firstName;
		UserRole = userRole;
		DayBegin = dayBegin;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getUserRole() {
		return UserRole;
	}
	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	public Date getDayBegin() {
		return DayBegin;
	}
	public void setDayBegin(Date dayBegin) {
		DayBegin = dayBegin;
	}
	@Override
	public String toString() {
		return "User [Username=" + Username + ", Pass=" + Pass + ", LastName=" + LastName + ", FirstName=" + FirstName
				+ ", UserRole=" + UserRole + ", DayBegin=" + DayBegin + "]";
	}
	
	
}
