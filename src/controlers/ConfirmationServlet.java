package controlers;

import java.io.IOException;
import java.util.ArrayList;
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
import daos.OrderDAO;
import daos.UserDAO;
import models.CartBean;


@WebServlet("/confirmation")
public class ConfirmationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("inside confirmation servlet.");
		
		UserDAO userDAO = new UserDAO();
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		
		int userId = userDAO.getUserIdByEmail(email);		
		
		CartDAO cartDAO = new CartDAO();
		OrderDAO orderDAO = new OrderDAO();
		
		List<CartBean> carts = cartDAO.getCartsByUserId(userId);
		
		session.setAttribute("carts", carts);
		
		
		ItemDAO itemDAO = new ItemDAO();
		
		for (CartBean cart : carts) {
			int iid = cart.getIid();
			cartDAO.deleteItem(userId, iid);
			orderDAO.insertOrder(userId, iid, cart.getTotal(), cart.getName());
			itemDAO.decreaseItem(iid, cart.getQuantity());
		}
		
		int orderId = orderDAO.getOrderId(userId, carts.get(0).getIid());
		
		session.setAttribute("orderId", orderId + 2000);
		
		RequestDispatcher rd = req.getRequestDispatcher("confirmation.jsp");
		rd.include(req, resp);
		
		System.out.println("below include function.");
//		resp.sendRedirect("/confirmation.jsp");
		
	}

	
	
}
