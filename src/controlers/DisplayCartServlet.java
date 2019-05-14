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
import daos.UserDAO;
import models.CartBean;

@WebServlet("/display")
public class DisplayCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDAO userDAO = new UserDAO();
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		
		int userId = userDAO.getUserIdByEmail(email);
		
		CartDAO cartDAO = new CartDAO();
		
		List<CartBean> carts = cartDAO.getCartsByUserId(userId);
		session.setAttribute("carts", carts);
		
//		req.setAttribute("carts", carts);
		
		System.out.println("in the display servlet");
		System.out.println(carts.size());
		
		RequestDispatcher rd = req.getRequestDispatcher("/cart.jsp");
		rd.forward(req, resp);
		
		
	}

	
	
	
}
