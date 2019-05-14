package models;

public class OrderBean {

	private int uid;
	private int iid;
	private double total;
	
	private String name;

	public int getUid() {
		return uid;
	}

	public int getIid() {
		return iid;
	}

	public double getTotal() {
		return total;
	}

	public String getName() {
		return name;
	}

	public OrderBean(int uid, int iid, double total, String name) {
		super();
		this.uid = uid;
		this.iid = iid;
		this.total = total;
		this.name = name;
	}
	
	
	
	
	
}
