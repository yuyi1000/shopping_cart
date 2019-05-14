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
import models.UserBean;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("in the login servlet.");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		
		UserDAO dao = new UserDAO();
		
		HttpSession session = req.getSession();
		
		if (dao.validUser(email, password)) {
			
			System.out.println("a valid user.");
			
			UserBean user = dao.getUserByEmail(email);
			session.setAttribute("firstname", user.getFirstName());
			session.setAttribute("email", email);
			
			int userId = dao.getUserIdByEmail(email);
			
			session.setAttribute("userid", userId);
			
			session.setAttribute("validUser", true);
			
			CartDAO cartDAO = new CartDAO();
			
			List<CartBean> carts = cartDAO.getCartsByUserId(userId);
			session.setAttribute("carts", carts);
			
		}
		else {
			session.setAttribute("validUser", false);
		}
		
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
		
//		System.out.println("at the end of login.");
		
		
	}
	
}
