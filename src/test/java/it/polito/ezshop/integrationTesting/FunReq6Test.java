package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.data.SaleTransaction;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;
public class FunReq6Test {

	@Test 
	public void testStartSaleTransaction() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("cashieruser", "pwd", "Cashier");
		
		assertThrows(UnauthorizedException.class, () -> {e.startSaleTransaction();});

		e.login("cashieruser", "pwd");

		int id = e.startSaleTransaction();
		assertEquals(1, id);

		id = e.startSaleTransaction();
		assertEquals(2, id);
	}

	@Test
	public void testComputePointsForSale() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("cashieruser", "pwd", "Cashier");
		
		assertThrows(UnauthorizedException.class, () -> {e.computePointsForSale(1);});

		e.login("cashieruser", "pwd");

		assertThrows(InvalidTransactionIdException.class, () -> {e.computePointsForSale(-1);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.computePointsForSale(null);});

		int points = e.computePointsForSale(1);

		assertEquals(-1, points);

		e.startSaleTransaction();
		e.endSaleTransaction(1);
		SaleTransaction s = e.getSaleTransaction(1);
		
		points = e.computePointsForSale(1);
		assertEquals(0, points);

		s.setPrice(8.99);
		points = e.computePointsForSale(1);
		assertEquals(0, points);

		s.setPrice(19.99);
		points = e.computePointsForSale(1);
		assertEquals(1, points);

		s.setPrice(20.00);
		points = e.computePointsForSale(1);
		assertEquals(2, points);
	}

	@Test
	public void testGetSaleTransaction() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("cashieruser", "pwd", "Cashier");
		
		assertThrows(UnauthorizedException.class, () -> {e.getSaleTransaction(1);});

		e.login("cashieruser", "pwd");

		assertThrows(InvalidTransactionIdException.class, () -> {e.computePointsForSale(-1);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.computePointsForSale(null);});

		e.startSaleTransaction();

		assertNull(e.getSaleTransaction(1));
		assertNull(e.getSaleTransaction(2));

		e.endSaleTransaction(1);

		assertNotNull(e.getSaleTransaction(1));
		assertEquals(1, (int) e.getSaleTransaction(1).getTicketNumber());
	}
}
