package it.polito.ezshop.unitTesting.blackBox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.model.Cardhandler;

public class checkLuhnTest {

	@Test 
	public void testNull() {
		
		assertFalse(Cardhandler.checkLuhn(null));
	}
	
	@Test 
	public void testInvalidStrings() {
		
		assertFalse(Cardhandler.checkLuhn(""));
		assertFalse(Cardhandler.checkLuhn("1"));
		assertFalse(Cardhandler.checkLuhn("a"));
		assertFalse(Cardhandler.checkLuhn("qwerty"));
		assertFalse(Cardhandler.checkLuhn("4716258050958642"));
		assertFalse(Cardhandler.checkLuhn("448537008651089"));	
		assertFalse(Cardhandler.checkLuhn("1234qwerty56789a"));
		
	}
		
	@Test 
	public void testValidStrings() {
		
		assertTrue(Cardhandler.checkLuhn("4485370086510891"));
		assertTrue(Cardhandler.checkLuhn("5100293991053009"));
		assertTrue(Cardhandler.checkLuhn("4716258050958645"));	
		
	}	
		
}
	

