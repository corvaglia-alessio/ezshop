package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import it.polito.ezshop.model.FileReaderAndWriter;
import it.polito.ezshop.model.SaleTransactionClass;
import it.polito.ezshop.model.CreditCardClass;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.*;


public class FunReq7Test {
static EZShop e;
	
	@BeforeAll
	public static void init() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException{
		e = new EZShop();
	}
	
	@BeforeEach
	public void createTransactionsandReturns() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidCustomerNameException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidTransactionIdException, InvalidQuantityException {
		e.reset();
		e.createUser("validCashier", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validAdministrator", "pass");
		/*define products*/
/*		e.createProductType("product1", "123456789012", 3.00, "p1");
		e.createProductType("product2", "042100005264", 10, "p1");
		e.createProductType("product3", "987654321098", 4.5, "p1");
		e.getProductTypeByBarCode("123456789012").setQuantity(10);
		e.getProductTypeByBarCode("042100005264").setQuantity(10);
		e.getProductTypeByBarCode("987654321098").setQuantity(10);*/
		/*transactions and returns*/
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.startSaleTransaction();

		e.endSaleTransaction(1);
		e.endSaleTransaction(2);
		e.endSaleTransaction(3);

		e.getSaleTransaction(1).setPrice(30);
		e.getSaleTransaction(2).setPrice(25.34);
		e.getSaleTransaction(3).setPrice(5);
/*
		e.sales.get(1).setState("Closed");
		e.sales.get(2).setPrice(25.34);
		e.sales.get(2).setState("Closed");
		e.sales.get(3).setPrice(40.9);
		e.sales.get(3).setState("Closed");*/
		e.startReturnTransaction(1);
		e.startReturnTransaction(2);
		e.startReturnTransaction(3);
		/*creditCards*/
		HashMap <String,CreditCardClass> ccs = FileReaderAndWriter.CreditCardsReader();

	}
	
	
	@Test 
	public void receiveCashPaymentTest() throws InvalidUsernameException, InvalidPasswordException, InvalidTransactionIdException, InvalidPaymentException, UnauthorizedException {
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.receiveCashPayment(1,5.15);});
		
		
		e.login("validAdministrator", "pass");
		
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCashPayment(null,5.15);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCashPayment(0,5.15);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCashPayment(-1,5.15);});
		
		assertThrows(InvalidPaymentException.class, () -> {e.receiveCashPayment(1,0);});
		assertThrows(InvalidPaymentException.class, () -> {e.receiveCashPayment(1,-1);});
		
		assertTrue(e.receiveCashPayment(10, 15)==-1);
		assertTrue(e.receiveCashPayment(1, 3.14)==-1);
		
		double prevBalance = e.computeBalance();
		assertTrue(e.receiveCashPayment(1, 30)==0);
		assertTrue(((SaleTransactionClass) e.getSaleTransaction(1)).getState().equals("Paid"));
		assertTrue(e.computeBalance()==prevBalance+e.getSaleTransaction(1).getPrice());

		prevBalance = e.computeBalance();
		assertTrue(e.receiveCashPayment(2, 30)==30-25.34);
		assertTrue(((SaleTransactionClass) e.getSaleTransaction(1)).getState().equals("Paid"));
		assertTrue(e.computeBalance()==prevBalance+e.getSaleTransaction(2).getPrice());
		e.logout();
		
		
		e.login("validManager", "pass");
		e.receiveCashPayment(3,10);
		e.logout();
		e.login("validCashier", "pass");
		assertTrue(e.receiveCashPayment(3,10)==-1);
		e.logout();


	}
	
	@Test 
	public void receiveCreditCardPaymentTest() throws InvalidUsernameException, InvalidPasswordException {
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.receiveCreditCardPayment(1,null);});
		
		e.login("validCashier", "pass");
		assertThrows(UnauthorizedException.class, () -> {e.receiveCreditCardPayment(1,null);});
		e.logout();
		
		e.login("validAdministrator", "pass");
		
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCreditCardPayment(null,null);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCreditCardPayment(0,null);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCreditCardPayment(-1,null);});
		
		
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
