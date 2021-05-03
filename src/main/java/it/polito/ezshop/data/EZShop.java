package it.polito.ezshop.data;

import it.polito.ezshop.exceptions.*;
import it.polito.ezshop.model.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import it.polito.ezshop.model.*;


public class EZShop implements EZShopInterface {


	HashMap<Integer ,it.polito.ezshop.model.Customer> customers;
	HashMap<String,LoyaltyCard> loyaltyCards;
    Map<Integer, User> users;
    User loggedInUser;

    public EZShop() {
        users = new HashMap<Integer, User>();
        loggedInUser = null;
        //load everything from file
    }

    @Override
    public void reset() {

    }

    @Override
    public Integer createUser(String username, String password, String role) throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
        Integer maxid = 1;

        if(password.equals("") || password == null)
            throw new InvalidPasswordException("Password should not be empty");

        if(username.equals("") || username == null)
            throw new InvalidUsernameException("Username should not be empty");

        for(User u : users.values()){
            if(u.getUsername().equals(username))
                return -1;
            else{
                if(u.getId()>maxid)
                    maxid=u.getId();
            }
        }

        if(role.equals("Administrator") || role.equals("Cashier") || role.equals("ShopManager")){
            User u = new UserClass(maxid+1, username, password, role);
            users.put(maxid+1, u);
            //WRITE ON FILE AND IF ERROR -1
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
            if(users.get(id) != null)
                users.remove(id);
                //WRITE ON FILE, IF ERROR, return FALSE
            else
                return false;
        }
        else{
            throw new UnauthorizedException("Function not available for the current user");
        }
        return true;
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

        if(id<=0 || id==null)
            throw new InvalidUserIdException("Invalid id");

        if(role.equals("Administrator") || role.equals("Cashier") || role.equals("ShopManager")){
            users.get(id).setRole(role);
            //file
            return true;
        }
        else
            throw new InvalidRoleException("Non existing role");
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
    	 if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null)
             throw new UnauthorizedException("Function not available for the current user");
    	
    	if(customers.values().stream().anyMatch((c)->{
        	if(c.getCustomerName().compareTo(customerName)==0) {
        		return true;
        	}
        	else
        		return false;
        
        })){
        	return -1;
        }
        else {       	
        	customers.put(customers.size()+1, new it.polito.ezshop.model.Customer(customers.size()+1,customerName));
        	return customers.size()+1;
        }
        
    }

    @Override
    public boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard) throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException, UnauthorizedException {
    	
    	if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null)
            throw new UnauthorizedException("Function not available for the current user"); 	
    	if(newCustomerName == null || newCustomerName=="") {
    		throw new InvalidCustomerNameException("Invalid customer Name");
    	}
    	if(newCustomerCard == null || newCustomerCard.length()!=10) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	}
    	
    	
    	Customer c = customers.get(id);
        
        c.setCustomerName(newCustomerName);
        c.setCustomerCard(newCustomerCard);
    	return false;
    }

    @Override
    public boolean deleteCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
    	
    	if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null)
            throw new UnauthorizedException("Function not available for the current user");
    	if(id==null || id<=0) {
    		throw new InvalidCustomerIdException("Invalid customer Id");
    	}
    	if(customers.containsKey(id)) {
    		customers.remove(id);
    		return true;
    	}
    	else
    		return false;
    }

    @Override
    public Customer getCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
    	
    	if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null)
            throw new UnauthorizedException("Function not available for the current user");
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
    	if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null)
            throw new UnauthorizedException("Function not available for the current user");
        
    	return customers.values().stream().collect(Collectors.toList());
        }

    @Override
    public String createCard() throws UnauthorizedException {
    	if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null)
            throw new UnauthorizedException("Function not available for the current user");
   	
        String customerCard = GenerateAlphaNumericString.getRandomString(10);
        while(loyaltyCards.containsKey(customerCard));
        loyaltyCards.put(customerCard, new it.polito.ezshop.model.LoyaltyCard(customerCard));
        return customerCard;
    }

    @Override
    public boolean attachCardToCustomer(String customerCard, Integer customerId) throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
    	if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null) {
            throw new UnauthorizedException("Function not available for the current user");
    	}
    	if(customerId==null || customerId <= 0) {
    		throw new InvalidCustomerIdException();
    	}
    	if(customerCard==null || customerCard=="" || customerCard.length()!=10) {
    		throw new InvalidCustomerCardException();
    	}   	
    	if(loyaltyCards.get(customerCard).getCustomer()==null && customers.containsKey(customerId)) {
    		customers.get(customerId).setCustomerCard(customerCard);
    		loyaltyCards.get(customerCard).setCustomer(customers.get(customerId));
    		return true;
    	}
    	else
    		return false;
    }

    @Override
    public boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded) throws InvalidCustomerCardException, UnauthorizedException {
    	if(loggedInUser.getRole() != "Administrator" || loggedInUser.getRole() != "Manager"|| loggedInUser.getRole() != "Cashier"||loggedInUser == null) {
            throw new UnauthorizedException("Function not available for the current user");
    	}
    	if(customerCard == null || customerCard.length()!=10) {
    		throw new InvalidCustomerCardException("Invalid customer Card");
    	}
    	
    	if(!loyaltyCards.containsKey(customerCard)) {
    		return false;
    	}
    	else if(loyaltyCards.get(customerCard).getPoints()+pointsToBeAdded < 0) {
    		return false;
    	}
    	else {
    		loyaltyCards.get(customerCard).setPoints(loyaltyCards.get(customerCard).getPoints()+pointsToBeAdded);
    		return true;
    	}
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
        return false;
    }

    @Override
    public List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to) throws UnauthorizedException {
        return null;
    }

    @Override
    public double computeBalance() throws UnauthorizedException {
        return 0;
    }
}
