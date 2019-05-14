package controlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ItemDAO;
import models.ItemBean;

@WebServlet("/search_product")
public class SearchProductServlet  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("inside search project servlet.");
		
		String keyword = req.getParameter("name");
		ItemDAO itemDAO = new ItemDAO();
		List<ItemBean> items = itemDAO.getItemByKeyword(keyword);
		
		req.setAttribute("items", items);
		
		RequestDispatcher rd = req.getRequestDispatcher("/products.jsp");
		rd.forward(req, resp);
		
		
	}

	
	
}
