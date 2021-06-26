package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class CreateSarvlet
 */
@WebServlet("/create")
public class CreateSarvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSarvlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			EntityManager em = DBUtil.createEntityManager();
			Task task = new Task();

			String title = request.getParameter("title");
			task.setTitle(title);

			String content = request.getParameter("content");
			task.setContent(content);

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			task.setCreated_at(currentTime);
			task.setUpdated_at(currentTime);

			em.getTransaction().begin();
			em.persist(task);
			em.getTransaction().commit();
			em.close();

			response.sendRedirect(request.getContextPath() + "/index");


	}

}
