package models;

public class CartBean {

	
	private int uid;
	private int iid;
	private int quantity;
	private double total;
	private String imageLink;
	private String name;
	private int cid;
	public int getUid() {
		return uid;
	}
	public int getIid() {
		return iid;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getTotal() {
		return total;
	}
	public String getImageLink() {
		return imageLink;
	}
	public String getName() {
		return name;
	}
	public int getCid() {
		return cid;
	}
	public CartBean(int uid, int iid, int quantity, double total, String imageLink, String name, int cid) {
//		super();
		this.uid = uid;
		this.iid = iid;
		this.quantity = quantity;
		this.total = total;
		this.imageLink = imageLink;
		this.name = name;
		this.cid = cid;
	}
	
	
	
	
}
