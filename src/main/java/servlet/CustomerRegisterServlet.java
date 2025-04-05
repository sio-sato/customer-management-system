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
import model.dao.UserDAO;
import model.entity.AreaBean;
import model.entity.CustomerBean;
import model.entity.UserBean;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/registerCustomer")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<AreaBean> areaList = null;
		List<UserBean> userList = null;
		AreaDAO areaDAO = new AreaDAO();
		UserDAO userDAO = new UserDAO();

		try {
			areaList = areaDAO.getAllArea();
			userList = userDAO.getAllUser();
			request.setAttribute("areaList", areaList);
			request.setAttribute("userList", userList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("customerRegister.jsp");
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
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		//		HttpSession session = request.getSession();

		int count = 0;

		CustomerDAO dao = new CustomerDAO();
		CustomerBean customer = new CustomerBean();
		AreaDAO areaDAO = new AreaDAO();
		AreaBean selectedArea = null;
		UserDAO userDAO = new UserDAO();
		UserBean selectedUser = null;

		try {
			String name = request.getParameter("customerName");
			String nameKana = request.getParameter("customerNameKana");
			String post = request.getParameter("postalCode");
			String address = request.getParameter("address");
			String area = request.getParameter("area");
			String Pname = request.getParameter("personName");
			String PnameKana = request.getParameter("personNameKana");
			String tel = request.getParameter("personTel");
			String userId = (String) request.getParameter("user");

			//			顧客名と地区名は必ず入力されているようにする
			if (name == null || name.trim().isEmpty() || area == null) {
				request.setAttribute("message", "登録失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
				return;
			}

			customer.setCustomerName(name);
			customer.setCustomerNameKana(nameKana);
			customer.setPostalCode(post);
			customer.setAddress(address);
			customer.setAreaCode(area);
			customer.setContactPersonName(Pname);
			customer.setContactPersonNameKana(PnameKana);
			customer.setContactPersonTel(tel);
			customer.setUserId(userId);

			//			ユーザが入力した地区名とリストにある地区名で一致したものをselectedAreaに保管しjspで表示させる
			List<AreaBean> areaList = areaDAO.getAllArea();
			for (AreaBean Area : areaList) {
				if (Area.getAreaCode().equals(area)) {
					selectedArea = Area;
					break;
				}
			}
			List<UserBean> userList = userDAO.getAllUser();
			for (UserBean User : userList) {
				if (User.getUserId().equals(userId)) {
					selectedUser = User;
					break;
				}
			}

			count = dao.addCustomer(customer);

			if (count > 0) {

				List<CustomerBean> customerList = null;
				customerList = dao.getAllCustomer();
				request.setAttribute("customerList", customerList);
				request.setAttribute("register", customer);
				request.setAttribute("area", selectedArea);
				request.setAttribute("user", selectedUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("registerCompletion.jsp");
				dispatcher.forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "登録失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}

	}

}
