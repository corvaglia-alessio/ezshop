package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;

import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.InvalidLocationException;
import it.polito.ezshop.exceptions.InvalidOrderIdException;
import it.polito.ezshop.exceptions.InvalidPasswordException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.exceptions.InvalidQuantityException;
import it.polito.ezshop.exceptions.InvalidRFIDException;
import it.polito.ezshop.exceptions.InvalidRoleException;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;
import it.polito.ezshop.exceptions.InvalidUsernameException;
import it.polito.ezshop.exceptions.UnauthorizedException;
import it.polito.ezshop.model.Product;

public class Change2Test {

	@AfterClass
    public static void clearEzShop(){
        EZShop e = new EZShop();
        e.reset();
    }
    
    @Test
	public void testAddProductToSaleRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.addProductToSaleRFID(1, "000000000001");});

		e.login("adminuser", "pwd");

		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSaleRFID(null, "000000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSaleRFID(-1, "000000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSaleRFID(0, "000000000001");});

		assertThrows(InvalidRFIDException.class, () -> {e.addProductToSaleRFID(1, null);});
        assertThrows(InvalidRFIDException.class, () -> {e.addProductToSaleRFID(1, "");});
        assertThrows(InvalidRFIDException.class, () -> {e.addProductToSaleRFID(1, "001");});

        assertFalse(e.addProductToSaleRFID(1, "000000000001")); //non existing transaction

		e.startSaleTransaction();
		e.endSaleTransaction(1);

		assertFalse(e.addProductToSaleRFID(1, "000000000001")); //already closed

		e.createProductType("product", "123456789012", 12, "product");
		e.updatePosition(1, "1-f-1");
        e.updateQuantity(1, 2);
		e.RFIDs.put("000000000001", new Product("000000000001", 1));
		e.RFIDs.put("000000000002", new Product("000000000002", 1));

		e.startSaleTransaction();

		assertFalse(e.addProductToSaleRFID(2, "000000001212")); //non existing rfid

		assertTrue(e.addProductToSaleRFID(2, "000000000001")); //okay

		assertEquals(1, (int) e.getProductTypeByBarCode("123456789012").getQuantity());

		assertTrue(e.addProductToSaleRFID(2, "000000000002")); //okay

		assertEquals(0, (int) e.getProductTypeByBarCode("123456789012").getQuantity());

		e.endSaleTransaction(2);

		assertEquals(24, e.getSaleTransaction(2).getPrice(), 0.0001);
		assertEquals(1, e.getSaleTransaction(2).getEntries().size());
        assertEquals(2, e.getSaleTransaction(2).getEntries().get(0).getAmount());
	}

    @Test
    public void testDeleteProductFromSaleRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {

    	EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		
		e.login("validAdministrator","pass");
		int id1 = e.createProductType("Tomato", "628176957012", 2.0, "nothing to see here");
		int id2 = e.createProductType("Banana", "628176957029", 3.0, "nothing to see here too");
		
		e.getProductTypeByBarCode("628176957012").setQuantity(1);
		
		Product p1 = new Product("000000000001", id1);
		Product p12 = new Product("000000000002", id1);
		Product p2 = new Product("000000000003", id2);

		e.RFIDs.put("000000000001", p1);
		e.RFIDs.put("000000000002", p12);
		e.RFIDs.put("000000000003", p2);
		
		e.logout();
		//end setting up environment
		assertThrows(UnauthorizedException.class, () -> {e.deleteProductFromSaleRFID(1, "000000000001");});
	
		e.login("validAdministrator", "pass");

		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSaleRFID(null, "000000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSaleRFID(-1, "000000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSaleRFID(0, "000000000001");});

		assertThrows(InvalidRFIDException.class, () -> {e.deleteProductFromSaleRFID(1, null);});
        assertThrows(InvalidRFIDException.class, () -> {e.deleteProductFromSaleRFID(1, "");});
        assertThrows(InvalidRFIDException.class, () -> {e.deleteProductFromSaleRFID(1, "001");});
        
        assertFalse(e.deleteProductFromSaleRFID(1, "000000000001")); //non existing transaction

        e.startSaleTransaction();
        e.endSaleTransaction(1);
        
        assertFalse(e.deleteProductFromSaleRFID(1, "000000000001"));   //transaction already closed
        
        e.startSaleTransaction();
        
        e.addProductToSaleRFID(2, "000000000001");
        
        
        assertFalse(e.deleteProductFromSaleRFID(2, "000000100000"));	//RFID does not exists
        assertFalse(e.deleteProductFromSaleRFID(2, "000000000003"));  //RFID exists but belongs to product type not in TicketEntry list
                
        double price_before = 2.0;
        
        int pId = e.RFIDs.get("000000000001").getProductId();
        String barCode = e.getAllProductTypes().stream().filter((p)->p.getId() == pId).findFirst().get().getBarCode();
        Integer previous_quantity = 0;
        
        assertTrue(e.deleteProductFromSaleRFID(2,  "000000000001"));
        
        e.endSaleTransaction(2);
        assertTrue(e.getSaleTransaction(2).getPrice() == price_before - e.getProductTypeByBarCode(barCode).getPricePerUnit().doubleValue());
        assertTrue(e.getProductTypeByBarCode(barCode).getQuantity() == previous_quantity+1);
        assertFalse(e.getSaleTransaction(2).getEntries().stream().
        		filter((t)->t.getBarCode().equals(barCode)).
        		findAny().isPresent());	//check that product with quantity == 0 was removed from list
        e.logout();
        
        /*testing login works also with other types of user: ShopManager, Cashier*/
        e.login("validManager", "pass");
		e.deleteProductFromSaleRFID(2, "000000000005");
		e.logout();
		
		e.login("validUser","pass");
		e.deleteProductFromSaleRFID(2, "000000000005");
		e.logout();
    }

    @Test
    public void testRecordOrderArrivalRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {
        EZShop e = new EZShop();
		e.reset();

		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		String basic_RFID = "000000000001";

		assertThrows(UnauthorizedException.class, () -> {e.recordOrderArrivalRFID(1, basic_RFID);});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.recordOrderArrivalRFID(1, basic_RFID);});

		e.logout();
		e.login("adminuser", "password");

		e.createProductType("apple", "628176957012", 2.0, "green apples");
		e.createProductType("banana", "628176957074", 2.0, "yellow bananas");
		e.updatePosition(1, "1-a-0");
		int issued_order = e.issueOrder("628176957012", 100, 2.0);
		
		e.recordBalanceUpdate(500.0);
		int payed_order_id = e.payOrderFor("628176957012", 100, 2.0);
		int order_no_location_id = e.payOrderFor("628176957074", 1, 2.0);

		assertThrows(InvalidOrderIdException.class, () -> {e.recordOrderArrivalRFID(-1, basic_RFID);});
		assertThrows(InvalidOrderIdException.class, () -> {e.recordOrderArrivalRFID(null, basic_RFID);});
		assertThrows(InvalidLocationException.class, () -> {e.recordOrderArrivalRFID(order_no_location_id, basic_RFID);});
		assertThrows(InvalidRFIDException.class, () -> {e.recordOrderArrivalRFID(payed_order_id, null);});
		assertThrows(InvalidRFIDException.class, () -> {e.recordOrderArrivalRFID(payed_order_id, "");});
		assertThrows(InvalidRFIDException.class, () -> {e.recordOrderArrivalRFID(payed_order_id, "00001");});

		assertFalse(e.recordOrderArrivalRFID(404, basic_RFID));
		assertFalse(e.recordOrderArrivalRFID(issued_order, basic_RFID));
		assertTrue(e.recordOrderArrivalRFID(payed_order_id, basic_RFID));
		assertFalse(e.recordOrderArrivalRFID(payed_order_id, basic_RFID));

		assertTrue(e.RFIDs.size() == 100);
		assertTrue(e.RFIDs.get("000000000050").getRFID().equals("000000000050"));
    }

    @Test
    public void testReturnProductRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {
        EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.returnProductRFID(1, "000000000001");});
	
		e.login("validAdministrator", "pass");

		assertThrows(InvalidTransactionIdException.class, () -> {e.returnProductRFID(null, "000000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnProductRFID(-1, "000000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.returnProductRFID(0, "000000000001");});

		assertThrows(InvalidRFIDException.class, () -> {e.returnProductRFID(1, null);});
        assertThrows(InvalidRFIDException.class, () -> {e.returnProductRFID(1, "");});
        assertThrows(InvalidRFIDException.class, () -> {e.returnProductRFID(1, "001");});
		assertThrows(InvalidRFIDException.class, () -> {e.returnProductRFID(1, "00000000001");});

		int stID = e.startSaleTransaction();	
		int id1 = e.createProductType("Tomato", "628176957012", 2.0, "nothing to see here");
		int id2 = e.createProductType("Banana", "628176957029", 3.0, "nothing to see here too");

		e.getProductTypeByBarCode("628176957012").setQuantity(10);
		e.addProductToSale(stID, "628176957012", 2);

		Product p1 = new Product("000000000001", id1);
		Product p12 = new Product("000000000002", id1);
		Product p2 = new Product("000000000003", id2);

		e.RFIDs.put("000000000001", p1);
		e.RFIDs.put("000000000002", p12);
		e.RFIDs.put("000000000003", p2);

		e.endSaleTransaction(stID);

		int rtID = e.startReturnTransaction(stID);

		assertTrue(e.returnProductRFID(rtID, "000000000001"));
		assertFalse(e.returnProductRFID(rtID, "000000000003"));
		assertFalse(e.returnProductRFID(1000, "000000000001"));
		assertFalse(e.returnProductRFID(rtID, "000000000010"));
    }
}

