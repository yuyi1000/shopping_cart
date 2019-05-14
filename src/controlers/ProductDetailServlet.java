package controlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ItemDAO;
import models.ItemBean;

@WebServlet("/product_detail")
public class ProductDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ItemDAO dao = new ItemDAO();
		
		String name = req.getParameter("name");
		System.out.println("inside servlet product detail");
		System.out.println(name);
		
		ItemBean item = dao.getItemByName(name);
		
		req.setAttribute("item", item);
		
		RequestDispatcher rd = req.getRequestDispatcher("/product_detail.jsp");
		rd.forward(req, resp);
		
	}

	
	
	
}
