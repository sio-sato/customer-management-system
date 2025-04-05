package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CalendarLogic;
import model.entity.CalendarBean;

/**
 * Servlet implementation class TaskCalendarSarvlet
 */
@WebServlet("/TaskCalendarServlet")
public class TaskCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskCalendarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");

		int year, month;
		Calendar cal = Calendar.getInstance();

		if (s_year != null && s_month != null) {
			try {
				year = Integer.parseInt(s_year);
				month = Integer.parseInt(s_month);
			} catch (NumberFormatException e) {
				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH は 0-11 のため +1 する
			}
		} else {
			//クエリパラメータが来ていないときは実行日時のカレンダーを生成する。
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
		}

		CalendarLogic logic = new CalendarLogic();
		CalendarBean cb = logic.createCalendar(year, month);
		request.setAttribute("cb", cb);

		//viewにフォワード
		RequestDispatcher rd = request.getRequestDispatcher("calendar.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
