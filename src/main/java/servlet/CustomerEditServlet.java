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
 * Servlet implementation class CustomerEditServlet
 */
@WebServlet("/editCustomer")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<AreaBean> AreaList = null;
		List<UserBean> UserList = null;
		CustomerBean customer = null;

		CustomerDAO dao = new CustomerDAO();
		AreaDAO Adao = new AreaDAO();
		UserDAO Udao = new UserDAO();

		String customerId = request.getParameter("customerId");
		//		customerIdがnullだとエラーになる
		if (customerId == null || customerId.isEmpty()) {
			response.sendRedirect("error.jsp");
			return;
		}
		int id = Integer.parseInt(customerId);
		try {
			customer = dao.getCustomer(id);
			//			ユーザーが選択した顧客idがDBになければエラーになる
			if (customer == null) {
				response.sendRedirect("error.jsp");
				return;
			}

			AreaList = Adao.getAllArea();
			UserList = Udao.getAllUser();

			request.getSession().setAttribute("selectedAreaCode", customer.getAreaCode());
			request.getSession().setAttribute("selectedUserName", customer.getUserName());
			request.setAttribute("customer", customer);
			request.setAttribute("AreaList", AreaList);
			request.setAttribute("userList", UserList);
			request.setAttribute("customerId", id); //id情報を引き継げるようにする

			RequestDispatcher dispatcher = request.getRequestDispatcher("customerEdit.jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//		HttpSession session = request.getSession();
		CustomerBean customer = new CustomerBean();
		CustomerDAO dao = new CustomerDAO();
		int count = 0; //処理件数を表すのに使用

		try {
			int id = Integer.parseInt(request.getParameter("customerId"));

			//編集画面からユーザーが入力した値をそれぞれ取得
			String name = request.getParameter("customerName");
			String nameKana = request.getParameter("customerNameKana");
			String post = request.getParameter("postalCode");
			String address = request.getParameter("address");
			String area = request.getParameter("area");
			String Pname = request.getParameter("personName");
			String PnameKana = request.getParameter("personNameKana");
			String tel = request.getParameter("personTel");
			String userId = (String) request.getParameter("user");
			System.out.println("今ここ" + userId);

			//顧客名と地区名がnullだとエラー
			if (name == null || name.trim().isEmpty() || area == null) {
				request.setAttribute("message", "更新失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
				return; //エラーが出た時はリストが更新されないようにする
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
			customer.setCustomerId(id);

			count = dao.updateCustomer(customer); //DBで更新された値の件数

			System.out.println("今ここ2" + customer.getUserId());

			AreaBean selectedArea = null;
			UserBean selectedUser = null;
			AreaDAO areaDAO = new AreaDAO();
			UserDAO userDAO = new UserDAO();
			List<AreaBean> areaList = areaDAO.getAllArea();
			List<UserBean> userList = userDAO.getAllUser();
			for (AreaBean Area : areaList) {
				if (Area.getAreaCode().equals(area)) {
					selectedArea = Area;
					break;
				}
			}
			for (UserBean User : userList) {
				if (User.getUserId().equals(userId)) {
					selectedUser = User;
					break;
				}
			}

			if (count > 0) { //更新する件数が全て空白の場合はエラー画面へ遷移

				List<CustomerBean> customerList = null; //参照型の変数がどこも指していない状態を表す

				try { //ファイル操作やDB接続、ユーザー入力など予期しないエラーが発生した場合に適切に処理
					customerList = dao.getAllCustomer(); //全てのリストを表示させるための代入
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace(); //エラーが発生した際に詳細な情報をコンソールに表示してくれる
				}
				request.setAttribute("customerList", customerList);
				request.setAttribute("edit", customer);
				request.setAttribute("area", selectedArea);
				request.setAttribute("user", selectedUser);
				RequestDispatcher rd = request.getRequestDispatcher("editComplete.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "更新失敗");
				RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);

			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); //
		}
	}
}
