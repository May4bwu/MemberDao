package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDao;
import dao.DaoFactory;
import domain.Admin;

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
		request.getRequestDispatcher("/WEB-INF/view/login.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String loginId = request.getParameter("loginId");
			String loginPass = request.getParameter("loginPass");
			AdminDao adminDao = DaoFactory.createAdminDao();
			Admin admin = adminDao.findByLoginIdAndLoginPass(loginId, loginPass);
			if (admin != null) {
				request.getSession().setAttribute("loginId",
						admin.getLoginId());
				response.sendRedirect("listMember");
			} else {
				request.setAttribute("error", true);
				request.getRequestDispatcher("/WEB-INF/view/login.jsp")
						.forward(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
