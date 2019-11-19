package controllers.comment;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;
import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class CommentEditServlet
 */
@WebServlet("/comments/edit")
public class CommentEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Comment c = em.find(Comment.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        if(login_employee.getName().equals(c.getName())){
            request.setAttribute("comment", c);
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("time", c.getComment_date());
            request.getSession().setAttribute("comment_id", c.getId());

            Date time = new Date(System.currentTimeMillis());
            request.setAttribute("time", time);
            request.setAttribute("title", c.getTitle());
            request.setAttribute("content", c.getComment());
        }



        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/comments/edit.jsp");
        rd.forward(request, response);
    }

}
