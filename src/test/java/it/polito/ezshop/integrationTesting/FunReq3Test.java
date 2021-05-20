package it.polito.ezshop.integrationTesting;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import it.polito.ezshop.data.*;
import it.polito.ezshop.exceptions.*;

public class FunReq3Test {

	@Test 
	public void testCreateProductType() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.logout();
		e.login("adminuser", "password");

		assertThrows(InvalidPricePerUnitException.class, () -> {e.createProductType("apple", "628176957012", 0.0, "red apples");});
		assertThrows(InvalidProductDescriptionException.class, () -> {e.createProductType(null, "628176957012", 2.0, "red apples");});
		assertThrows(InvalidProductDescriptionException.class, () -> {e.createProductType("", "628176957012", 2.0, "red apples");});
		assertThrows(InvalidProductCodeException.class, () -> {e.createProductType("apple", "6281769570", 2.0, "red apples");});

		int productID = e.createProductType("apple", "628176957012", 2.0, "red apples");
		int replicateProductID = e.createProductType("apple", "628176957012", 2.5, "green apples");

		assertEquals(1, productID);
		assertEquals(-1, replicateProductID);
	}

	@Test
	public void testUpdateProductType() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException, InvalidProductIdException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.logout();
		e.login("adminuser", "password");

		int productID = e.createProductType("apple", "628176957012", 2.0, "red apples");
		e.createProductType("apple", "628176957074", 1.5, "green apples");

		assertThrows(InvalidProductIdException.class, () -> {e.updateProduct(null, "apples", "628176957012", 2.5, "red apples PREMIUM");});
		assertThrows(InvalidProductIdException.class, () -> {e.updateProduct(0, "apples", "628176957012", 2.5, "red apples PREMIUM");});
		assertThrows(InvalidProductDescriptionException.class, () -> {e.updateProduct(productID, null, "628176957012", 2.5, "red apples PREMIUM");});
		assertThrows(InvalidProductDescriptionException.class, () -> {e.updateProduct(productID, "", "628176957012", 2.5, "red apples PREMIUM");});
		assertThrows(InvalidPricePerUnitException.class, () -> {e.updateProduct(productID, "apples", "628176957012", 0.0, "red apples PREMIUM");});
		assertThrows(InvalidProductCodeException.class, () -> {e.updateProduct(productID, "apples", "62817", 2.5, "red apples PREMIUM");});

		assertFalse(e.updateProduct(404, "apples", "628176957012", 2.5, "red apples PREMIUM"));
		assertFalse(e.updateProduct(productID, "apples", "628176957074", 2.5, "red apples PREMIUM"));
		assertTrue(e.updateProduct(productID, "apples", "628176957012", 2.5, "red apples PREMIUM"));
	}

	@Test
	public void testDeleteProductType() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException, InvalidProductIdException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.logout();
		e.login("adminuser", "password");

		int productID = e.createProductType("apple", "628176957012", 2.0, "red apples");

		assertThrows(InvalidProductIdException.class, () -> {e.deleteProductType(null);});
		assertThrows(InvalidProductIdException.class, () -> {e.deleteProductType(0);});

		assertTrue(e.deleteProductType(productID));
	}

	@Test
	public void testGetAllProductTypes() throws InvalidUsernameException, InvalidPasswordException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException, InvalidRoleException, InvalidProductIdException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.logout();
		e.login("adminuser", "password");

		int productID = e.createProductType("apple", "628176957012", 2.0, "red apples");
		int otherProductID = e.createProductType("apple", "628176957074", 1.5, "green apples");

		List<ProductType> products = e.getAllProductTypes();
		
		assertEquals(2, products.size());

		e.deleteProductType(productID);
		products = e.getAllProductTypes();

		assertEquals(1, products.size());

		e.deleteProductType(otherProductID);
		products = e.getAllProductTypes();

		assertTrue(products.isEmpty());
	}

	@Test
	public void testGetProductTypeByBarCode() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException {
		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.logout();
		e.login("adminuser", "password");

		e.createProductType("apple", "628176957012", 2.0, "red apples");

		assertThrows(InvalidProductCodeException.class, () -> {e.getProductTypeByBarCode("62812");});

		ProductType product = e.getProductTypeByBarCode("628176957012");
		ProductType otherProduct = e.getProductTypeByBarCode("628176957074");

		assertNotNull(product);
		assertNull(otherProduct);
	}

	@Test
	public void testGetProductByDescription() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException, InvalidProductIdException{
		EZShop e = new EZShop();
		e.reset();
		e.createUser("adminuser", "password", "Administrator");
		e.createUser("cashieruser", "pwd", "Cashier");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.login("cashieruser", "pwd");

		assertThrows(UnauthorizedException.class, () -> {e.createProductType("apple", "628176957012", 2.0, "red apples");});

		e.logout();
		e.login("adminuser", "password");

		int productID = e.createProductType("apple", "628176957012", 2.0, "red apples");
		e.createProductType("apple", "628176957074", 1.5, "green apples");

		List<ProductType> products = e.getProductTypesByDescription(null);
		assertEquals(2, products.size());

		products = e.getProductTypesByDescription("");
		assertEquals(2, products.size());

		products = e.getProductTypesByDescription("app");
		assertEquals(2, products.size());

		products = e.getProductTypesByDescription("apple");
		assertEquals(2, products.size());

		e.updateProduct(productID, "tomato", "628176957012", 2.0, "It was tomatoes all along !");
		products = e.getProductTypesByDescription("apple");
		assertEquals(1, products.size());
		
		products = e.getProductTypesByDescription("banana");
		assertTrue(products.isEmpty());
	}
}
