package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.HashPassword;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); //文字として表示
		String id = request.getParameter("id"); //ユーザが入力したUserIdを取得
		String pass = request.getParameter("password");
		String password = null;

		UserBean user = new UserBean();
		UserDAO dao = new UserDAO();
		HashPassword hash = new HashPassword();

		try {
			user = dao.getUser(id);
			password = hash.toHash(pass); //ハッシュ化
			System.out.println(password);

		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		//DAOで処理したidの結果がnullじゃなく、ユーザが入力したpasswordとDAOで確認したpasswordが一致した場合はログイン
		if (user != null && password.equals(user.getPassword())) {
			HttpSession session = request.getSession(); //セッション新規
			session.setAttribute("user", id); //UserBean userユーザから取得したidをセットしセッションに保存
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("manager", user.getRoleId());
			System.out.println(user.getRoleId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("menu.jsp"); //urlに代入されている場所に変移
			dispatcher.forward(request, response);

		} else {
			request.setAttribute("error", "ユーザーIDまたはパスワードが違います");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp"); //urlに代入されている場所に変移
			dispatcher.forward(request, response);
		}

	}

}
