package it.polito.ezshop.unitTesting.blackBox;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import it.polito.ezshop.model.ProductTypeClass;

public class VerifyBarCodeTest {
	
	@Test 
	public void verifyNull() {
		assertFalse(ProductTypeClass.VerifyBarCode(null));
	}

	@Test
	public void verifyFalseString() {
		assertFalse(ProductTypeClass.VerifyBarCode(""));
		assertFalse(ProductTypeClass.VerifyBarCode("01"));
		assertFalse(ProductTypeClass.VerifyBarCode("error"));
		assertFalse(ProductTypeClass.VerifyBarCode("111111111111111111111"));
		assertFalse(ProductTypeClass.VerifyBarCode("628176957011"));
		assertFalse(ProductTypeClass.VerifyBarCode("628176a57012"));
	}

	@Test
	public void verifyTrueString() {
		assertTrue(ProductTypeClass.VerifyBarCode("628176957012"));
		assertTrue(ProductTypeClass.VerifyBarCode("0628176957012"));
		assertTrue(ProductTypeClass.VerifyBarCode("00628176957012"));
		assertTrue(ProductTypeClass.VerifyBarCode("628176957029"));
		assertTrue(ProductTypeClass.VerifyBarCode("628176957074"));
		assertTrue(ProductTypeClass.VerifyBarCode("10628176957101"));
	}
}
