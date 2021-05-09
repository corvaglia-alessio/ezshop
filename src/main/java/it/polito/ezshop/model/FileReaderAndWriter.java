package it.polito.ezshop.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.polito.ezshop.data.BalanceOperation;
import it.polito.ezshop.data.User;


public class FileReaderAndWriter {

    //reads the users actually registered in the systems and return them in a Map
    //one user per-line
    //user fields are the following, in the specified order <id>;<username>;<password>;<role>
    //field separator is ;
    static public Map<Integer, User>  UsersReader(){
        Map<Integer, User> users = new HashMap<Integer, User>();
        
        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/users.txt");
        System.out.println(inputFile.getAbsolutePath());
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] res = line.split(";");
                User u = new UserClass(Integer.parseInt(res[0]), res[1], res[2], res[3]);
                users.put(Integer.parseInt(res[0]), u);
            }
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(s!=null){
                s.close();
            }
        }
        return users;
    }

    //writes the user list received as parameter in the users.txt file
    //one user per-line
    //user fields are the following, in the specified order: <id>;<username>;<password>;<role>
    //field separator is ;
    static public Boolean UsersWriter(Map<Integer, User> users){
        String x = "";
        for(User u : users.values()){
            x = x + u.getId() + ";" + u.getUsername() + ";" + u.getPassword() + ";" + u.getRole() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/users.txt");
        PrintWriter out = null;
        try{
            out = new PrintWriter(outputFile);
            out.print(x);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        finally{
            if(out!=null)
                out.close();
        }

        return true;
    }
    //reads the users actually registered in the systems and return them in a Map
    //one user per-line
    //user fields are the following, in the specified order <id>;<customerName>;<customerCard>;<points>
    //field separator is ;
    static public HashMap<Integer,it.polito.ezshop.model.Customer> CustomersReader(){
    	HashMap<Integer, it.polito.ezshop.model.Customer> customers = new HashMap<Integer, it.polito.ezshop.model.Customer>();
        
        
        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/customers.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] res = line.split(";");
                it.polito.ezshop.model.Customer c = new it.polito.ezshop.model.Customer(Integer.parseInt(res[0]), res[1], res[2],Integer.parseInt(res[3]));
                customers.put(Integer.parseInt(res[0]), c);
            }
        } 	
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(s!=null){
                s.close();
            }
        }
        return customers;
        
    }
    
    /*writing customers to file*/
    static public Boolean CustomersWriter(HashMap<Integer, it.polito.ezshop.model.Customer> customers){
        String x = "";
        for(it.polito.ezshop.model.Customer c : customers.values()){
            x = x + c.getId() + ";" + c.getCustomerName() + ";" + c.getCustomerCard()+";"+c.getPoints()+"\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/customers.txt");
        PrintWriter out = null;
        try{
            out = new PrintWriter(outputFile);
            out.print(x);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        finally{
            if(out!=null)
                out.close();
        }

        return true;
    }

 
    //reads the balanceOperations actually registered in the system and return them in a Map
    //one balanceOperation per-line
    //balanceOperation fields are the following, in the specified order <balanceId>;<date>;<money>;<type>
    //field separator is ;
    static public Map<Integer, BalanceOperation> BalanceOperationsReader(){
        Map<Integer, BalanceOperation> bos = new HashMap<Integer, BalanceOperation>();
        
        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/balanceoperations.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] res = line.split(";");
                BalanceOperation bo = new BalanceOperationClass(Integer.parseInt(res[0]), LocalDate.parse(res[1]), Double.parseDouble(res[2]), res[3]);
                bos.put(Integer.parseInt(res[0]), bo);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(s!=null){
                s.close();
            }
        }
        return bos;
    }

    //writes the balanceOperations Map received as parameter in the balanceoperations.txt file
    //one balance operation per-line
    //balanceOperation fields are the following, in the specified order: <balanceId>;<date>;<money>;<type>
    //field separator is ;
    static public Boolean balanceOperationsWriter(Map<Integer, BalanceOperation> balanceOperations){
        String x = "";
        for(BalanceOperation bo : balanceOperations.values()){
            x = x + bo.getBalanceId() + ";" + bo.getDate().toString() + ";" + bo.getMoney() + ";" + bo.getType() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/balanceoperations.txt");
        PrintWriter out = null;
        try{
            out = new PrintWriter(outputFile);
            out.print(x);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        finally{
            if(out!=null)
                out.close();
        }
        return true;
    }

    //reads the saletransactions actually registered in the system and return them in a Map
    //one saletransaction per-line
    //saletransaction fields are the following, in the specified order <ticketNumber>;<price>;<discountrate>
    //field separator is ;
    static public Map<Integer, SaleTransactionClass> SaleTransactionsReader(){
        Map<Integer, SaleTransactionClass> st = new HashMap<Integer, SaleTransactionClass>();
        
        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/saletransactions.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] res = line.split(";");
                SaleTransactionClass t = new SaleTransactionClass(Integer.parseInt(res[0]), Double.parseDouble(res[1]), Double.parseDouble(res[2]), res[3]);
                st.put(Integer.parseInt(res[0]), t);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(s!=null){
                s.close();
            }
        }
        return st;
    }

    //writes the salestransaction Map received as parameter in the salestransactions.txt file
    //one saletransaction per-line
    //sale transaction fields are the following, in the specified order: <ticketNumber>;<price>;<discountrate>
    //field separator is ;
    //writes ONLY CLOSED AND PAID transactions
    static public Boolean saletransactionsWriter(Map<Integer, SaleTransactionClass> salesTransactions){
        String x = "";
        for(SaleTransactionClass s : salesTransactions.values()){
            if(!s.getState().equals("Open"))
                x = x + s.getTicketNumber() + ";" + s.getPrice() + ";" + s.getDiscountRate() + ";" + s.getState()+ "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/saletransactions.txt");
        PrintWriter out = null;
        try{
            out = new PrintWriter(outputFile);
            out.print(x);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        finally{
            if(out!=null)
                out.close();
        }
        return true;
    }

    //reads the ticketentries actually registered in the system and return them in a List
    //one ticketentry per-line
    //ticketentry fields are the following, in the specified order <transactionid>;<barcode>;<productdescription>;<amount>;<priceperunit>;<discountrate>
    //field separator is ;
    static public List<TicketEntryClass> ticketEntriesReader(){
        List<TicketEntryClass> te = new ArrayList<TicketEntryClass>();
        
        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/ticketentries.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] res = line.split(";");
                TicketEntryClass t = new TicketEntryClass(Integer.parseInt(res[0]), res[1], res[2], Integer.parseInt(res[3]), Double.parseDouble(res[4]), Double.parseDouble(res[5]));
                te.add(t);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(s!=null){
                s.close();
            }
        }
        return te;
    }

    //writes the ticketentries list received as parameter in the tciketentries.txt file
    //one ticketentry per-line
    // ticket entries fields are the following, in the specified order: <transactionid>;<barcode>;<productdescription>;<amount>;<priceperunit>;<discountrate>
    //field separator is ;
    static public Boolean ticketEntriesWriter(List<TicketEntryClass> ticketEntries){
        String x = "";
        for(TicketEntryClass t : ticketEntries){
            x = x + t.getTransactionId() + ";" + t.getBarCode() + ";" + t.getProductDescription() + ";" + t.getAmount() + ";" + t.getPricePerUnit() + ";" + t.getDiscountRate() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/ticketentries.txt");
        PrintWriter out = null;
        try{
            out = new PrintWriter(outputFile);
            out.print(x);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        finally{
            if(out!=null)
                out.close();
        }
        return true;
    }

    //reads the orders actually registered in the systems and return them in a Map
    //one order per-line
    //Order fields are the following, in the specified order <orderId>;<balanceId>;<ProductCode>;<pricePerUnit>;<quantity>;<status>
    //field separator is ;
    static public HashMap<Integer, OrderClass> OrdersReader(){
    	HashMap<Integer, OrderClass> orders = new HashMap<Integer, OrderClass>();
        
        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/orders.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while(s.hasNextLine()){
                String line = s.nextLine();
                String[] res = line.split(";");
                OrderClass order = new OrderClass(Integer.parseInt(res[0]), Integer.parseInt(res[1]), res[2], Double.parseDouble(res[3]), Integer.parseInt(res[4]), res[5]);
                orders.put(Integer.parseInt(res[0]), order);
            }
        } 	
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(s!=null){
                s.close();
            }
        }
        return orders;        
    }
    
    /*writing orders to file*/
    static public Boolean OrdersWriter(Map<Integer, OrderClass> orders){
        String x = "";
        for(OrderClass o : orders.values()){
            x = x + o.getOrderId() + ";" + o.getBalanceId() + ";" + o.getProductCode() +";"+ o.getPricePerUnit() + o.getQuantity() + o.getStatus() +"\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/orders.txt");
        PrintWriter out = null;
        try{
            out = new PrintWriter(outputFile);
            out.print(x);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
        finally{
            if(out!=null)
                out.close();
        }

        return true;
    }
}
