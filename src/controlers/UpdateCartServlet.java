package controlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("inside update cart servlet.");
		
		
		String iid = req.getParameter("iid");
		System.out.println("iid: " + iid);
		
		
		
		String result = "result from update cart";
		
		resp.setContentType("text/plain");
		resp.getWriter().write(result);
		
	}
	
	

}
