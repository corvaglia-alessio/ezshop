package it.polito.ezshop.model;

public class CreditCardClass {
	String creditCardId;
	double balance;
	
	public CreditCardClass(String creditCardId, double balance) {
		super();
		this.creditCardId = creditCardId;
		this.balance = balance;
	}
	
	public String getCreditCardId() {
		return creditCardId;
	}
	public void setCreditCardId(String creditCardId) {
		this.creditCardId = creditCardId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
