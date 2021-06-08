package it.polito.ezshop.integrationTesting;



import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.data.EZShop;


@RunWith(Suite.class)
@Suite.SuiteClasses({FunReq1Test.class, FunReq3Test.class, FunReq4Test.class, FunReq5Test.class, FunReq6Test.class, FunReq7Test.class, FunReq8Test.class, Change2Test.class})
public class IntegrationTestSuite {

    @AfterClass
    public static void clearEzShop(){
        EZShop e = new EZShop();
        e.reset();
    }
}





