package it.polito.ezshop.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.ezshop.data.SaleTransaction;
import it.polito.ezshop.data.TicketEntry;

public class SaleTransactionClass implements SaleTransaction {

    private Integer ticketNumber;
    private Double price;
    private Double discountRate;
    private List<TicketEntry> entries;
    private String state;

    //constructor for loading transactions from file
    public SaleTransactionClass(Integer ticketNumber, Double price, Double discountRate, String state){
        this.ticketNumber = ticketNumber;
        this.price = price;
        this.discountRate = discountRate;
        this.entries = new ArrayList<TicketEntry>();
        this.state = state;
    }

    //constructor for new transactions
    public SaleTransactionClass(Integer ticketNumber){
        this.ticketNumber = ticketNumber;
        this.price = 0D;
        this.discountRate = 0D;
        this.entries = new ArrayList<TicketEntry>();
        this.state = "Open";
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state = state;
    }

    @Override
    public Integer getTicketNumber() {
        return this.ticketNumber;
    }

    @Override
    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public List<TicketEntry> getEntries() {
        return this.entries;
    }

    @Override
    public void setEntries(List<TicketEntry> entries) {
        this.entries = entries;
    }

    @Override
    public double getDiscountRate() {
        return this.discountRate;
    }

    @Override
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
