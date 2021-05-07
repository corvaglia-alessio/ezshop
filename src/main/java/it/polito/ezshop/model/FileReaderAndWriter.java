package it.polito.ezshop.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
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
}
