package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;

/**
 * Servlet implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String path = req.getRequestURI().substring(req.getContextPath().length());

		
		if (path.startsWith("/static/")) {
			chain.doFilter(request, response);
			return;
		}

		
		if (path.startsWith("/login") || path.startsWith("/register") || path.startsWith("/forgot-password")
				|| path.startsWith("/reset-password") || path.startsWith("/verify-otp")  || path.startsWith("/logout"))  {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession(false);

		
		if (session == null || session.getAttribute("user") == null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		User user = (User) session.getAttribute("user");

		
		if (path.startsWith("/admin") && !"ADMIN".equals(user.getRole())) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN); // 403
			return;
		}
		if (!path.startsWith("/admin") && "ADMIN".equals(user.getRole())) {
		    res.sendRedirect(req.getContextPath() + "/admin/story-list");
		    return;
		}

		
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
