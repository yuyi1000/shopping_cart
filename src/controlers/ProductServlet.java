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

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		ItemDAO dao = new ItemDAO();
		
		String category = req.getParameter("category");
		System.out.println("inside product servlet.");
		System.out.println(category);
		
		List<ItemBean> items = dao.getItemByCategory(category);
		
		req.setAttribute("items", items);
		
		UserDAO userDAO = new UserDAO();
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		
		int userId = userDAO.getUserIdByEmail(email);
		
		CartDAO cartDAO = new CartDAO();
		
		List<CartBean> carts = cartDAO.getCartsByUserId(userId);
		session.setAttribute("carts", carts);
		
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/products.jsp");
		rd.forward(req, resp);
		
	}
	
	
	

}
