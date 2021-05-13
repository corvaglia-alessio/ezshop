package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.data.SaleTransaction;
import it.polito.ezshop.exceptions.InvalidDiscountRateException;
import it.polito.ezshop.exceptions.InvalidLocationException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPaymentException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
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

	@Test
	public void testAddProductToSale() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.addProductToSale(1, "1", 1);});

		e.login("adminuser", "pwd");

		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSale(null, "1", 1);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSale(-1, "1", 1);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSale(0, "1", 1);});

		assertThrows(InvalidQuantityException.class, () -> {e.addProductToSale(1, "1", -1);});

		assertThrows(InvalidProductCodeException.class, () -> {e.addProductToSale(1, "1", 1);});
		assertThrows(InvalidProductCodeException.class, () -> {e.addProductToSale(1, null, 1);});
		assertThrows(InvalidProductCodeException.class, () -> {e.addProductToSale(1, "", 1);});

		e.startSaleTransaction();
		e.endSaleTransaction(1);

		assertFalse(e.addProductToSale(1, "123456789012", 1));

		e.createProductType("product", "123456789012", 12, "product");
		e.updatePosition(1, "1-f-1");
		e.updateQuantity(1, 10);

		e.startSaleTransaction();
		assertFalse(e.addProductToSale(2, "8076800105704", 1));
		assertFalse(e.addProductToSale(2, "123456789012", 20));

		assertTrue(e.addProductToSale(2, "123456789012", 2));

		assertEquals(8, (int) e.getProductTypeByBarCode("123456789012").getQuantity());

		assertTrue(e.addProductToSale(2, "123456789012", 1));

		assertEquals(7, (int) e.getProductTypeByBarCode("123456789012").getQuantity());

		e.endSaleTransaction(2);

		assertEquals(36, e.getSaleTransaction(2).getPrice(), 0.0001);
		assertEquals(1, e.getSaleTransaction(2).getEntries().size());
	}

	@Test
	public void testApplyDiscountRateToSale() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidDiscountRateException, InvalidPaymentException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.applyDiscountRateToSale(1, 0.5);});

		e.login("adminuser", "pwd");

		assertThrows(InvalidTransactionIdException.class, () -> {e.applyDiscountRateToSale(null, 0.5);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.applyDiscountRateToSale(-1, 0.5);});
		assertThrows(InvalidDiscountRateException.class, () -> {e.applyDiscountRateToSale(1, -0.1);});
		assertThrows(InvalidDiscountRateException.class, () -> {e.applyDiscountRateToSale(1, 1);});
		assertThrows(InvalidDiscountRateException.class, () -> {e.applyDiscountRateToSale(1, 1.2);});
		

		e.startSaleTransaction();
		e.endSaleTransaction(1);
		e.getSaleTransaction(1).setPrice(10.50);
		assertTrue(e.applyDiscountRateToSale(1, 0.5));
		assertEquals(5.25, e.getSaleTransaction(1).getPrice(), 0.01);
		assertEquals(0.5, e.getSaleTransaction(1).getDiscountRate(), 0.01);
		e.receiveCashPayment(1, 10);
		assertFalse(e.applyDiscountRateToSale(1, 0.2));
	}
}
