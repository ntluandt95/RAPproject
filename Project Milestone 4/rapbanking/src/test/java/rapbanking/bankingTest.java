package rapbanking;
import org.junit.Test;

import com.revature.banking.dao.UserDAOImpl;
import com.revature.banking.model.User;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class bankingTest {
	static UserDAOImpl userDAOImpl = new UserDAOImpl();
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testLoginFail() {
		User user = userDAOImpl.login("asadasdsadasdsads", "asdasdsadasdasdsadsadsa");
		assertTrue(user==null);
	}

	@Test
	public void testLoginSuccess() {
		User user = userDAOImpl.login("employee1", "12345");
		assertTrue(user.getUsername().equals("employee1"));
	}
	@Test
	public void testGetUser() {
		User user = userDAOImpl.getUser("customer1");
		assertTrue(user.getUsername().equals("customer1"));
	}
}
