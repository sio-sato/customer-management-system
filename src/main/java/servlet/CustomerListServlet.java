package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CustomerDAO;
import model.entity.CustomerBean;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/listCustomer")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	//	顧客一覧に全リストを表示させる
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		List<CustomerBean> customer = null;
		CustomerDAO dao = new CustomerDAO();

		try {
			customer = dao.getAllCustomer();
			request.setAttribute("customerList", customer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("customerList.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		CustomerBean customer = new CustomerBean();
		CustomerDAO dao = new CustomerDAO();

		try {
			String keyword = request.getParameter("search");
			System.out.println(keyword);
			customer.setCustomerName(keyword);
			customer.setCustomerNameKana(keyword);
			customer.setPostalCode(keyword);
			customer.setAddress(keyword);
			customer.setAreaName(keyword);
			customer.setContactPersonName(keyword);
			customer.setContactPersonNameKana(keyword);
			customer.setContactPersonTel(keyword);
			customer.setUserName(keyword);
            //	検索する前に古いリストデータを削除し、HITしたリストのみを表示する		
			List<CustomerBean> customerList = new ArrayList<CustomerBean>();
			customerList.clear();
			customerList = dao.searchCustomer(customer);
			System.out.println("サーブレット" + customer.getCustomerName());

			request.setAttribute("customerList", customerList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("customerList.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
