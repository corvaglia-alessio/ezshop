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
import it.polito.ezshop.model.FileReaderAndWriter;
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

	@Test
	public void testDeleteProductFromSale() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidDiscountRateException, InvalidPaymentException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.deleteProductFromSale(1, "123456789012", 2);}); //try without being logged in

		e.login("adminuser", "pwd");

		//try with invalid transactions id
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSale(-1, "123456789012", 2);});
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSale(null, "123456789012", 2);});

		//try with invalid quantity
		assertThrows(InvalidQuantityException.class, () -> {e.deleteProductFromSale(1, "123456789012", -1);});

		//try with invalid barcodes
		assertThrows(InvalidProductCodeException.class, () -> {e.deleteProductFromSale(1, "", 2);});
		assertThrows(InvalidProductCodeException.class, () -> {e.deleteProductFromSale(1, null, 2);});
		assertThrows(InvalidProductCodeException.class, () -> {e.deleteProductFromSale(1, "12", 2);});
		
		e.startSaleTransaction();
		e.endSaleTransaction(1);

		//try to delete products from a closed sale
		assertFalse(e.deleteProductFromSale(1, "123456789012", 2));

		e.startSaleTransaction();

		//try to delete product non present in the inventory
		assertFalse(e.deleteProductFromSale(2, "123456789012", 2));

		e.createProductType("product", "123456789012", 10, "note");
		e.updatePosition(1, "1-f-1");
		e.updateQuantity(1, 20);
		e.createProductType("product2", "8076800105704", 10, "note");
		e.updatePosition(2, "1-g-1");
		e.updateQuantity(2, 20);

		
		//try to remove products from an empty sale
		assertFalse(e.deleteProductFromSale(2, "8076800105704", 2));

		e.addProductToSale(2, "123456789012", 4);

		//try to delete products that are not in the ticket
		assertFalse(e.deleteProductFromSale(2, "8076800105704", 2));

		//try to remove more than the added product quantity
		assertFalse(e.deleteProductFromSale(2, "123456789012", 5));

		assertTrue(e.deleteProductFromSale(2, "123456789012", 2)); //remove 2, actual = 2

		e.endSaleTransaction(2);

		SaleTransaction s = e.getSaleTransaction(2);

		assertEquals(20, s.getPrice(), 0.01);
		assertEquals(1, s.getEntries().size());
		assertEquals(2, s.getEntries().get(0).getAmount());
		assertEquals(18, (int) e.getProductTypeByBarCode("123456789012").getQuantity());

		e.startSaleTransaction();
		e.addProductToSale(3, "123456789012", 2);
		assertTrue(e.deleteProductFromSale(3, "123456789012", 2));
		e.endSaleTransaction(3);
		s = e.getSaleTransaction(3);

		assertEquals(0, s.getPrice(), 0.01);
		assertEquals(0, s.getEntries().size());
		assertEquals(18, (int) e.getProductTypeByBarCode("123456789012").getQuantity());
	}

	@Test
	public void testDeleteSaleTransaction() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidDiscountRateException, InvalidPaymentException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.deleteSaleTransaction(1);}); //user not logged in

		e.login("adminuser", "pwd");

		//invalid transactions
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteSaleTransaction(null);}); 
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteSaleTransaction(-1);});

		assertFalse(e.deleteSaleTransaction(1)); //non existing transaction

		e.startSaleTransaction();
		e.endSaleTransaction(1);
		e.receiveCashPayment(1, 10);

		assertFalse(e.deleteSaleTransaction(1)); //already paid transaction

		e.createProductType("product", "123456789012", 10, "note");
		e.updatePosition(1, "1-f-1");
		e.updateQuantity(1, 20);
		e.createProductType("product2", "8076800105704", 10, "note");
		e.updatePosition(2, "1-g-1");
		e.updateQuantity(2, 10);

		e.startSaleTransaction();
		e.addProductToSale(2, "123456789012", 4);
		e.addProductToSale(2, "8076800105704", 4);

		e.endSaleTransaction(2);
		assertTrue(e.deleteSaleTransaction(2));

		assertEquals(20, (int) e.getProductTypeByBarCode("123456789012").getQuantity());
		assertEquals(10, (int) e.getProductTypeByBarCode("8076800105704").getQuantity());

		assertNull(e.getSaleTransaction(2));

		assertEquals(0, FileReaderAndWriter.ticketEntriesReader().size());
		assertEquals(1, FileReaderAndWriter.SaleTransactionsReader().size());
		assertEquals(20, (int) FileReaderAndWriter.InventoryReader().get(1).getQuantity());
	}

	@Test
	public void testEndSaleTransaction() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidDiscountRateException, InvalidPaymentException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.endSaleTransaction(1);}); //user not logged in

		e.login("adminuser", "pwd");

		//invalid transactions
		assertThrows(InvalidTransactionIdException.class, () -> {e.endSaleTransaction(null);}); 
		assertThrows(InvalidTransactionIdException.class, () -> {e.endSaleTransaction(-1);});

		assertFalse(e.deleteSaleTransaction(1)); //non existing transaction

		e.startSaleTransaction();
		assertTrue(e.endSaleTransaction(1));
		assertFalse(e.endSaleTransaction(1)); //duplicated closing

		e.createProductType("product", "123456789012", 10, "note");
		e.updatePosition(1, "1-f-1");
		e.updateQuantity(1, 20);
		e.createProductType("product2", "8076800105704", 10, "note");
		e.updatePosition(2, "1-g-1");
		e.updateQuantity(2, 10);

		e.startSaleTransaction();
		e.addProductToSale(2, "123456789012", 4);
		e.addProductToSale(2, "8076800105704", 4);
		e.endSaleTransaction(2);

		assertEquals(2, FileReaderAndWriter.ticketEntriesReader().size());
		assertEquals(2, FileReaderAndWriter.SaleTransactionsReader().size());
		assertEquals(16, (int) FileReaderAndWriter.InventoryReader().get(1).getQuantity());
		assertEquals(6, (int) FileReaderAndWriter.InventoryReader().get(2).getQuantity());
	}

	@Test
	public void testApplyDiscountRateToProduct() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidDiscountRateException, InvalidPaymentException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.applyDiscountRateToProduct(1, "123456789012", 0.1);}); //user not logged in

		e.login("adminuser", "pwd");

		assertThrows(InvalidDiscountRateException.class, () -> {e.applyDiscountRateToProduct(1, "123456789012", -0.1);});
		assertThrows(InvalidDiscountRateException.class, () -> {e.applyDiscountRateToProduct(1, "123456789012", 1.2);});
		assertThrows(InvalidDiscountRateException.class, () -> {e.applyDiscountRateToProduct(1, "123456789012", 1);}); 

		assertThrows(InvalidProductCodeException.class, () -> {e.applyDiscountRateToProduct(1, "", 0.5);}); 
		assertThrows(InvalidProductCodeException.class, () -> {e.applyDiscountRateToProduct(1, null, 0.5);}); 
		assertThrows(InvalidProductCodeException.class, () -> {e.applyDiscountRateToProduct(1, "12", 0.5);}); 

		assertThrows(InvalidTransactionIdException.class, () -> {e.applyDiscountRateToProduct(-1, "123456789012", 0.5);}); 
		assertThrows(InvalidTransactionIdException.class, () -> {e.applyDiscountRateToProduct(null, "123456789012", 0.5);});

		e.startSaleTransaction();
		e.endSaleTransaction(1);

		assertFalse(e.applyDiscountRateToProduct(1, "123456789012", 0.5));

		e.createProductType("product", "123456789012", 10, "note");
		e.updatePosition(1, "1-f-1");
		e.updateQuantity(1, 20);

		e.startSaleTransaction();
		e.addProductToSale(2, "123456789012", 2);
		assertTrue(e.applyDiscountRateToProduct(2, "123456789012", 0.5));
		e.endSaleTransaction(2);

		SaleTransaction s = e.getSaleTransaction(2);

		assertEquals(10, s.getPrice(), 0.01);
		assertEquals(0.5, s.getEntries().get(0).getDiscountRate(), 0.01);
	}
}
