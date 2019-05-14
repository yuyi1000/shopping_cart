package controlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CartDAO;
import daos.ItemDAO;
import daos.UserDAO;
import models.CartBean;
import models.ItemBean;

@WebServlet("/additem")
public class AddItemToCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		System.out.println("inside add item to cart servlet.");
		String name = req.getParameter("name");
		System.out.println(name);
				
		
		String category = "all";
		String choice = req.getParameter("category");
		if (choice != null && !choice.equals("null")) {
			category = choice;
		}
		
		System.out.println("category: " + category);
//		System.out.println(category == null);
//		System.out.println(category == "null");
//		System.out.println(category == "");
//		System.out.println(category.equals("null"));
//		System.out.println(category.equals(""));
//		System.out.println(category.equals(null));
		
		
		
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		
		CartDAO dao = new CartDAO();
		dao.insertCart(email, name);
		
		ItemDAO dao2 = new ItemDAO();
		List<ItemBean> items = dao2.getItemByCategory(category);
		
		req.setAttribute("items", items);
		
		UserDAO userDAO = new UserDAO();
		
		int userId = userDAO.getUserIdByEmail(email);
		
		
		List<CartBean> carts = dao.getCartsByUserId(userId);
		session.setAttribute("carts", carts);
		
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/products.jsp?category=" + category);
//		RequestDispatcher rd = req.getRequestDispatcher("/products.jsp");
		rd.forward(req, resp);
		
	}
	
	

}
