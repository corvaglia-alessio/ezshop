package it.polito.ezshop.model;

public class Product {

    private String RFID;
    private Integer ProductId;
    private boolean sold;

    public Product(String rfid, Integer productid, boolean sold ){
        this.RFID = rfid;
        this.ProductId = productid;
        this.sold = sold;
    }

    public String getRFID(){
        return this.RFID;
    }

    public Integer getProductId(){
        return this.ProductId;
    }

    public boolean getSold(){
        return this.sold;
    }

    public void setSold(boolean sold){
        this.sold = sold;
    }
}
