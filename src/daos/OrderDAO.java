package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.ItemBean;
import utils.Conn;

public class OrderDAO {

	String insertOrderString = "insert into orders (uid, iid, total, name, order_date)" +
			"values (?, ?, ?, ?, curdate())";
	
	String getOrderIdString = "select oid from orders where uid = ? and iid = ?";
	
	public void insertOrder(int uid, int iid, double total, String name) {
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(insertOrderString);) 
		{
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, iid);
			preparedStatement.setDouble(3, total);
			preparedStatement.setString(4, name);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public int getOrderId(int uid, int iid) {
		int orderId = -1;
		try (Connection conn = Conn.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(getOrderIdString))
		{
			preparedStatement.setInt(1, uid);
			preparedStatement.setInt(2, iid);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				orderId = rs.getInt("oid");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return orderId;
		
	}
	
	
}
