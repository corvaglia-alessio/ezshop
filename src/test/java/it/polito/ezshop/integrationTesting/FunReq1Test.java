package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.data.User;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUserIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class FunReq1Test {

	@AfterClass
    public static void clearEzShop(){
        EZShop e = new EZShop();
        e.reset();
    }

	@Test 
	public void testCreateUser() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		
		EZShop e = new EZShop();
		e.reset();

		assertThrows(InvalidUsernameException.class, () -> { e.createUser(null, "password", "Cashier");});
		
		assertThrows(InvalidUsernameException.class, () -> {e.createUser("", "password", "Cashier");});

		assertThrows(InvalidPasswordException.class, () -> {e.createUser("user", "", "Cashier");});

		assertThrows(InvalidRoleException.class, () -> {e.createUser("user", "password", "StrangeRole");});

		assertThrows(InvalidRoleException.class, () -> {e.createUser("user", "password", null);});

		int userid = e.createUser("username", "password", "Cashier");
		assertEquals(1, userid);

		int replicateduserid = e.createUser("username", "password", "Cashier");
		assertEquals(-1, replicateduserid);
	}

	@Test
	public void testDeleteUser() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidUserIdException, UnauthorizedException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");
		
		assertThrows(UnauthorizedException.class, () -> {e.deleteUser(1);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.deleteUser(1);});

		e.logout();
		e.login("adminuser", "password");

		assertThrows(InvalidUserIdException.class, () -> {e.deleteUser(null);});
		assertThrows(InvalidUserIdException.class, () -> {e.deleteUser(-2);});
		
		boolean result = e.deleteUser(4);
		assertFalse(result);

		result = e.deleteUser(2);
		assertTrue(result);
	}

	@Test
	public void testGetAllUsers() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidUserIdException{

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.getAllUsers();});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.getAllUsers();});

		e.logout();
		e.login("adminuser", "password");

		List<User> users = e.getAllUsers();

		assertEquals(2, users.size());

		e.deleteUser(1);
		users = e.getAllUsers();
		assertEquals(1, users.size());

		e.deleteUser(2);
		users = e.getAllUsers();
		assertTrue(users.isEmpty());
	}

	@Test
	public void testGetUser() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidUserIdException{

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.getUser(1);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.getUser(1);});

		e.logout();
		e.login("adminuser", "password");

		assertThrows(InvalidUserIdException.class, () -> {e.getUser(-2);});
		assertThrows(InvalidUserIdException.class, () -> {e.getUser(null);});

		User u = e.getUser(1);
		assertEquals("adminuser", u.getUsername());

		u = e.getUser(3);
		assertEquals(null, u);
	}

	@Test
	public void testUpdateUserRights() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidUserIdException{

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.updateUserRights(2, "ShopManager");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.updateUserRights(2, "ShopManager");});

		e.logout();
		e.login("adminuser", "password");

		assertThrows(InvalidUserIdException.class, () -> {e.updateUserRights(-4, "ShopManager");});
		assertThrows(InvalidUserIdException.class, () -> {e.updateUserRights(null, "ShopManager");});

		assertThrows(InvalidRoleException.class, () -> {e.updateUserRights(2, "WrongCashier");});
		assertThrows(InvalidRoleException.class, () -> {e.updateUserRights(2, null);});

		boolean res = e.updateUserRights(3, "ShopManager");
		assertFalse(res);

		res = e.updateUserRights(2, "ShopManager");
		assertTrue(res);
		assertEquals("ShopManager", e.getUser(2).getRole());
	}

	@Test
	public void testLogin() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidUserIdException{

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");


		assertThrows(InvalidUsernameException.class, () -> { e.login(null, "password");});
		assertThrows(InvalidUsernameException.class, () -> { e.login("", "password");});
		assertThrows(InvalidPasswordException.class, () -> { e.login("adminuser", "");});
		assertThrows(InvalidPasswordException.class, () -> { e.login("adminuser", null);});

		User u = e.login("adminuser", "wrongpassword");
		assertNull(u);

		u = e.login("wronguser", "password");
		assertNull(u);

		u = e.login("adminuser", "password");
		assertNotNull(u);
		assertEquals("adminuser", u.getUsername());
	}

	@Test
	public void testLogout() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidUserIdException{

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");

		boolean res = e.logout();
		assertFalse(res);
		
		e.login("adminuser", "password");
		res = e.logout();
		assertTrue(res);
	}
}
