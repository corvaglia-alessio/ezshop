package it.polito.ezshop.model;

public class LoyaltyCard {
	private String cardId;
	private int points;
	private Customer customer;
	
	public LoyaltyCard() {
		
		this.cardId = GenerateAlphaNumericString.getRandomString(15);
		this.points = 0;
		this.customer = null;
	}
	
	
		
}
