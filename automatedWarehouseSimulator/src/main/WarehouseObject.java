package main;

public abstract class WarehouseObject {
	protected String UID;
	
	public WarehouseObject (String UID) {
		this.UID = UID;
	}
	
	public String getUID() {
		return UID;
	}
	
	public abstract String toString();

}
