package it.polito.ezshop.model;

import java.util.HashMap;
import java.util.Map;

public class ReturnTransaction {
    private Integer id;
    private Integer saleTransactionID;
    private Map<Integer, Integer> returnedProducts;
    private String status;

    public ReturnTransaction(Integer _id, Integer _saleTransactionID) {
        this.id = _id;
        this.saleTransactionID = _saleTransactionID;
        this.returnedProducts = new HashMap<Integer, Integer>();
        this.status = "Ongoing";
    }

    public ReturnTransaction(Integer _id, Integer _saleTransactionID, Map<Integer, Integer> _returnedProducts, String _status) {
        this.id = _id;
        this.saleTransactionID = _saleTransactionID;
        this.returnedProducts = _returnedProducts;
        this.status = _status;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }
}
