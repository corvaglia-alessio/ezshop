package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
/*
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
*/
import it.polito.ezshop.model.FileReaderAndWriter;
import it.polito.ezshop.model.SaleTransactionClass;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.*;


public class FunReq7Test {

	@AfterClass
    public static void clearEzShop(){
        EZShop e = new EZShop();
        e.reset();
    }
	/*
static EZShop e;
	
	@BeforeAll
	public static void init() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException{
		e = new EZShop();
	}
	
	@BeforeEach
	public void createTransactionsandReturns() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidCustomerNameException, UnauthorizedException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidTransactionIdException, InvalidQuantityException, InvalidPaymentException {
		
		e = new EZShop();
		e.reset();
		e.createUser("validCashier", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validAdministrator", "pass");
		//define products
		e.createProductType("product1", "123456789012", 3.00, "p1");
		e.createProductType("product2", "042100005264", 10, "p1");
		e.createProductType("product3", "987654321098", 4.5, "p1");
		e.getProductTypeByBarCode("123456789012").setQuantity(10);
		e.getProductTypeByBarCode("042100005264").setQuantity(10);
		e.getProductTypeByBarCode("987654321098").setQuantity(10);
		
		//sale transactions
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.endSaleTransaction(1);
		e.endSaleTransaction(2);
		e.endSaleTransaction(3);
		e.getSaleTransaction(1).setPrice(30);
		e.getSaleTransaction(2).setPrice(25.34);
		e.getSaleTransaction(3).setPrice(5);

		//return transaction
		int id = e.startSaleTransaction();
		e.addProductToSale(id, "123456789012", 5);
		e.addProductToSale(id, "042100005264", 6);
		e.endSaleTransaction(id);
//		((SaleTransactionClass) e.getSaleTransaction(id)).setState("Paid");
		e.receiveCashPayment(id, e.getSaleTransaction(id).getPrice());
		e.startReturnTransaction(id);
		e.returnProduct(1, "123456789012", 3);  // tot to return for this product: 3*3.00 
		e.returnProduct(1, "042100005264", 2); // tot to return for this product: 2*10 
		e.endReturnTransaction(1, true); // total sum to return for this return transaction: 3*3.00+2*10 = 29 
		
		e.startReturnTransaction(2);  // to test the close condition
		//creditCards
		e.creditCards = FileReaderAndWriter.CreditCardsReader();
		//end BeforeEach
	}
	
	*/
	@Test 
	public void receiveCashPaymentTest() throws InvalidUsernameException, InvalidPasswordException, InvalidTransactionIdException, InvalidPaymentException, UnauthorizedException, InvalidRoleException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validCashier", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validAdministrator", "pass");
		/*define products*/
		e.createProductType("product1", "123456789012", 3.00, "p1");
		e.createProductType("product2", "042100005264", 10, "p1");
		e.createProductType("product3", "987654321098", 4.5, "p1");
		e.getProductTypeByBarCode("123456789012").setQuantity(10);
		e.getProductTypeByBarCode("042100005264").setQuantity(10);
		e.getProductTypeByBarCode("987654321098").setQuantity(10);
		
		/*sale transactions*/
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.endSaleTransaction(1);
		e.endSaleTransaction(2);
		e.endSaleTransaction(3);
		e.getSaleTransaction(1).setPrice(30);
		e.getSaleTransaction(2).setPrice(25.34);
		e.getSaleTransaction(3).setPrice(5);

		/*return transaction */
		int id = e.startSaleTransaction();
		e.addProductToSale(id, "123456789012", 5);
		e.addProductToSale(id, "042100005264", 6);
		e.endSaleTransaction(id);
//		((SaleTransactionClass) e.getSaleTransaction(id)).setState("Paid");
		e.receiveCashPayment(id, e.getSaleTransaction(id).getPrice());
		e.startReturnTransaction(id);
		e.returnProduct(1, "123456789012", 3);  /* tot to return for this product: 3*3.00 */
		e.returnProduct(1, "042100005264", 2); /* tot to return for this product: 2*10 */
		e.endReturnTransaction(1, true); /* total sum to return for this return transaction: 3*3.00+2*10 = 29 */
		
		e.startReturnTransaction(2);  // to test the close condition
		/*creditCards*/
		e.creditCards = FileReaderAndWriter.CreditCardsReader();
		/*end BeforeEach*/
		
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
		assertTrue(e.receiveCashPayment(3,10)==5);
		e.logout();
		e.login("validCashier", "pass");
		assertTrue(e.receiveCashPayment(3,10)==-1);
		e.logout();


	}
	
	@Test 
	public void receiveCreditCardPaymentTest() throws InvalidUsernameException, InvalidPasswordException, InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidRoleException, InvalidPaymentException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validCashier", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validAdministrator", "pass");
		/*define products*/
		e.createProductType("product1", "123456789012", 3.00, "p1");
		e.createProductType("product2", "042100005264", 10, "p1");
		e.createProductType("product3", "987654321098", 4.5, "p1");
		e.getProductTypeByBarCode("123456789012").setQuantity(10);
		e.getProductTypeByBarCode("042100005264").setQuantity(10);
		e.getProductTypeByBarCode("987654321098").setQuantity(10);
		
		/*sale transactions*/
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.endSaleTransaction(1);
		e.endSaleTransaction(2);
		e.endSaleTransaction(3);
		e.getSaleTransaction(1).setPrice(30);
		e.getSaleTransaction(2).setPrice(25.34);
		e.getSaleTransaction(3).setPrice(5);

		/*return transaction */
		int id = e.startSaleTransaction();
		e.addProductToSale(id, "123456789012", 5);
		e.addProductToSale(id, "042100005264", 6);
		e.endSaleTransaction(id);
//		((SaleTransactionClass) e.getSaleTransaction(id)).setState("Paid");
		e.receiveCashPayment(id, e.getSaleTransaction(id).getPrice());
		e.startReturnTransaction(id);
		e.returnProduct(1, "123456789012", 3);  /* tot to return for this product: 3*3.00 */
		e.returnProduct(1, "042100005264", 2); /* tot to return for this product: 2*10 */
		e.endReturnTransaction(1, true); /* total sum to return for this return transaction: 3*3.00+2*10 = 29 */
		
		e.startReturnTransaction(2);  // to test the close condition
		/*creditCards*/
		e.creditCards = FileReaderAndWriter.CreditCardsReader();
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.receiveCreditCardPayment(1,null);});
		
		e.login("validAdministrator", "pass");
		
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCreditCardPayment(null,null);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCreditCardPayment(0,null);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.receiveCreditCardPayment(-1,null);});
		
		assertThrows(InvalidCreditCardException.class, () -> {e.receiveCreditCardPayment(1,null);});
		assertThrows(InvalidCreditCardException.class, () -> {e.receiveCreditCardPayment(1,"");});
		assertThrows(InvalidCreditCardException.class, () -> {e.receiveCreditCardPayment(1,"500293991003009");});

		assertFalse(e.receiveCreditCardPayment(10, "4485370086510891"));
		assertFalse(e.receiveCreditCardPayment(1, "5100293991053009"));
		assertFalse(e.receiveCreditCardPayment(1, "3531174300936064"));
		
		double prevEZShopBalance = e.computeBalance();
		double prevCardBalance = e.creditCards.get("4485370086510891").getBalance();
		assertTrue(e.receiveCreditCardPayment(1, "4485370086510891"));
		assertTrue(((SaleTransactionClass) e.getSaleTransaction(1)).getState().equals("Paid"));
		assertTrue(e.computeBalance()==prevEZShopBalance+e.getSaleTransaction(1).getPrice());
		assertTrue(e.creditCards.get("4485370086510891").getBalance()==prevCardBalance-e.getSaleTransaction(1).getPrice());
		e.logout();
		
		e.login("validManager", "pass");
		assertTrue(e.receiveCreditCardPayment(3,"4485370086510891"));
		e.logout();
		e.login("validCashier", "pass");
		assertFalse(e.receiveCreditCardPayment(3,"4485370086510891"));
		e.logout();
		
	}

	@Test
	public void startReturnTransaction() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validAdministrator", "pass", "Administrator");

		assertThrows(UnauthorizedException.class, () -> {e.startReturnTransaction(null);});

		e.login("validAdministrator", "pass");

		int stID = e.startSaleTransaction();

		assertThrows(InvalidTransactionIdException.class, () -> {e.startReturnTransaction(null);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.startReturnTransaction(0);});

		int id1 = e.startReturnTransaction(stID);
		int id2 = e.startReturnTransaction(stID);

		assertEquals(1, id1);
		assertEquals(2, id2);
	}

	@Test 
	public void returnProductTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidQuantityException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validAdministrator", "pass", "Administrator");

		assertThrows(UnauthorizedException.class, () -> {e.returnProduct(null, null, 0);});

		e.login("validAdministrator", "pass");

		int stID = e.startSaleTransaction();
		int rtID = e.startReturnTransaction(stID);

		e.createProductType("product1", "123456789012", 3.00, "p1");
		e.getProductTypeByBarCode("123456789012").setQuantity(10);
		e.addProductToSale(stID, "123456789012", 2);

		e.endSaleTransaction(stID);

		assertThrows(InvalidTransactionIdException.class, () -> {e.returnProduct(null, "123456789012", 1);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnProduct(0, "123456789012", 1);});
		assertThrows(InvalidProductCodeException.class, () -> {e.returnProduct(rtID, "123456789011", 1);});
		assertThrows(InvalidQuantityException.class, () -> {e.returnProduct(rtID, "123456789012", 0);});

		assertFalse(e.returnProduct(rtID, "123456789012", 10));
		assertTrue(e.returnProduct(rtID, "123456789012", 1));
		
	}

	@Test
	public void endReturnTransactionTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validAdministrator", "pass", "Administrator");

		assertThrows(UnauthorizedException.class, () -> {e.returnProduct(null, null, 0);});

		e.login("validAdministrator", "pass");

		int stID = e.startSaleTransaction();
		int rtID = e.startReturnTransaction(stID);

		assertThrows(InvalidTransactionIdException.class, () -> {e.endReturnTransaction(null, false);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.endReturnTransaction(0, false);});

		assertFalse(e.endReturnTransaction(15000, false));
		assertTrue(e.endReturnTransaction(rtID, false));

	}

	@Test
	public void deleteReturnTransaction() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validAdministrator", "pass", "Administrator");

		assertThrows(UnauthorizedException.class, () -> {e.returnProduct(null, null, 0);});

		e.login("validAdministrator", "pass");

		int stID = e.startSaleTransaction();
		int rtID = e.startReturnTransaction(stID);
		int rtID2 = e.startReturnTransaction(stID);

		assertThrows(InvalidTransactionIdException.class, () -> {e.endReturnTransaction(null, false);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.endReturnTransaction(0, false);});

		e.endReturnTransaction(rtID2, false);
		e.returnCashPayment(rtID2);
		assertFalse(e.deleteReturnTransaction(rtID2));

		assertTrue(e.deleteReturnTransaction(rtID));
		assertFalse(e.deleteReturnTransaction(rtID));
	}
	
	@Test 
	public void returnCashPaymentTest() throws InvalidUsernameException, InvalidPasswordException, InvalidTransactionIdException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidRoleException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidPaymentException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validCashier", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validAdministrator", "pass");
		/*define products*/
		e.createProductType("product1", "123456789012", 3.00, "p1");
		e.createProductType("product2", "042100005264", 10, "p1");
		e.createProductType("product3", "987654321098", 4.5, "p1");
		e.getProductTypeByBarCode("123456789012").setQuantity(10);
		e.getProductTypeByBarCode("042100005264").setQuantity(10);
		e.getProductTypeByBarCode("987654321098").setQuantity(10);
		
		/*sale transactions*/
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.endSaleTransaction(1);
		e.endSaleTransaction(2);
		e.endSaleTransaction(3);
		e.getSaleTransaction(1).setPrice(30);
		e.getSaleTransaction(2).setPrice(25.34);
		e.getSaleTransaction(3).setPrice(5);

		/*return transaction */
		int id = e.startSaleTransaction();
		e.addProductToSale(id, "123456789012", 5);
		e.addProductToSale(id, "042100005264", 6);
		e.endSaleTransaction(id);
//		((SaleTransactionClass) e.getSaleTransaction(id)).setState("Paid");
		e.receiveCashPayment(id, e.getSaleTransaction(id).getPrice());
		e.startReturnTransaction(id);
		e.returnProduct(1, "123456789012", 3);  /* tot to return for this product: 3*3.00 */
		e.returnProduct(1, "042100005264", 2); /* tot to return for this product: 2*10 */
		e.endReturnTransaction(1, true); /* total sum to return for this return transaction: 3*3.00+2*10 = 29 */
		
		e.startReturnTransaction(2);  // to test the close condition
		/*creditCards*/
		e.creditCards = FileReaderAndWriter.CreditCardsReader();
		/*end BeforeEach*/
		
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.returnCashPayment(null);});
		
		e.login("validAdministrator", "pass");
		
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnCashPayment(null);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnCashPayment(0);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnCashPayment(-1);});
		
		assertTrue(e.returnCashPayment(3)==-1);
		
		double prevBalance = e.computeBalance();
		assertTrue(e.returnCashPayment(1)==29.00);
		assertTrue(e.computeBalance()==prevBalance-29);
		e.logout();
		
		e.login("validManager", "pass");
		assertTrue(e.returnCashPayment(1)==-1);
		e.logout();
		e.login("validCashier", "pass");
		assertTrue(e.returnCashPayment(2)==-1);
		e.logout();
	}
	
	@Test 
	public void returnCreditCardPaymentTest() throws InvalidUsernameException, InvalidPasswordException, InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException, InvalidProductCodeException, InvalidQuantityException, InvalidRoleException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidPaymentException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validCashier", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validAdministrator", "pass");
		/*define products*/
		e.createProductType("product1", "123456789012", 3.00, "p1");
		e.createProductType("product2", "042100005264", 10, "p1");
		e.createProductType("product3", "987654321098", 4.5, "p1");
		e.getProductTypeByBarCode("123456789012").setQuantity(10);
		e.getProductTypeByBarCode("042100005264").setQuantity(10);
		e.getProductTypeByBarCode("987654321098").setQuantity(10);
		
		/*sale transactions*/
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.startSaleTransaction();
		e.endSaleTransaction(1);
		e.endSaleTransaction(2);
		e.endSaleTransaction(3);
		e.getSaleTransaction(1).setPrice(30);
		e.getSaleTransaction(2).setPrice(25.34);
		e.getSaleTransaction(3).setPrice(5);

		/*return transaction */
		int id = e.startSaleTransaction();
		e.addProductToSale(id, "123456789012", 5);
		e.addProductToSale(id, "042100005264", 6);
		e.endSaleTransaction(id);
//		((SaleTransactionClass) e.getSaleTransaction(id)).setState("Paid");
		e.receiveCashPayment(id, e.getSaleTransaction(id).getPrice());
		e.startReturnTransaction(id);
		e.returnProduct(1, "123456789012", 3);  /* tot to return for this product: 3*3.00 */
		e.returnProduct(1, "042100005264", 2); /* tot to return for this product: 2*10 */
		e.endReturnTransaction(1, true); /* total sum to return for this return transaction: 3*3.00+2*10 = 29 */
		
		e.startReturnTransaction(2);  // to test the close condition
		/*creditCards*/
		e.creditCards = FileReaderAndWriter.CreditCardsReader();
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.returnCreditCardPayment(null,null);});
		
		e.login("validAdministrator", "pass");
		
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnCreditCardPayment(null,"4485370086510891");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnCreditCardPayment(0,"4485370086510891");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnCreditCardPayment(-1,"4485370086510891");});
		
		
		assertThrows(InvalidCreditCardException.class, () -> {e.returnCreditCardPayment(1,null);});
		assertThrows(InvalidCreditCardException.class, () -> {e.returnCreditCardPayment(1,"");});
		assertThrows(InvalidCreditCardException.class, () -> {e.returnCreditCardPayment(1,"500293991003009");});
		
		assertTrue(e.returnCreditCardPayment(3,"4485370086510891")==-1);
		
		double prevEZShopBalance = e.computeBalance();
		double prevCardBalance = e.creditCards.get("4485370086510891").getBalance();
		assertTrue(e.returnCreditCardPayment(1, "4485370086510891")==29.00);
		assertTrue(e.computeBalance()==prevEZShopBalance-29.00);
		assertTrue(e.creditCards.get("4485370086510891").getBalance()==prevCardBalance+29.00);
		e.logout();

		e.login("validManager", "pass");
		assertTrue(e.returnCreditCardPayment(1,"4485370086510891")==-1);
		e.logout();
		e.login("validCashier", "pass");
		assertTrue(e.returnCreditCardPayment(2,"4485370086510891")==-1);
		e.logout();
		

	}
}
