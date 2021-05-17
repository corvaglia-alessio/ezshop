# Integration and API Test Documentation

Authors: Martin CAM / Alessio CORVAGLIA / Alessandro IANDOLI / Federico MUSTICH

Date: 14 May 2021

Version: 1.0

# Contents

- [Dependency graph](#dependency graph)

- [Integration approach](#integration)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Dependency graph 

     <report the here the dependency graph of the classes in EzShop, using plantuml>

![Dependency Graph](http://www.plantuml.com/plantuml/png/TOz1QiCm44NtEeKla3ia3Od9fWcnGw71b47wf2BIo8n62z--K2WOEbb-tfDuiwrAeYqdLv-HPnBAdIzvBWpMGPU4Jk2L-97WtFtmENoN-Qk_dQPki-d-zkvocjnw_C8tIiGUvnb26WjFtNzdBmXHzoJ1fArgoH2BdINOu28bDA_ZCiE8kaARy2Z4bVohge4IregxZz4_eGTMMMp5-6NrniAtQlVtO0Wftbk6H3-eH7wOuh4t3Zp6X2ieG7OSFYGgP79RS6Wv_G00)
     
# Integration approach

    <Write here the integration sequence you adopted, in general terms (top down, bottom up, mixed) and as sequence
    (ex: step1: class A, step 2: class A+B, step 3: class A+B+C, etc)> 
    <Some steps may  correspond to unit testing (ex step1 in ex above), presented in other document UnitTestReport.md>
    <One step will  correspond to API testing>
    
<b>Approach used: Bottom-Up</b>
     
<b>Step 1:</b> Unit testing
<b>Step 2:</b> API testing

#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them> JUnit test classes should be here src/test/java/it/polito/ezshop

## Step 1
| Classes  | JUnit test cases |
|--|--|
|ProductTypeClass.java|VerifyBarCodeTest.verifyNull(), VerifyBarCodeTest.verifyFalseString(), VerifyBarCodeTest.verifyTrueString()|
|GFG.java|checkLuhnTest.testNull(), VerifyBarCodeTest.testInvalidStrings(), VerifyBarCodeTest.testValidStrings()|


## Step 2
| Classes  | JUnit test cases |
|--|--|
|EZShop.java (in data package)| All tests included in the following classes: FunReq1Test.java, FunReq3Test.java, FunReq4Test.java, FunReq5Test.java, FunReq6Test.java, FunReq7Test.java, FunReq8Test.java. All these classes are included in the IntegrationTesting package|


# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC in the OfficialRequirements that they detail>

## Scenario UCx.y

| Scenario |  name |
| ------------- |:-------------:| 
|  Precondition     |  |
|  Post condition     |   |
| Step#        | Description  |
|  1     |  ... |  
|  2     |  ... |

## Scenario UC2-4

| Scenario |  List all users |
| ------------- |:-------------:| 
|  Precondition     | Admin A exists and is logged in |
|  Post condition     |  List of users is returned |
| Step#        | Description  |
|  1     |  A asks the application to retrieve the user list |  

## Scenario UC9-2

| Scenario |  Record a balance operation |
| ------------- |:-------------:| 
|  Precondition     | User U exists and is logged in |
|  Post condition     | Balance operation is recorded |
|   | The system balance is modified |
| Step#        | Description  |
|  1     | User U pays for an order, issue a sale or a return and the balance is updated  |  



# Coverage of Scenarios and FR


<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR. 
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >




| Scenario ID | Functional Requirements covered | JUnit  Test(s) | 
| ----------- | ------------------------------- | ----------- | 
|  2-1         | FR1                            |      FunReq1Test.testCreateUser()       |             
|  2-2         | FR1                             |       FunReq1Test.testDeleteUser()       |             
|  2-3         |    FR1                             |        FunReq1Test.testUpdateUserRights()      |             
| 2-4    |      FR1       | FunReq1Test.testGetAllUsers(), FunReq1Test.testGetUser() |                             
| 5-1         |                   FR1              |        FunReq1Test.testLogin()     |      
| 5-2         |                   FR1              |        FunReq1Test.testLogout()     |           
| 6-1         |                   FR6              |        FunReq6Test.testStartSaleTransaction(),  FunReq6Test.testAddProductToSale(), FunReq6Test.testDeleteProductFromSale(), FunReq6Test.testEndSaleTransaction(), FunReq6Test.testGetSaleTransaction()     |  
| 6-2         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testApplyDiscountRateToProduct()     |  
| 6-3         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testApplyDiscountRateToSale()     |  
| 6-4         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testComputePointsForSale()    |  
| 6-5         |                   FR6              |        Same as scenario 6.1 + FunReq6Test.testDeleteSaleTransaction()    |  
| 6-6         |                   FR6              |        Same as scenario 6.1   |  
|  9-1           |       FR8   |  FunReq8Test.testGetCreditsAndDebits()   FunReq8Test.testComputeBalance()   |
|  9-2           |       FR8          |             FunReq8Test.testRecordBalanceUpdate()     |



# Coverage of Non Functional Requirements


<Report in the following table the coverage of the Non Functional Requirements of the application - only those that can be tested with automated testing frameworks.>


### 

| Non Functional Requirement | Test name |
| -------------------------- | --------- |
|                            |           |


