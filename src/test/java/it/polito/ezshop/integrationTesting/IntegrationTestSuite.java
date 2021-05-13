package it.polito.ezshop.integrationTesting;

/*
*This is the older version
*
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({FunReq1Test.class, FunReq3Test.class, FunReq4Test.class, FunReq5Test.class, FunReq6Test.class, FunReq7Test.class, FunReq8Test.class})
public class IntegrationTestingSuite {
	
	
	
}
*/


/*new version supporting also BeforeEach AfterEach syntax*/
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectClasses({FunReq1Test.class, FunReq3Test.class, FunReq4Test.class, FunReq5Test.class, FunReq6Test.class, FunReq7Test.class, FunReq8Test.class} )
public class IntegrationTestSuite {
	
	
}

