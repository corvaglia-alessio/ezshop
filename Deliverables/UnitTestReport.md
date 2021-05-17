# Unit Testing Documentation

Authors: Martin CAM / Alessio CORVAGLIA / Alessandro IANDOLI / Federico MUSTICH

Date: 12 May 2021

Version: 1.0

# Contents

- [Black Box Unit Tests](#black-box-unit-tests)




- [White Box Unit Tests](#white-box-unit-tests)


# Black Box Unit Tests

    <Define here criteria, predicates and the combination of predicates for each function of each class.
    Define test cases to cover all equivalence classes and boundary conditions.
    In the table, report the description of the black box test case and (traceability) the correspondence with the JUnit test case writing the 
    class and method name that contains the test case>
    <JUnit test classes must be in src/test/java/it/polito/ezshop   You find here, and you can use,  class TestEzShops.java that is executed  
    to start tests
    >

### **Class *className* - method *methodName***


**Criteria for method *methodName*:**
	
 - any input 




**Predicates for method *methodName*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setMethodName(fill_it) -> getMethodName()==fill_it | testUnitMethods.<<testclassName>> |


 ### **Class *GFG* - method *checkLuhn***


**Criteria for method *checkLuhn*:**
	
 - validity of input string





**Predicates for method *checkLuhn*:**

| Criteria | Predicate |
| -------- | --------- |
|     validity of input string     |     valid string      |
|          |       invalid string     |
|          |      NULL     |





**Boundaries**:

No boundaries



**Combination of predicates**:


| Criteria 1  | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|valid string | valid|T1("4485370086510891")->true|testValidStrings|
|invalid string| valid|T2("448537invalid0891")->false|testInvalidStrings|
|NULL| valid|T3(NULL)->false|testNull|



 ### **Class *ProductTypeClass* - method *VerifyBarCode***



**Criteria for method *VerifyBarCode*:**
	

 - Validity of input String





**Predicates for method *VerifyBarCode*:**

| Criteria | Predicate |
| -------- | --------- |
|  Validity of input String  |    Valid String    |
|          |    Invalid String       |
|          |    Null       |





**Boundaries**:

No boundaries



**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
| Valid String | Valid | T1("0628176957012") -> True | verifyTrueString |
| Invalid String | Valid | T2("1111111111") -> False | verifyFalseString |
| Null | Valid | T3(null) -> False | verifyNull |


### **Class *BalanceOperationClass* - method *setBalanceId***



**Criteria for method *setBalanceId*:**
	
 - any input 




**Predicates for method *setBalanceId*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setBalanceId(1) -> getBalanceId()==1 | testUnitMethods.testBalanceOperationClass() |



### **Class *TicketEntry* - method *setTransactionId()***


**Criteria for method *methodName*:**
	
 - any input 




**Predicates for method *methodName*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setTransactionId(2) -> getTransactionId()==2 | testUnitMethods.testTicketEntryClass |


### **Class *TicketEntry* - method *setBarCode()***


**Criteria for method *methodName*:**
	
 - any input 




**Predicates for method *methodName*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setBarCode("0628176957012") -> getBarCode()=="0628176957012" | testUnitMethods.testTicketEntryClass |

### **Class *TicketEntry* - method *setProductDescription()***


**Criteria for method *methodName*:**
	
 - any input 




**Predicates for method *methodName*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setProductDescription("kiwi") -> getProductDescription()=="kiwi" | testUnitMethods.testTicketEntryClass |

### **Class *TicketEntry* - method *setPricePerUnit()***


**Criteria for method *methodName*:**
	
 - any input 




**Predicates for method *methodName*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setPricePerUnit(0.9) -> getPricePerUnit()==0.9 | testUnitMethods.testTicketEntryClass |


### **Class *TicketEntry* - method *setAmount()***


**Criteria for method *methodName*:**
	
 - any input 




**Predicates for method *methodName*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setAmount(4) -> getAmount()==4 | testUnitMethods.testTicketEntryClass |

### **Class *TicketEntry* - method *setDiscountRate()***


**Criteria for method *methodName*:**
	
 - any input 




**Predicates for method *methodName*:**

| Criteria | Predicate |
| -------- | --------- |
|   any input       |    arbitrary values       |
|          |           |
|          |           |
|          |           |





**Boundaries**:


No boundaries


**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|any input |Valid | setDiscountRate(0.5) -> getDiscountRate()==0.5 | testUnitMethods.testTicketEntryClass |




# White Box Unit Tests

### Test cases definition
    
    <JUnit test classes must be in src/test/java/it/polito/ezshop>
    <Report here all the created JUnit test cases, and the units/classes under test >
    <For traceability write the class and method name that contains the test case>


| Unit name | JUnit test case |
|--|--|
|||
|||
||||

### Code coverage report

    <Add here the screenshot report of the statement and branch coverage obtained using
    the Eclemma tool. >

![coverage](unitTestCoverage.png)

### Loop coverage analysis

    <Identify significant loops in the units and reports the test cases
    developed to cover zero, one or multiple iterations >

|Unit name | Loop rows | Number of iterations | JUnit test case |
|---|---|---|---|
|||||
|||||
||||||
