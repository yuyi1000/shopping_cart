package models;

public class ItemBean {
	
	private String descr;
	private String image_link;
	private String category;
	private String name;
	private int quantity;
	private double price;
	public String getDescr() {
		return descr;
	}
	public String getImage_link() {
		return image_link;
	}

	
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public int getQuantity() {
		return quantity;
	}
	
	
	public double getPrice() {
		return price;
	}
	public ItemBean(String descr, String image_link, String category, String name, int quantity, double price) {
		super();
		this.descr = descr;
		this.image_link = image_link;
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	
	

}
