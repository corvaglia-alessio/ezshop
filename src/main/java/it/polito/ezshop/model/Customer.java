package it.polito.ezshop.model;

public class Customer implements it.polito.ezshop.data.Customer {
	private int customerId;
	private String customerName;
	private String customerCard;
	
	public Customer(int customerId, String customerName, String customerCard) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		if(customerCard == null)
			this.customerCard = "";
		else
			this.customerCard = customerCard;
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
		return null; 
	}

	@Override
	public void setPoints(Integer points) {
		// TODO Auto-generated method stub

	}

}
