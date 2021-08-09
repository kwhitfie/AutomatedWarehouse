package main;



public class PackingStation implements Tick{

	
	private String UID;
	private Order order;
	
	public PackingStation() {
		// TODO Auto-generated constructor stub
	}


	public String getUID() {
		return UID;
	}

	public Order getCurrentOrder() {
		return order;
	}

	@Override
	public void Tick() {
		// TODO Auto-generated method stub

	}

}
