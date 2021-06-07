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
}
