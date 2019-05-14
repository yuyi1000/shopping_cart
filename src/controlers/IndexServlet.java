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
import daos.OrderDAO;
import daos.UserDAO;
import models.CartBean;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName = (String) req.getAttribute("firstname");
		System.out.println("in the index servlet");
		System.out.println(firstName);
		
		// comment the next line to see if the result in jsp is affected.
		req.setAttribute("firstname", firstName);
		
		RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String firstName = (String) req.getAttribute("firstname");
		System.out.println("in the index servlet");
//		System.out.println(firstName);
		
		HttpSession session = req.getSession();
		
		String firstName = (String) session.getAttribute("firstname");
		System.out.println(firstName);
		
		
		// comment the next line to see if the result in jsp is affected.
		req.setAttribute("firstname", firstName);
		
		UserDAO userDAO = new UserDAO();
		String email = (String) session.getAttribute("email");
		
		int userId = userDAO.getUserIdByEmail(email);		
		
		CartDAO cartDAO = new CartDAO();
		OrderDAO orderDAO = new OrderDAO();
		
		List<CartBean> carts = cartDAO.getCartsByUserId(userId);
		
		session.setAttribute("carts", carts);		
		
		
		
		RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
		
		
	}
	
	
	
	
	
	

}
