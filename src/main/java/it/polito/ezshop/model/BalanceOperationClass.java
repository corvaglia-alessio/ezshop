package it.polito.ezshop.model;

import java.time.LocalDate;

import it.polito.ezshop.data.BalanceOperation;

public class BalanceOperationClass implements BalanceOperation{

    int balanceId;
    LocalDate date;
    double money;
    String type;

    public BalanceOperationClass(int balanceId, LocalDate date, double money, String type){
        this.balanceId = balanceId;
        this.date = date;
        this.money = money;
        this.type = type;
    }

    public int getBalanceId(){
        return this.balanceId;
    }

    public void setBalanceId(int balanceId){
        this.balanceId = balanceId;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public double getMoney(){
        return this.money;
    }

    public void setMoney(double money){
        this.money = money;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }
    
}