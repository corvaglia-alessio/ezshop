package it.polito.ezshop.integrationTesting;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import it.polito.ezshop.data.*;
import it.polito.ezshop.exceptions.*;

public class FunReq4Test {

	@AfterClass
    public static void clearEzShop(){
        EZShop e = new EZShop();
        e.reset();
    }

	@Test 
	public void testUpdateQuantity()  throws InvalidProductIdException, InvalidProductIdException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidLocationException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.updateQuantity(1, 1);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.updateQuantity(1, 1);});

		e.logout();
		e.login("adminuser", "password");

		assertThrows(InvalidProductIdException.class, () -> {e.updateQuantity(0, 1);});

		e.createProductType("apple", "628176957012", 2.0, "red apples");
		e.updateQuantity(1, 10);

		assertFalse(e.updateQuantity(404, 1));
		assertFalse(e.updateQuantity(1, -100));
		assertFalse(e.updateQuantity(1, 2));
		e.updatePosition(1, "1-a-0");
		assertTrue(e.updateQuantity(1, 2));
	}
	
	@Test 
	public void testUpdatePosition()  throws InvalidProductIdException, InvalidLocationException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException  {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.updatePosition(1, "1-a-0");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.updatePosition(1, "1-a-0");});

		e.logout();
		e.login("adminuser", "password");

		assertThrows(InvalidProductIdException.class, () -> {e.updatePosition(0, "1-a-0");});
		assertThrows(InvalidLocationException.class, () -> {e.updatePosition(1, "strange position");});

		e.createProductType("apple", "628176957012", 2.0, "red apples");
		e.createProductType("banana", "628176957074", 2.0, "yellow banana");


		assertFalse(e.updatePosition(404, "1-a-0"));
		assertTrue(e.updatePosition(1, "1-a-0"));
		assertFalse(e.updatePosition(2, "1-a-0"));
		assertTrue(e.updatePosition(2, ""));
	}


	@Test 
	public void testIssueOrder()  throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException, InvalidUsernameException, InvalidPasswordException, InvalidProductDescriptionException, InvalidRoleException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.issueOrder("628176957012", 100, 2.0);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.issueOrder("628176957012", 100, 2.0);});

		e.logout();
		e.login("adminuser", "password");

		e.createProductType("apple", "628176957012", 2.0, "green apples");

		assertThrows(InvalidQuantityException.class, () -> {e.issueOrder("628176957012", -100, 2.0);});
		assertThrows(InvalidPricePerUnitException.class, () -> {e.issueOrder("628176957012", 100, -2.0);});
		assertThrows(InvalidProductCodeException.class, () -> {e.issueOrder("6281769570", 100, 2.0);});
		assertThrows(InvalidProductCodeException.class, () -> {e.issueOrder(null, 100, 2.0);});
		assertThrows(InvalidProductCodeException.class, () -> {e.issueOrder("", 100, 2.0);});

		int right_order = e.issueOrder("628176957012", 100, 2.0);
		int wrong_order = e.issueOrder("628176957074", 100, 2.0);

		assertEquals(-1, wrong_order);
		assertTrue(right_order > 0);
	}

	@Test 
	public void testPayOrderFor() throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException, InvalidProductDescriptionException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.payOrderFor("628176957012", 100, 2.0);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.payOrderFor("628176957012", 100, 2.0);});

		e.logout();
		e.login("adminuser", "password");

		e.createProductType("apple", "628176957012", 2.0, "green apples");
		e.recordBalanceUpdate(500.0);

		assertThrows(InvalidQuantityException.class, () -> {e.payOrderFor("628176957012", -100, 2.0);});
		assertThrows(InvalidPricePerUnitException.class, () -> {e.payOrderFor("628176957012", 100, -2.0);});
		assertThrows(InvalidProductCodeException.class, () -> {e.payOrderFor("6281769570", 100, 2.0);});
		assertThrows(InvalidProductCodeException.class, () -> {e.payOrderFor(null, 100, 2.0);});
		assertThrows(InvalidProductCodeException.class, () -> {e.payOrderFor("", 100, 2.0);});

		int not_enough_money_order = e.payOrderFor("628176957012", 100, 100.0);
		int invalid_product_order = e.payOrderFor("628176957074", 100, 2.0);

		assertEquals(-1, not_enough_money_order);
		assertEquals(-1, invalid_product_order);

		int right_order = e.payOrderFor("628176957012", 100, 2.0);

		assertTrue(right_order > 0);
	}

	@Test 
	public void testPayOrder() throws InvalidOrderIdException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, InvalidProductDescriptionException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.payOrder(1);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.payOrder(1);});

		e.logout();
		e.login("adminuser", "password");

		e.createProductType("apple", "628176957012", 2.0, "green apples");
		int order_id = e.issueOrder("628176957012", 100, 2.0);
		e.recordBalanceUpdate(500.0);
		int payed_order_id = e.payOrderFor("628176957012", 100, 2.0);

		assertThrows(InvalidOrderIdException.class, () -> {e.payOrder(-1);});

		assertFalse(e.payOrder(404));
		assertFalse(e.payOrder(payed_order_id));

		assertTrue(e.payOrder(order_id));
	}

	@Test 
	public void testRecordOrderArrival() throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException, InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, InvalidProductDescriptionException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidProductIdException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.recordOrderArrival(1);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.recordOrderArrival(1);});

		e.logout();
		e.login("adminuser", "password");

		e.createProductType("apple", "628176957012", 2.0, "green apples");
		e.createProductType("banana", "628176957074", 2.0, "yellow bananas");
		e.updatePosition(1, "1-a-0");
		int issued_order = e.issueOrder("628176957012", 100, 2.0);
		
		e.recordBalanceUpdate(500.0);
		int payed_order_id = e.payOrderFor("628176957012", 100, 2.0);
		int order_no_location_id = e.payOrderFor("628176957074", 1, 2.0);

		assertThrows(InvalidOrderIdException.class, () -> {e.recordOrderArrival(-1);});
		assertThrows(InvalidLocationException.class, () -> {e.recordOrderArrival(order_no_location_id);});

		assertFalse(e.recordOrderArrival(404));
		assertFalse(e.recordOrderArrival(issued_order));
		assertTrue(e.recordOrderArrival(payed_order_id));
		assertFalse(e.recordOrderArrival(payed_order_id));
	}

	@Test
	public void testGetAllOrders() throws UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, InvalidProductDescriptionException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.recordOrderArrival(1);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.recordOrderArrival(1);});

		e.logout();
		e.login("adminuser", "password");
	
		e.createProductType("apple", "628176957012", 2.0, "green apples");
		e.recordBalanceUpdate(500.0);

		e.issueOrder("628176957012", 100, 2.0);
		e.payOrderFor("628176957012", 100, 2.0);

		List<Order> orders = e.getAllOrders();
	 
		assertEquals(2, orders.size());
	}
}
