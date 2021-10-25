package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	List<User> getUsers();
	User getUser(String username);
	void addUser(User user);
	void deleteUser(int id);
	void updateUser(User user);
	User login(String username, String password);
	User register(User user);
	void addCustomer(String username);
}
