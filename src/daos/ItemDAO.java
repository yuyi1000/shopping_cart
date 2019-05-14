package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.ItemBean;
import models.UserBean;
import utils.Conn;

public class ItemDAO {

	
	private List<ItemBean> items = new ArrayList<ItemBean>();
	
	
	private String getItems = "select * from item I, category C where I.cid = C.cid";
	
	private String decreaseItemString = "update item set quantity = quantity - ? where item_id = ?";
	
	private String getItemsNameString = "select name from item";
	
	
	public ItemDAO() {
		
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getItems);
				ResultSet rs = preparedStatement.executeQuery()) 
		{
			while (rs.next()) {
				
//				String password = rs.getString("password");
//				String email = rs.getString("email");
//				String firstName = rs.getString("firstname");
//				String lastName = rs.getString("lastname");
//				
//				UserBean user = new UserBean(password, email, firstName, lastName);
//				users.add(user);
				
				String descr = rs.getString("descr");
				String imageLink = rs.getString("image_link");
				String category = rs.getString("cname");
				String name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				
				ItemBean item = new ItemBean(descr, imageLink, category, name, quantity, price);
				items.add(item);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List<ItemBean> getItemByCategory(String category) {
		
		if (category.equalsIgnoreCase("all")) return items; 
		
		
		List<ItemBean> rs = new ArrayList<ItemBean>();
		
		for (ItemBean item : items) {
			if (item.getCategory().substring(0, 5).equalsIgnoreCase(category.substring(0, 5))) {
				rs.add(item);
			}
		}
		
		return rs;
	}
	
	
	public List<ItemBean> getItemByKeyword(String keyword) {
		
		List<ItemBean> rs = new ArrayList<ItemBean>();
		
		for (ItemBean item : items) {
			if (item.getName().toLowerCase().contains(keyword)) {
				rs.add(item);
			}
		}
		return rs;
	}
	
	
	public ItemBean getItemByName(String name) {
		
		for (ItemBean item : items) {
			if (item.getName().equals(name)) return item;
		}
		return null;
	}
	
	
	public void decreaseItem(int iid, int quantity) {
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(decreaseItemString);) 
		{
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, iid);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public String getItemsName() {
		List<String> names = new ArrayList<String>();
		
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getItemsNameString);
				ResultSet rs = preparedStatement.executeQuery()) 
		{
			while (rs.next()) {
				String name = rs.getString("name");
				names.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return String.join("_", names);
	}
	
	
}
