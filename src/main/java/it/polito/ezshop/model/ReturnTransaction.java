package it.polito.ezshop.model;

import java.util.Map;

public class ReturnTransaction {
    private Integer id;
    private Integer saleTransactionID;
    private Map<Integer, Integer> returnedProducts;

    public ReturnTransaction(Integer _id, Integer _saleTransactionID) {
        this.id = _id;
        this.saleTransactionID = _saleTransactionID;
    }

    public ReturnTransaction(Integer _id, Integer _saleTransactionID, Map<Integer, Integer> _returnedProducts) {
        this.id = _id;
        this.saleTransactionID = _saleTransactionID;
        this.returnedProducts = _returnedProducts;
    }

    public Integer getID() {
        return this.id;
    }

    public void setID(Integer newID) {
        this.id = newID;
        return;
    }

    public Integer getSaleTransactionID() {
        return this.saleTransactionID;
    }

    public void setSaleTransactionID(Integer newSTID) {
        this.saleTransactionID = newSTID;
        return;
    }

    public Map<Integer, Integer> getReturnedProduct() {
        return this.returnedProducts;
    }

    public void setReturnProducts(Map<Integer, Integer> newRP) {
        this.returnedProducts = newRP;
        return;
    }
}
