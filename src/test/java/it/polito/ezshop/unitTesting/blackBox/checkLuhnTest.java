package it.polito.ezshop.unitTesting.blackBox;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.model.GFG;

public class checkLuhnTest {

	@Test 
	public void testNullString() {
		
		assertFalse(GFG.checkLuhn(null));
	}
	
	@Test 
	public void testInvalidStrings() {
		
		assertFalse(GFG.checkLuhn("qwerty"));
		assertFalse(GFG.checkLuhn("4716258050958642"));
		assertFalse(GFG.checkLuhn("448537008651089"));	
		
	}
		
	@Test 
	public void testValidStrings() {
		
		assertTrue(GFG.checkLuhn("4485370086510891"));
		assertTrue(GFG.checkLuhn("5100293991053009"));
		assertTrue(GFG.checkLuhn("4716258050958645"));	
		
	}	
		
}
	

