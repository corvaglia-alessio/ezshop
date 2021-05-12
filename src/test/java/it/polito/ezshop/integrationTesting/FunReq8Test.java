package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class FunReq8Test {

	@Test 
	public void testComputeBalance() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.computeBalance();});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.computeBalance();});

		e.logout();
		e.login("adminuser", "password");

		double balance = e.computeBalance();

		assertEquals(0D, balance, 0.0001);
	}

	@Test
	public void testRecordBalanceUpdate() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.recordBalanceUpdate(1);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.recordBalanceUpdate(1);});

		e.logout();
		e.login("adminuser", "password");

		boolean result = e.recordBalanceUpdate(-5);
		assertFalse(result);

		result = e.recordBalanceUpdate(5);
		assertTrue(result);
		assertEquals(1, e.getCreditsAndDebits(null, null).size());
		assertEquals("CREDIT", e.getCreditsAndDebits(null, null).get(0).getType());
		assertEquals(5, e.getCreditsAndDebits(null, null).get(0).getMoney(), 0.0001);
		assertEquals(LocalDate.now(), e.getCreditsAndDebits(null, null).get(0).getDate());
		assertEquals(5, e.computeBalance(), 0.00001);

		result = e.recordBalanceUpdate(-3);
		assertTrue(result);
		assertEquals(2, e.getCreditsAndDebits(null, null).size());
		assertEquals("DEBIT", e.getCreditsAndDebits(null, null).get(1).getType());
		assertEquals(2, e.getCreditsAndDebits(null, null).get(1).getBalanceId());
		assertEquals(2, e.computeBalance(), 0.00001);

		result = e.recordBalanceUpdate(0);
		assertTrue(result);
		assertEquals(3, e.getCreditsAndDebits(null, null).size());
	}

	@Test
	public void testGetCreditsAndDebits() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.getCreditsAndDebits(null, null);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.getCreditsAndDebits(null, null);});

		e.logout();
		e.login("adminuser", "password");

		e.recordBalanceUpdate(-5);
		assertTrue(e.getCreditsAndDebits(null, null).isEmpty());

		e.recordBalanceUpdate(10);
		e.recordBalanceUpdate(-2);

		e.getCreditsAndDebits(null, null).get(0).setDate(LocalDate.now().plusDays(4));

		//test without boundaries
		assertEquals(2, e.getCreditsAndDebits(null, null).size());

		//test with from boundary
		assertEquals(2, e.getCreditsAndDebits(LocalDate.now(), null).size());

		//test with to boundary
		assertEquals(1, e.getCreditsAndDebits(null, LocalDate.now().plusDays(2)).size());

		//test with both boundaries
		assertEquals(0, e.getCreditsAndDebits(LocalDate.now().plusDays(2), LocalDate.now().plusDays(3)).size());

		//test with inverted boundaries, the method should understand this and invert from and to
		assertEquals(2, e.getCreditsAndDebits(LocalDate.now().plusDays(5), LocalDate.now()).size());

	}
}