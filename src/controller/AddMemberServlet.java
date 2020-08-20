package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.MemberDao;
import domain.Member;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#doGet(HttpServletRequest request,
	* HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp")
				.forward(request, response);
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request,
	* HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		// エラーフラグ
		boolean isError = false;
		// パラメータの取得
		// 名前の取得とバリデーション
		String name = request.getParameter("name");
		request.setAttribute("name", name);
		if (name == null || name.isEmpty()) {
			request.setAttribute("nameError", "名前が未入力です。");
			isError = true;
		}
		// 年齢の取得とバリデーション
		Integer age = null;
		String strAge = request.getParameter("age");
		request.setAttribute("age", strAge);
		if (strAge != null && !strAge.isEmpty()) {
			try {
				age = Integer.parseInt(strAge);
			} catch (NumberFormatException e) {
				request.setAttribute("ageError",
						"年齢は整数で指定してください。");
				isError = true;
			}
		}
		// 会員種別の取得
		Integer typeId = Integer.parseInt(request.getParameter("typeId"));
		request.setAttribute("typeId", typeId);
		// 住所の取得
		String address = request.getParameter("address");
		request.setAttribute("address", address);
		// エラーが無ければデータの追加
		// エラーが有ればフォームの再表示
		if (!isError) {
			//データの追加
			Member member = new Member();
			member.setName(name);
			member.setAge(age);
			member.setTypeId(typeId);
			member.setAddress(address);
			try {
				MemberDao memberDao = DaoFactory.createMemberDao();
				memberDao.insert(member);
				request.getRequestDispatcher("/WEB-INF/view/addMemberDone.jsp")
						.forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		 } else {
			 // フォームの再表示
			 request.getRequestDispatcher("/WEB-INF/view/addMember.jsp")
			 .forward(request, response);
		}
	}
}
