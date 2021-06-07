package it.polito.ezshop.model;

public class Product {

    private String RFID;
    private Integer ProductId;

    public Product(String rfid, Integer productid){
        this.RFID = rfid;
        this.ProductId = productid;
    }

    public String getRFID(){
        return this.RFID;
    }

    public Integer getProductId(){
        return this.ProductId;
    }
}
