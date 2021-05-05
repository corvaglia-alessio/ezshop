package it.polito.ezshop.model;

public class Customer implements it.polito.ezshop.data.Customer {
	private int customerId;
	private String customerName;
	private String customerCard;
	private int points;
	
	public Customer(int customerId, String customerName, String customerCard,int points) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerCard = customerCard;
		this.points = points;
	}

	@Override
	public String getCustomerName() {
		return this.customerName;
		
	}

	@Override
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		

	}

	@Override
	public String getCustomerCard() {
		return this.customerCard;

	}

	@Override
	public void setCustomerCard(String customerCard) {
		this.customerCard = customerCard;

	}

	@Override
	public Integer getId() {
		return this.getId();
	}

	@Override
	public void setId(Integer id) {
		this.customerId=id;
	}

	@Override
	public Integer getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(Integer points) {
		// TODO Auto-generated method stub
		this.points = points;
	}

}
