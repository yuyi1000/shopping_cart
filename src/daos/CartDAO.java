package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.CartBean;
import models.UserBean;
import utils.Conn;

// TODO: use views for database

public class CartDAO {

	private List<CartBean> carts;
	
	private String getCarts = "select * from cart";
	
	private String getUserByEmail = "select * from user where email = ?";
	
	private String getItemByName = "select * from item where name = ?";
	
	private String insertCartString = "insert into cart (uid, item_id, cid, quantity, total, image_link, name)" +
				"values (?, ?, ?, ?, ?, ?, ?)";
	
	private String getCartsByUserIdString = "select * from cart where uid = ?"; 
	
	private String getCartByUidAndIidString = "select * from cart where uid = ? and item_id = ?";
	
	private String updateCartString = "update cart set total = total / quantity * (quantity + 1),"
			+ " quantity = quantity + 1 where uid = ? and item_id = ?";
	
	private String increaseItemByOneString = "update cart set total = total / quantity * (quantity + 1),"
			+ "quantity = quantity + 1 where uid = ? and item_id = ?";
	
	private String decreaseItemByOneString = "update cart set total = total / quantity * (quantity - 1),"
			+ "quantity = quantity - 1 where uid = ? and item_id = ?";
	
	private String deleteItemString = "delete from cart where uid = ? and item_id = ?";
	
	private String getStockItemQuantityString = "select quantity from item where item_id = ?";
	
	private String getCartItemQuantityString = "select quantity from cart where uid = ? and item_id = ?";
	
	public CartDAO() {
		
		carts = new ArrayList<CartBean>();
		
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getCarts);
				ResultSet rs = preparedStatement.executeQuery()) 
		{
			while (rs.next()) {
				
				int uid = rs.getInt("uid");
				int iid = rs.getInt("item_id");
				int cid = rs.getInt("cid");
				int quantity = rs.getInt("quantity");
				double total = rs.getDouble("total");
				String imageLink = rs.getString("image_link");
				String name = rs.getString("name");
				
				CartBean cart = new CartBean(uid, iid, quantity, total, imageLink, name, cid);
				carts.add(cart);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public boolean cartExists(int uid, int iid) {
		
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getCartByUidAndIidString);) 
		{
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, iid);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return true;			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return false;
		
	}
	
	private int getStockItemsByItemId(int iid) {
		int quantity = -1;
		
		try (Connection conn = Conn.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(getStockItemQuantityString);) 
		{
			
			preparedStatement.setInt(1, iid);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				
				quantity = rs.getInt("quantity");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}
	
	private int getCartItems(int uid, int iid) {
		int quantity = -1;
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getCartItemQuantityString);) 
			{
				
				preparedStatement.setInt(1, uid);
				preparedStatement.setInt(2, iid);
				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					
					quantity = rs.getInt("quantity");				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return quantity;		
		
	}
	

	public void updateCart(int uid, int iid) {
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(updateCartString);) 
		{
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, iid);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	
	public boolean increaseItemByOne(int uid, int iid) {
		
		int cartQuantity = getCartItems(uid, iid);
		int stockQuantity = getStockItemsByItemId(iid);
		if (cartQuantity < 0 || stockQuantity < 0 || cartQuantity >= stockQuantity) return false;
		
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(increaseItemByOneString);) 
		{
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, iid);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public void decreaseItemByOne(int uid, int iid) {
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(decreaseItemByOneString);) 
		{
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, iid);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void deleteItem(int uid, int iid) {
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(deleteItemString);) 
		{
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, iid);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	// parameter name is item name
	public void insertCart(String email, String name) {

		int uid = -1;
		int iid = -1;
		int cid = 0;
		int quantity = 1;
		double total = 0;
		String imageLink = "";
		
		try (Connection conn = Conn.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(getUserByEmail);) 
		{
			
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				uid = rs.getInt("uid");				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		System.out.println("uid:" + uid);
		
		try (Connection conn = Conn.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(getItemByName);)
		{
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				iid = rs.getInt("item_id");
				cid = rs.getInt("cid");
//				quantity = rs.getInt("quantity");
				total = rs.getDouble("price");
				imageLink = rs.getString("image_link");
				name = rs.getString("name");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		System.out.println("iid: " + iid);
		
		System.out.println("total: " + total);
		
		if (uid >= 0 && iid >= 0) {
			
			if (cartExists(uid, iid)) {
				updateCart(uid, iid);
			}
			else {
				
				try (Connection conn = Conn.getConnection();
						PreparedStatement preparedStatement = conn.prepareStatement(insertCartString);) 
				{
					
					preparedStatement.setInt(1, uid);
					preparedStatement.setInt(2, iid);
					preparedStatement.setInt(3, cid);
					preparedStatement.setInt(4, quantity);
					preparedStatement.setDouble(5, total);
					preparedStatement.setString(6, imageLink);
					preparedStatement.setString(7, name);
					
					preparedStatement.executeUpdate();
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
				
		}
		
		
	}
	
	public List<CartBean> getCartsByUserId(int userId) {
		
		List<CartBean> list = new ArrayList<CartBean>();
		
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getCartsByUserIdString);)
		{
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				int uid = rs.getInt("uid");
				int iid = rs.getInt("item_id");
				int quantity = rs.getInt("quantity");
				double total = rs.getDouble("total");
				String imageLink = rs.getString("image_link");
				String name = rs.getString("name");
				int cid = rs.getInt("cid");
				
				CartBean bean = new CartBean(uid, iid, quantity, total, imageLink, name, cid);
				list.add(bean);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	
	
}
