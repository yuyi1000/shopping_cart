package controlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daos.CartDAO;

@WebServlet("/decrease_item")
public class DecreaseItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("in the decrease servlet.");
		
		HttpSession session = req.getSession();
		int userId = (Integer) session.getAttribute("userid");
		
		System.out.println("userid: " + userId);
		
		String iid = req.getParameter("iid");
		System.out.println("iid: " + iid);
		
		CartDAO cartDAO = new CartDAO();
		cartDAO.decreaseItemByOne(userId, Integer.parseInt(iid));
		
	}

	
	
}
