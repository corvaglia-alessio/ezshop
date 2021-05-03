package it.polito.ezshop.model;

public class LoyaltyCard {
	private String cardId;
	private int points;
	private Customer customer;
	
	public LoyaltyCard(String cardId) {
		
		this.cardId = cardId;
		this.points = 0;
		this.customer = null;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
