package controlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CartDAO;

@WebServlet("/increase_item")
public class IncreaseItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("in the increase servlet.");
		
		HttpSession session = req.getSession();
		int userId = (Integer) session.getAttribute("userid");
		
		System.out.println("userid: " + userId);
		
		String iid = req.getParameter("iid");
		System.out.println("iid: " + iid);
		
		CartDAO cartDAO = new CartDAO();
		boolean status = cartDAO.increaseItemByOne(userId, Integer.parseInt(iid));
		
		String result;
		if (status) 
			result = "T";
		else
			result = "F";
		
		resp.setContentType("text/plain");
		resp.getWriter().write(result);
		
		
	}
	
	

}
