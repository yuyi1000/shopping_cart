package controlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ItemDAO;

@WebServlet("/getproducts")
public class GetProductsNameServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ItemDAO itemDAO = new ItemDAO();
		String resultString = itemDAO.getItemsName();
		resp.setContentType("text/plain");
		resp.getWriter().write(resultString);		
		
		
		
		
	}

	
	
}
