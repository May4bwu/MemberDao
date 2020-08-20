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
* Servlet implementation class EditMemberServlet
*/
@WebServlet("/editMember")
public class EditMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#doGet(HttpServletRequest request,
	* HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String strId = request.getParameter("id");
			Integer id = Integer.parseInt(strId);
			MemberDao memberDao = DaoFactory.createMemberDao();
			Member member = memberDao.findById(id);
			request.setAttribute("name", member.getName());
			request.setAttribute("age", member.getAge());
			request.setAttribute("typeId", member.getTypeId());
			request.setAttribute("address", member.getAddress());
			request.getRequestDispatcher("/WEB-INF/view/editMember.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request,
	* HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
				request.setAttribute("ageError", "年齢は整数で指定してください。");
				isError = true;
			}
		}
		// 会員種別の取得
		Integer typeId = Integer.parseInt(request.getParameter("typeId"));
		request.setAttribute("typeId", typeId);
		// 住所の取得
		String address = request.getParameter("address");
		request.setAttribute("address", address);
		// エラーが無ければデータの更新
		// エラーが有ればフォームの再表示
		if (!isError) {
			// データの更新
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				MemberDao memberDao = DaoFactory.createMemberDao();
				Member member = memberDao.findById(id);
				member.setName(name);
				member.setAge(age);
				member.setTypeId(typeId);
				member.setAddress(address);
				memberDao.update(member);
				request.getRequestDispatcher("/WEB-INF/view/editMemberDone.jsp")
						.forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} else {
			// フォームの再表示
			request.getRequestDispatcher("/WEB-INF/view/editMember.jsp")
					.forward(request, response);
		}
	}
}
