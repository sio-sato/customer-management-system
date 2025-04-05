package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AccountDAO;
import model.dao.UserDAO;
import model.entity.AccountBean;
import model.entity.HashPassword;
import model.entity.UserBean;

/**
 * Servlet implementation class NewUserRegister
 */
@WebServlet("/NewUserRegister")
public class NewUserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserRegister() {
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

		List<AccountBean> list = null;
		AccountDAO dao = new AccountDAO();

		try {
			list = dao.getAllAccount();
			request.setAttribute("accountList", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("newUserRegister.jsp");
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

		UserBean user = new UserBean();
		UserDAO dao = new UserDAO();
		HashPassword hash = new HashPassword();
		List<AccountBean> list = null;
		AccountDAO Adao = new AccountDAO();

		int count = 0;

		String id = request.getParameter("id");
		String pass = request.getParameter("password");
		String name = request.getParameter("name");
		int role = Integer.parseInt(request.getParameter("roleId"));
		String password = null;

		try {
			//入力ミスでエラーが出た場合も、アカウント権限のプルダウンを表示させるため
			list = Adao.getAllAccount();
			request.setAttribute("accountList", list);

			if (pass == null || pass.trim().isEmpty()) {
				request.setAttribute("error", "すべての項目を正しい値で入力してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("newUserRegister.jsp");
				dispatcher.forward(request, response);
				return;
			}
			password = hash.toHash(pass); //ハッシュ化
			System.out.println(password);

			user.setUserId(id);
			user.setPassword(password);
			user.setUserName(name);
			user.setRoleId(role);

			count = dao.addUser(user);

			if (count > 0) {

				if (id == null || pass == null || name == null || id.trim().isEmpty() || pass.trim().isEmpty()
						|| name.trim().isEmpty()) {

					request.setAttribute("error", "すべての項目を正しい値で入力してください");
					RequestDispatcher dispatcher = request.getRequestDispatcher("newUserRegister.jsp");
					dispatcher.forward(request, response);
				} else {

					request.setAttribute("user", id);
					request.setAttribute("userName", name);
					RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {

			e.printStackTrace();

			request.setAttribute("error", "すべての項目を正しい値で入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("newUserRegister.jsp");
			dispatcher.forward(request, response);
		}
	}

}
