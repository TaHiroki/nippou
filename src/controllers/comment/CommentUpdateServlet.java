package controllers.comment;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Comment;
import models.validators.CommentValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CommentUpdateServlet
 */
@WebServlet("/comments/update")
public class CommentUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentUpdateServlet() {
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

            Comment c = em.find(Comment.class, (Integer)(request.getSession().getAttribute("comment_id")));

            c.setComment_date(Date.valueOf(request.getParameter("report_date")));
            c.setTitle(request.getParameter("title"));
            c.setComment(request.getParameter("content"));
            c.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = CommentValidator.validate(c);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("comment", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/comments/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().removeAttribute("comment_id");

                response.sendRedirect(request.getContextPath() + "/reports/show");
            }
        }
    }

}
