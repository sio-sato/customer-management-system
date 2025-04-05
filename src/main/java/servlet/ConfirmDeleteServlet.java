package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AreaDAO;
import model.dao.CustomerDAO;
import model.entity.AreaBean;
import model.entity.CustomerBean;

/**
 * Servlet implementation class ConfirmDeleteServlet
 */
@WebServlet("/confirmDelete")
public class ConfirmDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("customerId"));
		CustomerBean customerBean = new CustomerBean();
		CustomerDAO customerDAO = new CustomerDAO();
		List<AreaBean> AreaList = null;
		AreaDAO Adao = new AreaDAO();

		try {
			AreaList = Adao.getAllArea();
			customerBean = customerDAO.getCustomer(id);
			request.setAttribute("Delete", customerBean);
			request.setAttribute("AreaList", AreaList);
			request.setAttribute("customerId", id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("customerDelete.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		}

	}

}
