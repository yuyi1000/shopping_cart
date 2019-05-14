package controlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.ItemDAO;
import daos.UserDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("in the register servlet.");
		System.out.println("in the post method.");
		
		PrintWriter out = resp.getWriter();
		
		UserDAO userDAO = new UserDAO();
		String email = req.getParameter("email");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		
		HttpSession session = req.getSession();
		
		if (userDAO.addUser(password, email, firstName, lastName)) {
			
			
			session.setAttribute("firstname", firstName);
			session.setAttribute("email", email);
			
			session.setAttribute("duplicateEmail", false);

			int userId = userDAO.getUserIdByEmail(email);
			session.setAttribute("userid", userId);			
			
			req.setAttribute("firstname", firstName);
			RequestDispatcher rd=req.getRequestDispatcher("/index");
			rd.forward(req, resp);	
//			resp.sendRedirect("index");
			
			
		}
		else {
			session.setAttribute("duplicateEmail", true);
			RequestDispatcher rd=req.getRequestDispatcher("/register.jsp");
//			out.println("email already exists");
			rd.include(req, resp);
		}

		
	}
	
	
	
}
