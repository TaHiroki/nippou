package controllers.comment;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;

/**
 * Servlet implementation class CommentsNewServlet
 */
@WebServlet("/comments/new")
public class CommentsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Comment c = new Comment();
        c.setComment_date(new Date(System.currentTimeMillis()));
        request.setAttribute("comment", c);
        Date time = new Date(System.currentTimeMillis());
        request.setAttribute("time", time);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/comments/new.jsp");
        rd.forward(request, response);
    }

}
