package it.polito.ezshop.data;

import it.polito.ezshop.exceptions.*;
import it.polito.ezshop.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class EZShop implements EZShopInterface {

	
	
	HashMap<Integer,it.polito.ezshop.model.Customer> customers;
    Map<Integer, User> users;
    Map<Integer, BalanceOperation> balanceOperations;
    User loggedInUser;
    double currentBalance;

    public EZShop() {

        
        loggedInUser = null;
        currentBalance = 0;
        //load balance operations from file!!
        
         //user init
        this.users = FileReaderAndWriter.UsersReader();
        this.loggedInUser = null;

        //balance init
        this.balanceOperations = FileReaderAndWriter.BalanceOperationsReader();
        this.currentBalance = 0;
        for (BalanceOperation bo : balanceOperations.values()) //after having loaded operations from file, update the current balance
            this.currentBalance+=bo.getMoney();
        
        //customers init
        this.customers = FileReaderAndWriter.CustomersReader();

    }

    @Override
    public void reset() {

    }

    @Override
    public Integer createUser(String username, String password, String role) throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
        Integer maxid = 0;

        if(password.equals("") || password == null)
            throw new InvalidPasswordException("Password should not be empty");

        if(username.equals("") || username == null)
            throw new InvalidUsernameException("Username should not be empty");

        for(User u : users.values()){
            if(u.getUsername().equals(username)){
                return -1;
            }
            else{
                if(u.getId()>maxid)
                    maxid=u.getId();
            }
        }

        if(role.equals("Administrator") || role.equals("Cashier") || role.equals("ShopManager")){
            User u = new UserClass(maxid+1, username, password, role);
            users.put(maxid+1, u);
            if(!FileReaderAndWriter.UsersWriter(users))
                return -1;
        }
        else{
            throw new InvalidRoleException("Non existing role");
        }
            
        return maxid+1;
    }

    @Override
    public boolean deleteUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
        if(id<=0 || id==null)
            throw new InvalidUserIdException("Invalid id");
        
        if(loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if(loggedInUser.getRole().equals("Administrator")){
            if(users.get(id) != null){
                users.remove(id);
                if(!FileReaderAndWriter.UsersWriter(users))
                    return false;
                else
                    return true;
            }
            else
                return false;
        }
        else{
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public List<User> getAllUsers() throws UnauthorizedException {
        if(loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if(loggedInUser.getRole().equals("Administrator")){
            List<User> u = new ArrayList<User>();
            for(User us : users.values())
                u.add(us);
            return u;
        }
        else{
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public User getUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
        if(loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");
        
        if(id<=0 || id==null)
            throw new InvalidUserIdException("Invalid id");

        if(loggedInUser.getRole().equals("Administrator")){
            return users.get(id);
        }
        else{
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public boolean updateUserRights(Integer id, String role) throws InvalidUserIdException, InvalidRoleException, UnauthorizedException {
        if(loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");
        
        if(loggedInUser.getRole().compareTo("Administrator") != 0)
            throw new UnauthorizedException("Function not available for the current user");

        if(id<=0 || id==null)
            throw new InvalidUserIdException("Invalid id");

        if(role.equals("Administrator") || role.equals("Cashier") || role.equals("ShopManager")){
            if(users.get(id) == null)
                return false;
            else{
                users.get(id).setRole(role);
                if(FileReaderAndWriter.UsersWriter(users))
                    return true;
            }
        }
        else
            throw new InvalidRoleException("Non existing role");

        return false;
    }

    @Override
    public User login(String username, String password) throws InvalidUsernameException, InvalidPasswordException {
        if(password.equals("") || password == null)
            throw new InvalidPasswordException("Password should not be empty");

        if(username.equals("") || username == null)
            throw new InvalidUsernameException("Username should not be empty");
        
        for(User us : users.values()){
            if(us.getUsername().equals(username) && us.getPassword().equals(password)){
                loggedInUser = us;
                return loggedInUser;
            }
        }
        loggedInUser = null;
        return null;
    }

    @Override
    public boolean logout() {
        if(loggedInUser == null)
            return false;
        loggedInUser = null;
        return true;
    }

    @Override
    public Integer createProductType(String description, String productCode, double pricePerUnit, String note) throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException {
        return null;
    }

    @Override
    public boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote) throws InvalidProductIdException, InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteProductType(Integer id) throws InvalidProductIdException, UnauthorizedException {
        return false;
    }

    @Override
    public List<ProductType> getAllProductTypes() throws UnauthorizedException {
        return null;
    }

    @Override
    public ProductType getProductTypeByBarCode(String barCode) throws InvalidProductCodeException, UnauthorizedException {
        return null;
    }

    @Override
    public List<ProductType> getProductTypesByDescription(String description) throws UnauthorizedException {
        return null;
    }

    @Override
    public boolean updateQuantity(Integer productId, int toBeAdded) throws InvalidProductIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean updatePosition(Integer productId, String newPos) throws InvalidProductIdException, InvalidLocationException, UnauthorizedException {
        return false;
    }

    @Override
    public Integer issueOrder(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
        return null;
    }

    @Override
    public Integer payOrderFor(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
        return null;
    }

    @Override
    public boolean payOrder(Integer orderId) throws InvalidOrderIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean recordOrderArrival(Integer orderId) throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException {
        return false;
    }

    @Override
    public List<Order> getAllOrders() throws UnauthorizedException {
        return null;
    }

    @Override
    public Integer defineCustomer(String customerName) throws InvalidCustomerNameException, UnauthorizedException {
    	
    	if(loggedInUser == null || !loggedInUser.getRole().equals("Administrator") || !loggedInUser.getRole().equals("Manager") || !loggedInUser.getRole().equals("Cashier")) {
             throw new UnauthorizedException("Function not available for the current user");
         }
    	
    	if(customers.values().stream().anyMatch((c)->c.getCustomerName().equals(customerName))){
        	return -1;
        }
        else {
        	Optional<Integer> max = customers.keySet().stream().max((n1,n2)->n1-n2);
        	if(max.isPresent()) {
        		customers.put(max.get()+1, new it.polito.ezshop.model.Customer(max.get()+1,customerName,"",0));
        		FileReaderAndWriter.CustomersWriter(customers);
    	        return max.get()+1;
        	}
        	else {
        		customers.put(1, new it.polito.ezshop.model.Customer(1,customerName,"",0));
    	        FileReaderAndWriter.CustomersWriter(customers);
    	        return 1;
    	       
        	} 		
        }
        
    }

    @Override
    public boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard) throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException, UnauthorizedException {
    	
    	if(loggedInUser == null || (!loggedInUser.getRole().equals("Administrator") && !loggedInUser.getRole().equals("Manager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }	
    	if(newCustomerName == null || newCustomerName.equals("")) {
    		throw new InvalidCustomerNameException("Invalid customer Name");
    	}
    	
    	if (customers.containsKey(id)){
    		return false;
    	}
    	
    	if(newCustomerCard == null) {
    		customers.get(id).setCustomerName(newCustomerName);
    		FileReaderAndWriter.CustomersWriter(customers);		//TO CHECK WHAT MORISIO SAYS
    		return true;
    	}
    	
    	if(newCustomerCard.isEmpty()) {
    		customers.get(id).setCustomerName(newCustomerName);
    		customers.get(id).setCustomerCard("");
    		FileReaderAndWriter.CustomersWriter(customers);
    		return true;
    	}
    	
    	if(newCustomerCard.length()!=10) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	}
    	
    	try {
    		Integer.parseInt(newCustomerCard);
    	}
    	catch(NumberFormatException nfe) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	}
    	
    	if(customers.values().stream().anyMatch((c)->c.getCustomerCard().equals(newCustomerCard))){
    		return false;
    	}
    	customers.get(id).setCustomerCard(newCustomerCard);
    	FileReaderAndWriter.CustomersWriter(customers);
		return true;
    }

    @Override
    public boolean deleteCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
    	
    	if(loggedInUser == null || (!loggedInUser.getRole().equals("Administrator") && !loggedInUser.getRole().equals("Manager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
    	if(id==null || id<=0) {
    		throw new InvalidCustomerIdException("Invalid customer Id");
    	}
    	
    	if(!customers.containsKey(id))
    		return false;
    	else {
    		customers.remove(id);
        	FileReaderAndWriter.CustomersWriter(customers);
        	return true;
    	}
    }

    @Override
    public Customer getCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
    	
    	if(loggedInUser == null || (!loggedInUser.getRole().equals("Administrator") && !loggedInUser.getRole().equals("Manager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
    	
    	if(id==null || id<=0) {
    		throw new InvalidCustomerIdException("Invalid customer Id");
    	}
    	if(customers.containsKey(id)) {
    		return customers.get(id);
    	}
    	else
    		return null;
    }

    @Override
    public List<Customer> getAllCustomers() throws UnauthorizedException {
    	
    	if(loggedInUser == null || (!loggedInUser.getRole().equals("Administrator") && !loggedInUser.getRole().equals("Manager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }       
    	return customers.values().stream().collect(Collectors.toList());
    }

    @Override
    public String createCard() throws UnauthorizedException {
    	
    	if(loggedInUser == null || (!loggedInUser.getRole().equals("Administrator") && !loggedInUser.getRole().equals("Manager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
   	
        String customerCard = GenerateNumericString.getRandomString();
        
        return customerCard;
    }

    @Override
    public boolean attachCardToCustomer(String customerCard, Integer customerId) throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
    	
    	if(loggedInUser == null || (!loggedInUser.getRole().equals("Administrator") && !loggedInUser.getRole().equals("Manager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
    	
    	if(customerId==null || customerId <= 0) {
    		throw new InvalidCustomerIdException();
    	}
    	if(customerCard==null || customerCard.equals("") || customerCard.length()!=10) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	} 
    	try {
    		Integer.parseInt(customerCard);
    	}
    	catch(NumberFormatException nfe) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	}
    	if(customers.values().stream().anyMatch((c)->c.getCustomerCard().equals(customerCard))
    			|| !customers.containsKey(customerId)){
    		return false;
    	}
    	else {
    		customers.get(customerId).setCustomerCard(customerCard);
        	FileReaderAndWriter.CustomersWriter(customers);
    		return true;
    	}
    	
    }

    @Override
    public boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded) throws InvalidCustomerCardException, UnauthorizedException {
    	
    	if(loggedInUser == null || (!loggedInUser.getRole().equals("Administrator") && !loggedInUser.getRole().equals("Manager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
    	
    	if(customerCard == null || customerCard.length()!=10) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	}
    	
    	try {
    		Integer.parseInt(customerCard);
    	}
    	catch(NumberFormatException nfe) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	}
    	
    	Optional<it.polito.ezshop.model.Customer> optionalCustomer = customers.values().stream().filter((c)->c.getCustomerCard().equals(customerCard)).findFirst();
    		
    	if(optionalCustomer.isPresent()) {
    		it.polito.ezshop.model.Customer c = optionalCustomer.get();
    		if(c.getPoints()+pointsToBeAdded >= 0) {
    			c.setPoints(c.getPoints()+pointsToBeAdded);
            	FileReaderAndWriter.CustomersWriter(customers);
    		}
    	}
    	
    	return false;
    }

    @Override
    public Integer startSaleTransaction() throws UnauthorizedException {
    	
        return null;
    }

    @Override
    public boolean addProductToSale(Integer transactionId, String productCode, int amount) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteProductFromSale(Integer transactionId, String productCode, int amount) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean applyDiscountRateToProduct(Integer transactionId, String productCode, double discountRate) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidDiscountRateException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean applyDiscountRateToSale(Integer transactionId, double discountRate) throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
        return false;
    }

    @Override
    public int computePointsForSale(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
        return 0;
    }

    @Override
    public boolean endSaleTransaction(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteSaleTransaction(Integer saleNumber) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public SaleTransaction getSaleTransaction(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
        return null;
    }

    @Override
    public Integer startReturnTransaction(Integer saleNumber) throws /*InvalidTicketNumberException,*/InvalidTransactionIdException, UnauthorizedException {
        return null;
    }

    @Override
    public boolean returnProduct(Integer returnId, String productCode, int amount) throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean endReturnTransaction(Integer returnId, boolean commit) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public boolean deleteReturnTransaction(Integer returnId) throws InvalidTransactionIdException, UnauthorizedException {
        return false;
    }

    @Override
    public double receiveCashPayment(Integer ticketNumber, double cash) throws InvalidTransactionIdException, InvalidPaymentException, UnauthorizedException {
        return 0;
    }

    @Override
    public boolean receiveCreditCardPayment(Integer ticketNumber, String creditCard) throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
        return false;
    }

    @Override
    public double returnCashPayment(Integer returnId) throws InvalidTransactionIdException, UnauthorizedException {
        return 0;
    }

    @Override
    public double returnCreditCardPayment(Integer returnId, String creditCard) throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
        return 0;
    }

    @Override
    public boolean recordBalanceUpdate(double toBeAdded) throws UnauthorizedException {
        Integer maxid = 0;
        if(loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if(loggedInUser.getRole().equals("Administrator") || loggedInUser.getRole().equals("ShopManager")){
            if((currentBalance + toBeAdded) < 0)
                return false;
            else{
                for(BalanceOperation bo : balanceOperations.values()){
                    if(bo.getBalanceId()>maxid)
                        maxid = bo.getBalanceId();
                }
                BalanceOperation b = new BalanceOperationClass(maxid+1, LocalDate.now(), toBeAdded, toBeAdded<0 ? "DEBIT" : "CREDIT");
                this.balanceOperations.put(maxid+1, b);
                this.currentBalance+=toBeAdded;
                if(!FileReaderAndWriter.balanceOperationsWriter(this.balanceOperations))
                    return false;
                else
                    return true;
            } 
        }
        else{
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to) throws UnauthorizedException {
        if(loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");
        
        if(loggedInUser.getRole().equals("Administrator") || loggedInUser.getRole().equals("ShopManager")){
            //important, check which roles are able to to this operation since it is not specified in the interface!!

            List<BalanceOperation> l = new ArrayList<BalanceOperation>();

            if(from == null && to == null)
                l = this.balanceOperations.values().stream().collect(Collectors.toList());

            if(from !=null && to == null){
                for(BalanceOperation bo : balanceOperations.values()){
                    if(bo.getDate().isAfter(from) || bo.getDate().isEqual(from))
                        l.add(bo);
                }
            }
            if(from == null && to != null){
                for(BalanceOperation bo : balanceOperations.values()){
                    if(bo.getDate().isBefore(to) || bo.getDate().isEqual(to))
                        l.add(bo);
                }
            }
            if(from != null && to != null){
                for(BalanceOperation bo : balanceOperations.values()){
                    if((bo.getDate().isBefore(to) || bo.getDate().isEqual(to)) && (bo.getDate().isAfter(from) || bo.getDate().isEqual(from)))
                        l.add(bo);
                }
            } 

            return l;
        }
        else{
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public double computeBalance() throws UnauthorizedException {
        if(loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");
        
        if(loggedInUser.getRole().equals("Administrator") || loggedInUser.getRole().equals("ShopManager")){
            //important, check which roles are able to to this operation since it is not specified in the interface!!
            return this.currentBalance;
        }
        else{
            throw new UnauthorizedException("Function not available for the current user");
        }
    }
}
