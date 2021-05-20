package it.polito.ezshop.model;

public class OrderClass implements it.polito.ezshop.data.Order{
    private Integer balanceId;
    private String productCode;
    private double pricePerUnit;
    private int quantity;
    private String status;
    private Integer orderId;

    public OrderClass(Integer orderId, Integer balanceId, String productCode, double pricePerUnit, int quantity, String status){
        super();
        if (balanceId == null)
            balanceId = 0;
        this.balanceId = balanceId;
        this.productCode = productCode;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.status = status;
        this.orderId = orderId;
    }

    public Integer getBalanceId(){
        return this.balanceId;
    }

    public void setBalanceId(Integer balanceId){
        this.balanceId = balanceId;
    }

    public String getProductCode(){
        return this.productCode;
    }

    public void setProductCode(String productCode){
        this.productCode = productCode;
    }

    public double getPricePerUnit(){
        return this.pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit){
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Integer getOrderId(){
        return this.orderId;
    }

    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }
}