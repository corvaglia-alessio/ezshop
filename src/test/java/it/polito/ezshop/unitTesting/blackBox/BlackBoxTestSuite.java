package it.polito.ezshop.unitTesting.blackBox;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.data.EZShop;


@RunWith(Suite.class)
@Suite.SuiteClasses({checkLuhnTest.class, VerifyBarCodeTest.class, testUnitsMethods.class})
public class BlackBoxTestSuite {
	
    @AfterClass
    public static void clearEzShop(){
        EZShop e = new EZShop();
        e.reset();
    }
}