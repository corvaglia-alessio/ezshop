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

| Criteria | Boundary values |
| -------- | --------------- |
|          |                 |
|          |                 |



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
|any input |Valid | setBalanceId(1) -> getBalanceId()==1 | testBalanceOperationClass |


### **Class *OrderClass* - method *setBalanceId**





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
|any input |Valid | setBalanceId(1) -> getBalanceId()==1 | testOrderClass |


### **Class *OrderClass* - method *productCode***


**Criteria for method *productCode*:**
	
 - any input 




**Predicates for method *productCode*:**

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
|any input |Valid | setProductCode("productCode") -> getProductCode()=="productCode" | testOrderClass |


### **Class *OrderClass* - method *setPricePerUnit***


**Criteria for method *setPricePerUnit*:**
	
 - any input 




**Predicates for method *setPricePerUnit*:**

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
|any input |Valid | setPricePerUnit(2.00) -> getPricePerUnit()==2.00 | testOrderClass |


### **Class *OrderClass* - method *setQuantity***


**Criteria for method *setQuantity*:**
	
 - any input 




**Predicates for method *setQuantity*:**

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
|any input |Valid | setQuantity(5) -> getQuantity()==5 | testOrderClass |


### **Class *OrderClass* - method *setStatus***


**Criteria for method *setStatus*:**
	
 - any input 




**Predicates for method *setStatus*:**

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
|any input |Valid | setStatus("ORDERED") -> getStatus()=="ORDERED" | testOrderClass |



### **Class *OrderClass* - method *setOrderId***


**Criteria for method *setOrderId*:**
	
 - any input 




**Predicates for method *setOrderId*:**

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
|any input |Valid | setOrderId(5) -> getOrderId()==5 | testOrderClass |



### **Class *ProductTypeClass* - method *setQuantity***




**Criteria for method *setQuantity*:**
	
 - any input 




**Predicates for method *setQuantity*:**

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
|any input |Valid | setQuantity(1) -> getQuantity()==1 | testProductTypeClass |


### **Class *ProductTypeClass* - method *setLocation***



**Criteria for method *setLocation*:**
	
 - any input 




**Predicates for method *setLocation*:**

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
|any input |Valid | setLocation("location") -> getLocation()=="location" | testProductTypeClass |


### **Class *ProductTypeClass* - method *setNote***



**Criteria for method *setNote*:**
	
 - any input 




**Predicates for method *setNote*:**

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
|any input |Valid | setNote("note") -> getNote()=="note" | testProductTypeClass |


### **Class *ProductTypeClass* - method *setProductDescription***



**Criteria for method *setProductDescription*:**
	
 - any input 




**Predicates for method *setProductDescription*:**

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
|any input |Valid | setProductDescription("description") -> getProductDescription()=="description" | testProductTypeClass |


### **Class *ProductTypeClass* - method *setBarCode***



**Criteria for method *setBarCode*:**
	
 - any input 




**Predicates for method *setBarCode*:**

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
|any input |Valid | setBarCode("barcode") -> getBarCode()=="barcode" | testProductTypeClass |


### **Class *ProductTypeClass* - method *setPricePerUnit***



**Criteria for method *setPricePerUnit*:**
	
 - any input 




**Predicates for method *setPricePerUnit*:**

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
|any input |Valid | setPricePerUnit(1) -> getPricePerUnit()==1 | testProductTypeClass |


### **Class *ProductTypeClass* - method *setId***



**Criteria for method *setId*:**
	
 - any input 




**Predicates for method *setId*:**

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
|any input |Valid | setId(1) -> getId()==1 | testProductTypeClass |





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
