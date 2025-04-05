package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CustomerDAO;

/**
 * Servlet implementation class CustomerDeleteServlet
 */
@WebServlet("/deleteCustomer")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerDeleteServlet() {
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
		HttpSession session = request.getSession(false);
		int id = Integer.parseInt(request.getParameter("customerId"));
		CustomerDAO customerDAO = new CustomerDAO();

		if (session == null || session.getAttribute("user") == null) {

			request.setAttribute("message", "削除失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		try {

			customerDAO.deleteCustomer(id);

			RequestDispatcher dispatcher = request.getRequestDispatcher("deleteCompletion.jsp");
			dispatcher.forward(request, response);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();

			request.setAttribute("message", "削除失敗");
			request.getRequestDispatcher("error.jsp").forward(request, response);

		}

	}

}
