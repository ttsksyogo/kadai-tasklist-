package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();


		//該当のIDのメッセージ1をデータベースから取得

		Task task = em.find(Task.class, (Integer)(request.getSession().getAttribute("task_id")));

		em.getTransaction().begin();
		em.remove(task);
		em.getTransaction().commit();
		em.close();

		//セッションスコープ上の不要になったデータ削除
		request.getSession().removeAttribute("task_id");

		//indexページへリダイレクト
		response.sendRedirect(request.getContextPath() + "/index");
	}

}
