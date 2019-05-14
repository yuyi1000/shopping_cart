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
import models.CartBean;

@WebServlet("/delete_item")
public class DeleteItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("in the delete servlet.");
		
		HttpSession session = req.getSession();
		int userId = (Integer) session.getAttribute("userid");
		
		System.out.println("userid: " + userId);
		
		String iid = req.getParameter("iid");
		System.out.println("iid: " + iid);
		
		CartDAO cartDAO = new CartDAO();
		cartDAO.deleteItem(userId, Integer.parseInt(iid));		

		
		// TODO: Not refresh page.
//		List<CartBean> carts = cartDAO.getCartsByUserId(userId);
//		session.setAttribute("carts", carts);
//		
//		System.out.println(carts.size());
//		
//		RequestDispatcher rd = req.getRequestDispatcher("/cart.jsp");
//		rd.forward(req, resp);
		
		
	}
	
	

}
