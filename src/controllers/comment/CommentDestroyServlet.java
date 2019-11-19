package controllers.comment;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;
import utils.DBUtil;

/**
 * Servlet implementation class CommentDestroyServlet
 */
@WebServlet("/comments/destroy")
public class CommentDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Comment c = em.find(Comment.class, Integer.parseInt(request.getParameter("id")));

            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/reports/show");
        }
    }

}
