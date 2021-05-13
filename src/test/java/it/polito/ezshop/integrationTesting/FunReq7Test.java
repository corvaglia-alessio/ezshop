package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidCustomerNameException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;

public class FunReq7Test {
static EZShop e;
	
	@BeforeAll
	public static void init() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException{
		e = new EZShop();
	}
	
	@BeforeEach
	public void createTransactionsandReturns() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidCustomerNameException, UnauthorizedException {
		e.reset();
		e.createUser("validCashier", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validCashier", "pass");
		/*TODO: create transactions and returns here*/
		
	}
	
	
	@Test 
	public void receiveCashPaymentTest() throws InvalidUsernameException, InvalidPasswordException {
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.receiveCashPayment(1,5.15);});
		
		e.login("validCashier", "pass");
		assertThrows(UnauthorizedException.class, () -> {e.receiveCashPayment(1,5.15);});
		e.logout();
		
		e.login("validAdministrator", "pass");
		
		
	}
	
	@Test 
	public void receiveCreditCardPaymentTest() throws InvalidUsernameException, InvalidPasswordException {
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.receiveCreditCardPayment(1,null);});
		
		e.login("validCashier", "pass");
		assertThrows(UnauthorizedException.class, () -> {e.receiveCreditCardPayment(1,null);});
		e.logout();
		
		e.login("validAdministrator", "pass");
	}
	
	@Test 
	public void returnCashPaymentTest() throws InvalidUsernameException, InvalidPasswordException {
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.returnCashPayment(null);});
		
		e.login("validCashier", "pass");
		assertThrows(UnauthorizedException.class, () -> {e.returnCashPayment(null);});
		e.logout();
		
		e.login("validAdministrator", "pass");
	}
	
	@Test 
	public void returnCreditCardPaymentTest() throws InvalidUsernameException, InvalidPasswordException {
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.returnCreditCardPayment(null,null);});
		
		e.login("validCashier", "pass");
		assertThrows(UnauthorizedException.class, () -> {e.returnCreditCardPayment(null,null);});
		e.logout();
		
		e.login("validAdministrator", "pass");
	}
}
