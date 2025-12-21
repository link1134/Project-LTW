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
@WebServlet("/history-page")
public class test_history_page extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(">>> test_chapter_detail servlet RUNNING");

        RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/view/history_page/history_page.jsp");
        dispatcher.forward(request, response);
    }
}