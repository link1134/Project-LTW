package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test_story_page
 */
@WebServlet("/story_page")
public class test_story_page extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">>> test_story_page servlet RUNNING");

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/story_page/story.jsp");
        dispatcher.forward(request, response);
    }
}
