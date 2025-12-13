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
@WebServlet("/chapter-detail")
public class test_chapter_detail extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">>> test_chapter_detail servlet RUNNING");

        RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/view/chapter_detail_page/chapter_detail.jsp");
        dispatcher.forward(request, response);
    }
}
