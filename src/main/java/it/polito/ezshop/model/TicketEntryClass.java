package it.polito.ezshop.model;

import it.polito.ezshop.data.TicketEntry;

public class TicketEntryClass implements TicketEntry {

    private Integer transactionId; //the transaction id the entry is associated to
    private String barCode;
    private String productDescription;
    private Integer amount;
    private Double pricePerUnit;
    private Double discountRate;

    public TicketEntryClass(Integer transactionId, String barCode, String productDescription, Integer amount, Double pricePerUnit, Double discountRate){
        this.transactionId = transactionId;
        this.barCode = barCode;
        this.productDescription = productDescription;
        this.amount = amount;
        this.discountRate = discountRate;
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getTransactionId(){
        return this.transactionId;
    }

    public void setTransactionId(Integer transactionId){
        this.transactionId = transactionId;
    }

    @Override
    public String getBarCode() {
        return this.barCode;
    }

    @Override
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public String getProductDescription() {
        return this.productDescription;
    }

    @Override
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public double getPricePerUnit() {
        return this.pricePerUnit;
    }

    @Override
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public double getDiscountRate() {
       return this.discountRate;
    }

    @Override
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
    
}
