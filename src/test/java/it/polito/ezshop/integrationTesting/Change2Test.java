package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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

public class Change2Test {
    
    @Test
	public void testAddProductToSaleRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {

        //TODO: to test, since the recordOrderArrivalRFID() is needed

		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "pwd", "Administrator");
		
		assertThrows(UnauthorizedException.class, () -> {e.addProductToSaleRFID(1, "0000000001");});

		e.login("adminuser", "pwd");

		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSaleRFID(null, "0000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSaleRFID(-1, "0000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.addProductToSaleRFID(0, "0000000001");});

		assertThrows(InvalidRFIDException.class, () -> {e.addProductToSaleRFID(1, null);});
        assertThrows(InvalidRFIDException.class, () -> {e.addProductToSaleRFID(1, "");});
        assertThrows(InvalidRFIDException.class, () -> {e.addProductToSaleRFID(1, "001");});

        assertFalse(e.addProductToSaleRFID(1, "0000000001")); //non existing transaction

		e.startSaleTransaction();
		e.endSaleTransaction(1);

		assertFalse(e.addProductToSaleRFID(1, "0000000001")); //already closed

		e.createProductType("product", "123456789012", 12, "product");
		e.updatePosition(1, "1-f-1");
        e.recordBalanceUpdate(1000); //to pay the order
        Integer i = e.payOrderFor("123456789012", 2, 10);
        e.recordOrderArrivalRFID(i, "0000000001");

		e.startSaleTransaction();

		assertFalse(e.addProductToSaleRFID(2, "0000001212")); //non existing rfid

		assertTrue(e.addProductToSaleRFID(2, "0000000001")); //okay

        assertFalse(e.addProductToSaleRFID(2, "0000000001")); //already sold

		assertEquals(1, (int) e.getProductTypeByBarCode("123456789012").getQuantity());

		assertTrue(e.addProductToSaleRFID(2, "0000000002")); //okay

		assertEquals(0, (int) e.getProductTypeByBarCode("123456789012").getQuantity());

		e.endSaleTransaction(2);

		assertEquals(24, e.getSaleTransaction(2).getPrice(), 0.0001);
		assertEquals(1, e.getSaleTransaction(2).getEntries().size());
        assertEquals(2, e.getSaleTransaction(2).getEntries().get(0).getAmount());
	}

    @Test
    public void testDeleteProductFromSaleRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {
        //TODO:  put where needed barcode associated to RFID and run test
    	EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.deleteProductFromSaleRFID(1, "0000000001");});
	
		e.login("validAdministrator", "pass");

		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSaleRFID(null, "0000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSaleRFID(-1, "0000000001");});
		assertThrows(InvalidTransactionIdException.class, () -> {e.deleteProductFromSaleRFID(0, "0000000001");});

		assertThrows(InvalidRFIDException.class, () -> {e.deleteProductFromSaleRFID(1, null);});
        assertThrows(InvalidRFIDException.class, () -> {e.deleteProductFromSaleRFID(1, "");});
        assertThrows(InvalidRFIDException.class, () -> {e.deleteProductFromSaleRFID(1, "001");});
        
        assertFalse(e.deleteProductFromSaleRFID(1, "0000000001")); //non existing transaction

        e.startSaleTransaction();
        e.endSaleTransaction(1);
        
        assertFalse(e.deleteProductFromSaleRFID(1, "0000000001"));   //transaction already closed
        
        e.startSaleTransaction();
        
        e.addProductToSaleRFID(2, "0000000001");
        assertFalse(e.deleteProductFromSaleRFID(2, "0000100000"));	//RFID does not exists
        assertFalse(e.deleteProductFromSaleRFID(2, "0000000002"));  //RFID exists but belongs to product type not in TicketEntry list
                
        double price_before = e.getSaleTransaction(2).getPrice();
        Integer previous_quantity = e.getProductTypeByBarCode("/*put barcode associated to RFID*/").getQuantity();
        
        assertTrue(e.deleteProductFromSaleRFID(2,  "0000000001"));        
        assertTrue(e.getSaleTransaction(2).getPrice() == price_before - e.getProductTypeByBarCode("/*put barcode associated to RFID*/").getPricePerUnit());
        assertTrue(e.getProductTypeByBarCode("/*put barcode associated to RFID*/").getQuantity() == previous_quantity-1);
        assertTrue(e.getSaleTransaction(2).getEntries().stream().
        		filter((t)->t.getBarCode().equals("/*put barcode associated to RFID*/")).
        		findAny().get()==null);	//check that product with quantity == 0 was removed from list
        e.logout();
        
        /*testing login works also with other types of user: ShopManager, Cashier*/
        e.login("validManager", "pass");
		e.deleteProductFromSaleRFID(2, "0000000005");
		e.logout();
		
		e.login("validUser","pass");
		e.deleteProductFromSaleRFID(2, "0000000005");
		e.logout();
    }

    @Test
    public void testRecordOrderArrivalRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {
        //TODO
    }

    @Test
    public void testReturnProductRFID() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, UnauthorizedException, InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidRFIDException, InvalidOrderIdException {
        //TODO

    }
}

