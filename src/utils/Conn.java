package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

//  private static final String url1= "jdbc:mysql://localhost:3306/";
//  private static final String url2 = "?autoReconnect=true&useSSL=false";

//  private static final String url = "jdbc:mysql://localhost:3306/forest?autoReconnect=true&useSSL=false";
  static String url = "jdbc:mysql://localhost:3306/shopping_cart?autoReconnect=true&useSSL=false";
  private static final String username = "root";
  private static final String password = "flyaway1314";

  private static Connection con;

  public static Connection getConnection()
  {
      con = null;

      try
      {
    	  
    	  Class.forName("com.mysql.jdbc.Driver");

          con= DriverManager.getConnection(url, username, password);
          if(con!=null)
          {
              System.out.println("Connection Done");
          }
          else
          {
              System.out.println("Connection not Done");
          }

      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return con;
  }

//  @Override
//  public void close() {
//      System.out.println("calling close method from Conn class.");
//
//      if (con != null) {
//          try {
//              con.close();
//              System.out.println("Connection is closed.");
//          }
//          catch (SQLException e) {
//              e.printStackTrace();
//          }
//
//      }
//  }

}


