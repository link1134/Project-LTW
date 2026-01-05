package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test_chapter_detail
 */
@WebServlet("/follow-page")
public class FollowController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String title = "Follow Page";
    	String subTitle = "Đang theo dõi";
    	request.setAttribute("title", title);
    	request.setAttribute("subTitle", subTitle);
        System.out.println(">>> test_chapter_detail servlet RUNNING");

        RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/view/utility/record_and_tag.jsp");
        dispatcher.forward(request, response);
    }
}