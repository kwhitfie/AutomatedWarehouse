package main;

<<<<<<< HEAD
public class PackingStation {
	
	private String UID;
	private Order order;
=======
public class PackingStation implements Tick{
>>>>>>> branch 'master' of https://github.com/kwhitfie/AutomatedWarehouse.git

	public PackingStation() {
		// TODO Auto-generated constructor stub
	}

<<<<<<< HEAD
	public String getUID() {
		return UID;
	}

	public Order getCurrentOrder() {
		return order;
=======
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
>>>>>>> branch 'master' of https://github.com/kwhitfie/AutomatedWarehouse.git
	}

}
