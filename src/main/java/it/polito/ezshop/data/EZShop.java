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
    private HashMap<Integer, it.polito.ezshop.model.Customer> customers;
    private Map<Integer, User> users;
    private Map<Integer, BalanceOperation> balanceOperations;
    private Map<Integer, ReturnTransaction> returns;
    private Map<Integer, SaleTransactionClass> sales;
    private Map<Integer, ProductType> inventory;
    private Map<Integer, OrderClass> orders;
    public Map<String,CreditCardClass> creditCards;
    private User loggedInUser;
    private double currentBalance;

    public EZShop() {

        // user init
        this.users = FileReaderAndWriter.UsersReader();
        this.loggedInUser = null;

        // balance init
        this.balanceOperations = FileReaderAndWriter.BalanceOperationsReader();
        this.currentBalance = 0;
        for (BalanceOperation bo : balanceOperations.values()) // after having loaded operations from file, update the
                                                               // current balance
            this.currentBalance += bo.getMoney();

        // customers init
        this.customers = FileReaderAndWriter.CustomersReader();

        // sales init
        this.sales = FileReaderAndWriter.SaleTransactionsReader(); // load all transactions
        List<TicketEntryClass> entries = FileReaderAndWriter.ticketEntriesReader(); // load all entries

        // for each transaction, create a new list of ticketentries with transaction id
        // = transaction considered and set it
        // the list with ALL entries is local to the constructor, after that each entry
        // has been assigned to the right transaction, will be deleted

        for (SaleTransaction t : sales.values()) {
            List<TicketEntry> e = entries.stream().filter((e1) -> {
                return e1.getTransactionId() == t.getTicketNumber();
            }).collect(Collectors.toList());
            t.setEntries(e);
        }

        // inventory init
        this.inventory = FileReaderAndWriter.InventoryReader();

        // orders init
        this.orders = FileReaderAndWriter.OrdersReader(); // load all orders

        // returns init
        this.returns = FileReaderAndWriter.ReturnsReader();

        //creditCards init
        this.creditCards = FileReaderAndWriter.CreditCardsReader();
    }

    @Override
    public void reset() {

        this.users.clear();
        FileReaderAndWriter.UsersWriter(users);
        this.loggedInUser = null;

        this.balanceOperations.clear();
        FileReaderAndWriter.balanceOperationsWriter(balanceOperations);
        this.currentBalance = 0;

        this.customers.clear();
        FileReaderAndWriter.CustomersWriter(customers);

        this.sales.clear();
        FileReaderAndWriter.saletransactionsWriter(sales);
        FileReaderAndWriter.ticketEntriesWriter(new ArrayList<TicketEntryClass>());

        this.orders.clear();
        FileReaderAndWriter.OrdersWriter(orders);

        this.inventory.clear();
        FileReaderAndWriter.InventoryWriter(inventory);

        //this report the files in the original form given by the professor
        this.creditCards.clear();
        FileReaderAndWriter.CreditCardsWriter(creditCards);

        this.returns.clear();
        FileReaderAndWriter.ReturnsWriter(returns);

    }

    @Override
    public Integer createUser(String username, String password, String role)
            throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
        Integer maxid = 0;

        if (password == null ||password.equals(""))
            throw new InvalidPasswordException("Password should not be empty");

        if (username == null ||username.equals(""))
            throw new InvalidUsernameException("Username should not be empty");

        for (User u : users.values()) {
            if (u.getUsername().equals(username)) {
                return -1;
            } else {
                if (u.getId() > maxid)
                    maxid = u.getId();
            }
        }

        if (role!=null && (role.equals("Administrator") || role.equals("Cashier") || role.equals("ShopManager"))) {
            User u = new UserClass(maxid + 1, username, password, role);
            users.put(maxid + 1, u);
            if (!FileReaderAndWriter.UsersWriter(users))
                return -1;
        } else {
            throw new InvalidRoleException("Non existing role");
        }

        return maxid + 1;
    }

    @Override
    public boolean deleteUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
        if (id == null || id <= 0)
            throw new InvalidUserIdException("Invalid id");

        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if (loggedInUser.getRole().equals("Administrator")) {
            if (users.get(id) != null) {
                users.remove(id);
                if (!FileReaderAndWriter.UsersWriter(users))
                    return false;
                else
                    return true;
            } else
                return false;
        } else {
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public List<User> getAllUsers() throws UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if (loggedInUser.getRole().equals("Administrator")) {
            List<User> u = new ArrayList<User>();
            for (User us : users.values())
                u.add(us);
            return u;
        } else {
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public User getUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if (id == null ||id <= 0)
            throw new InvalidUserIdException("Invalid id");

        if (loggedInUser.getRole().equals("Administrator")) {
            return users.get(id);
        } else {
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public boolean updateUserRights(Integer id, String role)
            throws InvalidUserIdException, InvalidRoleException, UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if (loggedInUser.getRole().compareTo("Administrator") != 0)
            throw new UnauthorizedException("Function not available for the current user");

        if (id == null ||id <= 0)
            throw new InvalidUserIdException("Invalid id");

        if (role!=null && (role.equals("Administrator") || role.equals("Cashier") || role.equals("ShopManager"))) {
            if (users.get(id) == null)
                return false;
            else {
                users.get(id).setRole(role);
                if (FileReaderAndWriter.UsersWriter(users))
                    return true;
            }
        } else
            throw new InvalidRoleException("Non existing role");

        return false;
    }

    @Override
    public User login(String username, String password) throws InvalidUsernameException, InvalidPasswordException {
        if (password == null ||password.equals(""))
            throw new InvalidPasswordException("Password should not be empty");

        if (username == null ||username.equals(""))
            throw new InvalidUsernameException("Username should not be empty");

        for (User us : users.values()) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                loggedInUser = us;
                return loggedInUser;
            }
        }
        loggedInUser = null;
        return null;
    }

    @Override
    public boolean logout() {
        if (loggedInUser == null)
            return false;
        loggedInUser = null;
        return true;
    }

    @Override
    public Integer createProductType(String description, String productCode, double pricePerUnit, String note)
            throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
            UnauthorizedException {

        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (pricePerUnit <= 0)
            throw new InvalidPricePerUnitException("The price per unit is not valid.");

        if (description == null)
            throw new InvalidProductDescriptionException("There is no description for the product.");

        if (description.equals(""))
            throw new InvalidProductDescriptionException("The description of the product is empty.");

        if (!ProductTypeClass.VerifyBarCode(productCode))
            throw new InvalidProductCodeException("The product code is not valid.");

        if (getProductTypeByBarCode(productCode) != null)
            return -1;

        try {
            int[] maxID = { 0 };
            inventory.forEach((k, v) -> {
                if (k > maxID[0]) {
                    maxID[0] = k;
                }
            });
            ProductTypeClass newItem = new ProductTypeClass(maxID[0] + 1, description, productCode, pricePerUnit, note);
            inventory.put(newItem.getId(), newItem);
            if (FileReaderAndWriter.InventoryWriter(inventory)) {
                return newItem.getId();
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote)
            throws InvalidProductIdException, InvalidProductDescriptionException, InvalidProductCodeException,
            InvalidPricePerUnitException, UnauthorizedException {

        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (id == null || id <= 0)
            throw new InvalidProductIdException("The id provided is not valid.");

        if (newDescription == null || newDescription.equals(""))
            throw new InvalidProductDescriptionException("The new description is null or empty.");

        if (newPrice <= 0)
            throw new InvalidPricePerUnitException("The new price is less than or equal to 0.");

        if (!ProductTypeClass.VerifyBarCode(newCode))
            throw new InvalidProductCodeException("The new barcode is not valid.");

        ProductType productToUpdate = inventory.get(id);

        if (productToUpdate == null)
            return false;

        if (getProductTypeByBarCode(newCode) != null && getProductTypeByBarCode(newCode) != productToUpdate)
            return false;

        productToUpdate.setProductDescription(newDescription);
        productToUpdate.setBarCode(newCode);
        productToUpdate.setPricePerUnit(newPrice);
        productToUpdate.setNote(newNote);

        if (FileReaderAndWriter.InventoryWriter(inventory)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProductType(Integer id) throws InvalidProductIdException, UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (id == null || id <= 0)
            throw new InvalidProductIdException("The id provided is not valid.");

        try {
            inventory.remove(id);
            if (FileReaderAndWriter.InventoryWriter(inventory)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ProductType> getAllProductTypes() throws UnauthorizedException {

        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        List<ProductType> allProductTypes = new ArrayList<ProductType>();

        inventory.forEach((k, v) -> {
            allProductTypes.add(v);
        });
        return allProductTypes;
    }

    @Override
    public ProductType getProductTypeByBarCode(String barCode)
            throws InvalidProductCodeException, UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (!ProductTypeClass.VerifyBarCode(barCode))
            throw new InvalidProductCodeException("The barCode provided is not valid.");

        for (ProductType p : getAllProductTypes())
            if (p.getBarCode().equals(barCode))
                return p;

        return null;

    }

    @Override
    public List<ProductType> getProductTypesByDescription(String description) throws UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        List<ProductType> allProducts = getAllProductTypes();
        allProducts.removeIf((pt) -> {
            if (description == null) {
                return false;
            }
            if (!pt.getProductDescription().contains(description)) {
                return true;
            }
            return false;
        });

        return allProducts;
    }

    @Override
    public boolean updateQuantity(Integer productId, int toBeAdded)
            throws InvalidProductIdException, UnauthorizedException {
        if (this.loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (this.loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (productId <= 0 || productId == null)
            throw new InvalidProductIdException("Invalid Product ID.");

        ProductType productToUpdate = inventory.get(productId);
        if (productToUpdate == null || toBeAdded + productToUpdate.getQuantity() < 0
                || productToUpdate.getLocation() == null || productToUpdate.getLocation().isEmpty())
            return false;

        inventory.get(productId).setQuantity(productToUpdate.getQuantity() + toBeAdded);

        if (FileReaderAndWriter.InventoryWriter(inventory)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePosition(Integer productId, String newPos)
            throws InvalidProductIdException, InvalidLocationException, UnauthorizedException {
        if (this.loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (this.loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (productId <= 0 || productId == null)
            throw new InvalidProductIdException("Invalid Product ID.");

        if (!newPos.matches("[0-9]+-[a-zA-Z]+-[0-9]"))
            throw new InvalidLocationException("The location is in an invalid format.");
        
        for (ProductType product : inventory.values()){
            if(product.getLocation()!=null)
                if (product.getLocation().equals(newPos))
                    return false;
        }

        ProductType productToUpdate = inventory.get(productId);
        if (productToUpdate == null)
            return false;

        inventory.get(productId).setLocation(newPos);

        if (FileReaderAndWriter.InventoryWriter(inventory)) {
            return true;
        }
        return false;
    }

    @Override
    public Integer issueOrder(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException,
            InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
        if (this.loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (this.loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (quantity <= 0)
            throw new InvalidQuantityException("Quantity must be a positive integer.");

        if (pricePerUnit <= 0)
            throw new InvalidPricePerUnitException("Price per unit must be a positive integer.");

        if (!ProductTypeClass.VerifyBarCode(productCode))
            throw new InvalidProductCodeException("Invalid product code.");

        ProductType productToOrder = getProductTypeByBarCode(productCode);
        if (productToOrder == null)
            return -1;

        try {
            int[] maxID = { 0 };
            this.orders.forEach((k, v) -> {
                if (k > maxID[0])
                    maxID[0] = k;
            });
            OrderClass newOrder = new OrderClass(maxID[0] + 1, null, productCode, pricePerUnit, quantity, "ISSUED");
            this.orders.put(newOrder.getOrderId(), newOrder);
            FileReaderAndWriter.OrdersWriter(this.orders);
            return newOrder.getOrderId();
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer payOrderFor(String productCode, int quantity, double pricePerUnit)
            throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException,
            UnauthorizedException {
        if (this.loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (this.loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (quantity <= 0)
            throw new InvalidQuantityException("Quantity must be a positive integer.");

        if (pricePerUnit <= 0)
            throw new InvalidPricePerUnitException("Price per unit must be a positive integer.");

        if (!ProductTypeClass.VerifyBarCode(productCode))
            throw new InvalidProductCodeException("Invalid product code.");

        if (this.currentBalance < pricePerUnit * quantity)
            return -1;

        ProductType productToOrder = getProductTypeByBarCode(productCode);
        if (productToOrder == null)
            return -1;

        try {
            int[] maxID = { 0 };
            this.orders.forEach((k, v) -> {
                if (k > maxID[0])
                    maxID[0] = k;
            });

            OrderClass newOrder = new OrderClass(maxID[0] + 1, null, productCode, pricePerUnit, quantity, "PAYED");
            this.orders.put(newOrder.getOrderId(), newOrder);
            FileReaderAndWriter.OrdersWriter(this.orders);
            this.recordBalanceUpdate(pricePerUnit * quantity * -1);
            return newOrder.getOrderId();
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean payOrder(Integer orderId) throws InvalidOrderIdException, UnauthorizedException {
        if (this.loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (this.loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (orderId <= 0 || orderId == null)
            throw new InvalidOrderIdException("Id must be a positive integer.");

        Order order = orders.get(orderId);
        if (order == null || !order.getStatus().equals("ISSUED"))
            return false;

        this.orders.get(orderId).setStatus("PAYED");
        this.recordBalanceUpdate(order.getPricePerUnit() * order.getQuantity() * -1);
        FileReaderAndWriter.OrdersWriter(this.orders);

        return true;
    }

    @Override
    public boolean recordOrderArrival(Integer orderId)
            throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException {
        if (this.loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (this.loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        if (orderId <= 0 || orderId == null)
            throw new InvalidOrderIdException("Id must be a positive integer.");

        OrderClass order = orders.get(orderId);

        ProductType product;
		try {
			product = this.getProductTypeByBarCode(order.getProductCode());
			if (product.getLocation() == null || product.getLocation().isBlank())
	            throw new InvalidLocationException("The product must have a location.");

	        if (order == null || !order.getStatus().equals("PAYED"))
	            return false;
	        
	        this.orders.get(orderId).setStatus("COMPLETED");
	        
	        this.updateQuantity(product.getId(), order.getQuantity());
			
	        FileReaderAndWriter.OrdersWriter(this.orders);
	        
	        return true;
	        
		} catch (InvalidProductCodeException e) {
			return false;
		} 
		catch (InvalidProductIdException e) {
			return false;
		}
		
    }

    @Override
    public List<Order> getAllOrders() throws UnauthorizedException {
        if (this.loggedInUser == null)
            throw new UnauthorizedException("No one is logged in.");

        if (this.loggedInUser.getRole().equals("Cashier"))
            throw new UnauthorizedException("Function not available for the current user.");

        return orders.values().stream().collect(Collectors.toList());
    }

    @Override
    public Integer defineCustomer(String customerName) throws InvalidCustomerNameException, UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        
        if(customerName==null || customerName.isEmpty()) {
        	throw new InvalidCustomerNameException("customer name is not valid");
        }
        if (customers.values().stream().anyMatch((c) -> c.getCustomerName().equals(customerName))) {
            return -1;
        } else {
            Optional<Integer> max = customers.keySet().stream().max((n1, n2) -> n1 - n2);
            if (max.isPresent()) {
                customers.put(max.get() + 1, new it.polito.ezshop.model.Customer(max.get() + 1, customerName, "", 0));
                FileReaderAndWriter.CustomersWriter(customers);
                return max.get() + 1;
            } else {
                customers.put(1, new it.polito.ezshop.model.Customer(1, customerName, "", 0));
                FileReaderAndWriter.CustomersWriter(customers);
                return 1;

            }
        }

    }

    @Override
    public boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard)
            throws InvalidCustomerNameException, InvalidCustomerCardException, 
            UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (newCustomerName == null || newCustomerName.equals("")) {
            throw new InvalidCustomerNameException("Invalid customer Name");
        }

        if (!customers.containsKey(id)) {
            return false;
        }

        if (newCustomerCard == null) {
            customers.get(id).setCustomerName(newCustomerName);
            FileReaderAndWriter.CustomersWriter(customers);
            return true;
        }

        if (newCustomerCard.isEmpty()) {
            customers.get(id).setCustomerName(newCustomerName);
            customers.get(id).setCustomerCard("");
            FileReaderAndWriter.CustomersWriter(customers);
            return true;
        }

        if (newCustomerCard.length() != 10) {
            throw new InvalidCustomerCardException("Invalid customer Card");
        }

        try {
            Double.parseDouble(newCustomerCard);
        } catch (NumberFormatException nfe) {
            throw new InvalidCustomerCardException("Invalid customer Card");
        }

        if (customers.values().stream().filter((c) -> c.getId()!=id).anyMatch((c) -> c.getCustomerCard().equals(newCustomerCard))) {
            return false;
        }
        customers.get(id).setCustomerName(newCustomerName);
        customers.get(id).setCustomerCard(newCustomerCard);
        FileReaderAndWriter.CustomersWriter(customers);
        return true;
    }

    @Override
    public boolean deleteCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (id == null || id <= 0) {
            throw new InvalidCustomerIdException("Invalid customer Id");
        }

        if (!customers.containsKey(id))
            return false;
        else {
            customers.remove(id);
            FileReaderAndWriter.CustomersWriter(customers);
            return true;
        }
    }

    @Override
    public Customer getCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if (id == null || id <= 0) {
            throw new InvalidCustomerIdException("Invalid customer Id");
        }
        if (customers.containsKey(id)) {
            return customers.get(id);
        } else
            return null;
    }

    @Override
    public List<Customer> getAllCustomers() throws UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        return customers.values().stream().collect(Collectors.toList());
    }

    @Override
    public String createCard() throws UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        String customerCard = GenerateNumericString.getRandomString();

        return customerCard;
    }

    @Override
    public boolean attachCardToCustomer(String customerCard, Integer customerId)
            throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if (customerId == null || customerId <= 0) {
            throw new InvalidCustomerIdException();
        }
        if (customerCard == null || customerCard.equals("") || customerCard.length() != 10) {
            throw new InvalidCustomerCardException("Invalid customer Card");
        }
        try {
            Double.parseDouble(customerCard);
        } catch (NumberFormatException nfe) {
            throw new InvalidCustomerCardException("Invalid customer Card");
        }
        if (customers.values().stream().filter((c)-> c.getId()!= customerId).anyMatch((c) -> c.getCustomerCard().equals(customerCard))
                || !customers.containsKey(customerId)) {
            return false;
        } else {
            customers.get(customerId).setCustomerCard(customerCard);
            FileReaderAndWriter.CustomersWriter(customers);
            return true;
        }

    }

    @Override
    public boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded)
            throws InvalidCustomerCardException, UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if (customerCard == null || customerCard.length() != 10) {
            throw new InvalidCustomerCardException("Invalid customer Card");
        }

        try {
            Double.parseDouble(customerCard);
        } catch (NumberFormatException nfe) {
            throw new InvalidCustomerCardException("Invalid customer Card");
        }

        Optional<it.polito.ezshop.model.Customer> optionalCustomer = customers.values().stream()
                .filter((c) -> c.getCustomerCard().equals(customerCard)).findFirst();

        if (optionalCustomer.isPresent()) {
            it.polito.ezshop.model.Customer c = optionalCustomer.get();
            if (c.getPoints() + pointsToBeAdded >= 0) {
                c.setPoints(c.getPoints() + pointsToBeAdded);
                FileReaderAndWriter.CustomersWriter(customers);
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer startSaleTransaction() throws UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier")))
            throw new UnauthorizedException("Function not available for the current user");

        //by default transaction, when is built (not loaded from file) is open
        if (this.sales.isEmpty()) {
            this.sales.put(1, new SaleTransactionClass(1));
            return 1;
        } else {
            Optional<Integer> id = this.sales.keySet().stream().max((i, j) -> i - j);
            this.sales.put(id.get()+1, new SaleTransactionClass(id.get()+1));
            return id.get() + 1;
        }
    }

    @Override
    public boolean addProductToSale(Integer transactionId, String productCode, int amount)
            throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
            UnauthorizedException {

        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if (transactionId == null ||transactionId <= 0)
            throw new InvalidTransactionIdException("Wrong transaction id");

        if (amount < 0)
            throw new InvalidQuantityException("Quantity less than 0");
        
        if(productCode == null || productCode.equals("") || ProductTypeClass.VerifyBarCode(productCode) == false)
            throw new InvalidProductCodeException("Not a valid product");
        
        if(sales.get(transactionId).getState().compareTo("Open") != 0)
            return false;

        if (getProductTypeByBarCode(productCode) == null)
            return false;

        if (getProductTypeByBarCode(productCode).getQuantity() < amount)
            return false;

        List<TicketEntry> x = sales.get(transactionId).getEntries();
        TicketEntry t = null;

        for(TicketEntry e : x)
            if(e.getBarCode().equals(productCode)){
                t = e;
            }

        if (t == null) { // product not present
            t = new TicketEntryClass(transactionId, productCode,
                    getProductTypeByBarCode(productCode).getProductDescription(), amount,
                    getProductTypeByBarCode(productCode).getPricePerUnit(), 0D);
            x.add(t);
            sales.get(transactionId).setEntries(x);
        } else { // product already present
            t.setAmount(t.getAmount() + amount);
        }

        sales.get(transactionId).setPrice(sales.get(transactionId).getPrice()
                + (amount * getProductTypeByBarCode(productCode).getPricePerUnit()));
        getProductTypeByBarCode(productCode).setQuantity(getProductTypeByBarCode(productCode).getQuantity() - amount);

        return true;
    }

    @Override
    public boolean deleteProductFromSale(Integer transactionId, String productCode, int amount)
            throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
            UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if (transactionId == null || transactionId <= 0)
            throw new InvalidTransactionIdException("Wrong transaction id");

        if (amount < 0)
            throw new InvalidQuantityException("Quantity less than 0");
        
        if(productCode == null || productCode.equals("") || ProductTypeClass.VerifyBarCode(productCode) == false)
            throw new InvalidProductCodeException("Not a valid product");
        
        if(sales.get(transactionId).getState().compareTo("Open") != 0)
            return false;

        if (getProductTypeByBarCode(productCode) == null)
            return false;

        List<TicketEntry> x = sales.get(transactionId).getEntries();

        if (x == null) // if there are no entries, it is impossible to delete a product
            return false;

        TicketEntry t = null;

        for(TicketEntry e : x)
            if(e.getBarCode().equals(productCode))
                t = e;

        if (t == null) // if the product is note present in the entries, it is impossible to delete a
                       // product
            return false;

        if (t.getAmount() < amount) // if the amount already added are less the the desidered quantity to delete, it
                                    // is not possible to delete
            return false;

        if (t.getAmount() == amount) {
            x.remove(t);
        } 
        else {
            t.setAmount(t.getAmount() - amount);
        }

        sales.get(transactionId).setPrice(sales.get(transactionId).getPrice()- (amount * getProductTypeByBarCode(productCode).getPricePerUnit()));
        getProductTypeByBarCode(productCode).setQuantity(getProductTypeByBarCode(productCode).getQuantity() + amount);

        return true;
    }

    @Override
    public boolean applyDiscountRateToProduct(Integer transactionId, String productCode, double discountRate)
            throws InvalidTransactionIdException, InvalidProductCodeException, InvalidDiscountRateException,
            UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if(discountRate < 0 || discountRate >= 1)
            throw new InvalidDiscountRateException("Discount not valid");

        if(productCode == null || productCode == "" || ProductTypeClass.VerifyBarCode(productCode) == false)
            throw new InvalidProductCodeException("Not a valid product");

        if(transactionId == null || transactionId <= 0)
            throw new InvalidTransactionIdException("Wrong transaction id");

        if(sales.get(transactionId).getState().compareTo("Open") != 0)
            return false;

        List<TicketEntry> te = sales.get(transactionId).getEntries();

        for(TicketEntry t : te){
            if(t.getBarCode().equals(productCode)){
                t.setDiscountRate(discountRate);
                Double entryprice = t.getAmount() * t.getPricePerUnit();
                Double reduction = entryprice * discountRate;
                sales.get(transactionId).setPrice(sales.get(transactionId).getPrice() - reduction);
                return true;
            }
        }  
        return false;
    }

    @Override
    public boolean applyDiscountRateToSale(Integer transactionId, double discountRate)
            throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if(transactionId == null ||transactionId <= 0)
            throw new InvalidTransactionIdException("Wrong transaction id");

        if(discountRate < 0 || discountRate >= 1)
            throw new InvalidDiscountRateException("Discount not valid");
        
        SaleTransactionClass t = sales.get(transactionId);

        if(t.getState().compareTo("Paid") == 0)
            return false;
        else{
            t.setDiscountRate(discountRate);
            t.setPrice(t.getPrice() - (t.getPrice()*discountRate));
            if(!FileReaderAndWriter.saletransactionsWriter(sales))
                return false;
            return true;
        }
    }

    @Override
    public int computePointsForSale(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if(transactionId == null ||transactionId <= 0)
            throw new InvalidTransactionIdException("Wrong transaction id");

        SaleTransaction t = sales.get(transactionId);

        if(t==null){
            return -1;
        }
        else{
            return (int) t.getPrice() / 10;
        }
    }

    @Override
    public boolean endSaleTransaction(Integer transactionId)
            throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if(transactionId == null ||transactionId <= 0)
            throw new InvalidTransactionIdException("Wrong transaction id");

        SaleTransactionClass s = this.sales.get(transactionId);

        if(s == null)
            return false;

        if(!s.getState().equals("Open"))
             return false;
        
        s.setState("Closed");

        //update the persistent components after this operation
        if(!FileReaderAndWriter.saletransactionsWriter(sales))
            return false;

        List<TicketEntryClass> allentries = new ArrayList<TicketEntryClass>();

        for(SaleTransaction sa : this.sales.values())
            for(TicketEntry en : sa.getEntries()){
                TicketEntryClass ec = new TicketEntryClass(sa.getTicketNumber(), en.getBarCode(), en.getProductDescription(), en.getAmount(), en.getPricePerUnit(), en.getDiscountRate());
                allentries.add(ec);
            }

        if(!FileReaderAndWriter.ticketEntriesWriter(allentries))
            return false;
        if(!FileReaderAndWriter.InventoryWriter(inventory)) 
            return false;
            
        return true;
    }

    @Override
    public boolean deleteSaleTransaction(Integer saleNumber)
            throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if(saleNumber == null || saleNumber <= 0)
            throw new InvalidTransactionIdException("Wrong transaction id");

        SaleTransactionClass s = this.sales.get(saleNumber);

        if(s == null)
            return false;

        if(s.getState().equals("Paid"))
            return false;
        
        //for each ticket entry of the transaction that is going to be deleted, re-add the quantity in the inventory
        for(TicketEntry t : s.getEntries()){
            ProductType pt = null;
            try {
                pt = getProductTypeByBarCode(t.getBarCode());
            } 
            catch (InvalidProductCodeException e) {
                e.printStackTrace();
            }
            if(pt != null)
                pt.setQuantity(pt.getQuantity()+t.getAmount());
        }
        
        //cancel the sale from the main memory
        this.sales.remove(saleNumber);

        //update the persistent components after this operation
        if(!FileReaderAndWriter.saletransactionsWriter(sales))
            return false;

        List<TicketEntryClass> allentries = new ArrayList<TicketEntryClass>();

        for(SaleTransaction sa : this.sales.values())
            for(TicketEntry en : sa.getEntries()){
                TicketEntryClass ec = new TicketEntryClass(sa.getTicketNumber(), en.getBarCode(), en.getProductDescription(), en.getAmount(), en.getPricePerUnit(), en.getDiscountRate());
                allentries.add(ec);
            }

        if(!FileReaderAndWriter.ticketEntriesWriter(allentries))
            return false;
        
        if(!FileReaderAndWriter.InventoryWriter(inventory))
            return false;
        
        return true;
    }

    @Override
    public SaleTransaction getSaleTransaction(Integer transactionId)
            throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (transactionId == null || transactionId <= 0)
            throw new InvalidTransactionIdException("Transaction id is wrong");
        
        SaleTransactionClass s = sales.get(transactionId);

        if(s == null)
            return null;
        else
            if(s.getState().equals("Closed") || s.getState().equals("Paid")) //i added pay since it was written in a slack answer
                return s;
            else
                return null;
    }

    @Override
    public Integer startReturnTransaction(Integer transactionID)
            throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }

        if (transactionID == null || transactionID <= 0) {
            throw new InvalidTransactionIdException("The transaction ID provided is not valid.");
        }

        int[] maxID = { 0 };
        this.returns.forEach((k, v) -> {
            if (k > maxID[0])
                maxID[0] = k;
        });

        if (sales.containsKey(transactionID)) {
            ReturnTransaction rt = new ReturnTransaction(maxID[0] + 1, transactionID);
            returns.put(maxID[0] + 1, rt);
            return maxID[0] + 1;
        }

        return -1;
    }

    @Override
    public boolean returnProduct(Integer returnId, String productCode, int amount) throws InvalidTransactionIdException,
            InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (!ProductTypeClass.VerifyBarCode(productCode)) {
            throw new InvalidProductCodeException("The product code is invalid.");
        }
        if (amount <= 0) {
            throw new InvalidQuantityException("The quantity is less than or equal to 0.");
        }
        if (returnId == null || returnId <= 0) {
            throw new InvalidTransactionIdException("The ID of the return transaction is invalid.");
        }

        ProductType rp = getProductTypeByBarCode(productCode);
        ReturnTransaction rt = this.returns.get(returnId);
        SaleTransaction st = getSaleTransaction(rt.getSaleTransactionID());

        if (rp != null && rt != null && st != null) {
            List<TicketEntry> listTE = st.getEntries().stream().filter((ticket) -> {
                if (ticket.getBarCode() == productCode) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            /* List<TicketEntry> listTE = st.getEntries();
            listTE.removeIf((ticket) -> {
                if (ticket.getBarCode() != productCode) {			//I had to modify this part
                    return true;									//since the original version was going 
                }													//to remove entries, with removeIf(), from the original list
                return false;										//instead of creating a new one
            });*/
            if (listTE.size() > 0) {
                TicketEntry te = listTE.get(0);
                if (te.getAmount() >= amount) {
                    rt.getReturnedProduct().put(rp.getId(), amount);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean endReturnTransaction(Integer returnId, boolean commit)
            throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (returnId == null || returnId <= 0) {
            throw new InvalidTransactionIdException("The transaction ID is not valid.");
        }

        ReturnTransaction rt = returns.get(returnId);

        if(rt == null){
            return false;
        }

        SaleTransaction st = sales.get(rt.getSaleTransactionID());
        List<TicketEntry> ListTE = st.getEntries();
        Double returnedPrice = 0.0;

        for (Integer ptID : rt.getReturnedProduct().keySet()) {
            for(TicketEntry te : ListTE){
                if(te.getBarCode().equals(inventory.get(ptID).getBarCode())){
                    te.setAmount(te.getAmount() - rt.getReturnedProduct().get(ptID));
                    returnedPrice = returnedPrice + (rt.getReturnedProduct().get(ptID) * te.getPricePerUnit());
                    inventory.get(ptID).setQuantity(inventory.get(ptID).getQuantity() + rt.getReturnedProduct().get(ptID));
                }
            }
        }

        st.setPrice(st.getPrice() - returnedPrice);

        rt.setStatus("Completed");

        if(commit){
            try {
                FileReaderAndWriter.ReturnsWriter(this.returns);
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean deleteReturnTransaction(Integer returnId)
            throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (returnId == null || returnId <= 0) {
            throw new InvalidTransactionIdException("The ID provided is not valid");
        }

        ReturnTransaction rt = returns.get(returnId);

        if(rt != null) {
            if(rt.getStatus().equals("Payed")) {
                return false;
            }
            SaleTransaction st = sales.get(rt.getSaleTransactionID());
            List<TicketEntry> ListTE = st.getEntries();
            Double returnedPrice = 0.0;
    
            for (Integer ptID : rt.getReturnedProduct().keySet()) {
                for(TicketEntry te : ListTE){
                    if(te.getBarCode().equals(inventory.get(ptID).getBarCode())){
                        te.setAmount(te.getAmount() + rt.getReturnedProduct().get(ptID));
                        returnedPrice = returnedPrice + (rt.getReturnedProduct().get(ptID) * te.getPricePerUnit());
                        inventory.get(ptID).setQuantity(inventory.get(ptID).getQuantity() - rt.getReturnedProduct().get(ptID));
                    }
                }
            }

            st.setPrice(st.getPrice() + returnedPrice);
            returns.remove(returnId);

            try {
                FileReaderAndWriter.ReturnsWriter(returns);
                return true;
            } catch (Exception e) {
                return false;
            }
            
        }

        return false;
    }

    @Override
    public double receiveCashPayment(Integer transactionId, double cash)
            throws InvalidTransactionIdException, InvalidPaymentException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (transactionId == null || transactionId <= 0) {
        	throw new InvalidTransactionIdException("Transaction id is wrong");
        }
        if (cash <= 0) {
        	throw new InvalidPaymentException("money not provided");
        }
        if(sales.containsKey(transactionId) && cash >= sales.get(transactionId).getPrice() && sales.get(transactionId).getState().equals("Closed")) {
        	if(recordBalanceUpdate(sales.get(transactionId).getPrice())){
                sales.get(transactionId).setState("Paid");
                if(!FileReaderAndWriter.saletransactionsWriter(sales))
                    return -1;
                return cash-sales.get(transactionId).getPrice();
            }
        	else 
        		return -1;
        }
        else
        	return -1;
    }

    @Override
    public boolean receiveCreditCardPayment(Integer transactionId, String creditCard)
            throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (transactionId == null || transactionId <= 0) {
        	throw new InvalidTransactionIdException("Transaction id is wrong");
        }
        if (creditCard == null || creditCard.isEmpty() || !GFG.checkLuhn(creditCard)) {
        	throw new InvalidCreditCardException("credit card number is not valid");
        }
        
        if(creditCards.containsKey(creditCard) && sales.containsKey(transactionId) && sales.get(transactionId).getState().equals("Closed") && creditCards.get(creditCard).getBalance() >= sales.get(transactionId).getPrice()) {
        	if(recordBalanceUpdate(sales.get(transactionId).getPrice())) {
        		creditCards.get(creditCard).setBalance(creditCards.get(creditCard).getBalance()-sales.get(transactionId).getPrice());
        		FileReaderAndWriter.CreditCardsWriter(creditCards);
                sales.get(transactionId).setState("Paid");
                if(!FileReaderAndWriter.saletransactionsWriter(sales))
                    return false;
        		return true;
        	}
        	else
        		return false;
        }
        return false;
    }

    @Override
    public double returnCashPayment(Integer returnId) throws InvalidTransactionIdException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (returnId == null || returnId <= 0) {
        	throw new InvalidTransactionIdException("Return transaction id is wrong");
        }
        if(returns.containsKey(returnId) && returns.get(returnId).getStatus().equals("Completed")) {
        	
        	double sum = returns.get(returnId).getReturnedProduct().entrySet().stream()
        	.mapToDouble((entry) -> {
        		Integer pId = entry.getKey();
        		Integer amount = entry.getValue();
        		return inventory.get(pId).getPricePerUnit()*amount;
        	}).sum();
        	
        	if(recordBalanceUpdate(sum*-1)) {
        		returns.get(returnId).setStatus("Payed");
        		FileReaderAndWriter.ReturnsWriter(returns);
                return sum;
        	}
        }
        return -1;
    }

    @Override
    public double returnCreditCardPayment(Integer returnId, String creditCard)
            throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
        if (loggedInUser == null || (!loggedInUser.getRole().equals("Administrator")
                && !loggedInUser.getRole().equals("ShopManager") && !loggedInUser.getRole().equals("Cashier"))) {
            throw new UnauthorizedException("Function not available for the current user");
        }
        if (returnId == null || returnId <= 0) {
        	throw new InvalidTransactionIdException("Return transaction id is wrong");
        }
        if (creditCard == null || creditCard.isEmpty() || !GFG.checkLuhn(creditCard)) {
        	throw new InvalidCreditCardException("credit card number is not valid");
        }
        if(creditCards.containsKey(creditCard) && returns.containsKey(returnId)  && returns.get(returnId).getStatus().equals("Completed")) {
        	double sum = returns.get(returnId).getReturnedProduct().entrySet().stream()
                	.mapToDouble((entry) -> {
                		Integer pId = entry.getKey();
                		Integer amount = entry.getValue();
                		
                		return inventory.get(pId).getPricePerUnit()*amount;
                	}).sum();
        	if(recordBalanceUpdate(sum*-1)) {
        		creditCards.get(creditCard).setBalance(creditCards.get(creditCard).getBalance()+sum);
        		FileReaderAndWriter.CreditCardsWriter(creditCards);
        		returns.get(returnId).setStatus("Payed");
        		FileReaderAndWriter.ReturnsWriter(returns);
                return sum;
        	}
        }
        
        return -1;

     }

    @Override
    public boolean recordBalanceUpdate(double toBeAdded) throws UnauthorizedException {
        Integer maxid = 0;
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if (loggedInUser.getRole().equals("Administrator") || loggedInUser.getRole().equals("ShopManager")) {
            if ((currentBalance + toBeAdded) < 0)
                return false;
            else {
                for (BalanceOperation bo : balanceOperations.values()) {
                    if (bo.getBalanceId() > maxid)
                        maxid = bo.getBalanceId();
                }
                BalanceOperation b = new BalanceOperationClass(maxid + 1, LocalDate.now(), toBeAdded,
                        toBeAdded < 0 ? "DEBIT" : "CREDIT");
                this.balanceOperations.put(maxid + 1, b);
                this.currentBalance += toBeAdded;
                if (!FileReaderAndWriter.balanceOperationsWriter(this.balanceOperations))
                    return false;
                else
                    return true;
            }
        } else {
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to) throws UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if (loggedInUser.getRole().equals("Administrator") || loggedInUser.getRole().equals("ShopManager")) {

            List<BalanceOperation> l = new ArrayList<BalanceOperation>();

            if (from == null && to == null)
                l = this.balanceOperations.values().stream().collect(Collectors.toList());

            if (from != null && to == null) {
                for (BalanceOperation bo : balanceOperations.values()) {
                    if (bo.getDate().isAfter(from) || bo.getDate().isEqual(from))
                        l.add(bo);
                }
            }
            if (from == null && to != null) {
                for (BalanceOperation bo : balanceOperations.values()) {
                    if (bo.getDate().isBefore(to) || bo.getDate().isEqual(to))
                        l.add(bo);
                }
            }
            if (from != null && to != null) {
                if(from.isAfter(to)){
                    LocalDate x = from;
                    from = to;
                    to = x;
                }
                for (BalanceOperation bo : balanceOperations.values()) {
                    if ((bo.getDate().isBefore(to) || bo.getDate().isEqual(to))
                            && (bo.getDate().isAfter(from) || bo.getDate().isEqual(from)))
                        l.add(bo);
                }
            }
            return l;
        } else {
            throw new UnauthorizedException("Function not available for the current user");
        }
    }

    @Override
    public double computeBalance() throws UnauthorizedException {
        if (loggedInUser == null)
            throw new UnauthorizedException("No one is logged in");

        if (loggedInUser.getRole().equals("Administrator") || loggedInUser.getRole().equals("ShopManager")) {
            return this.currentBalance;
        } else {
            throw new UnauthorizedException("Function not available for the current user");
        }
    }
}