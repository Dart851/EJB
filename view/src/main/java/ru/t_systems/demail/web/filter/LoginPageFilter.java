package ru.t_systems.demail.web.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (request.getUserPrincipal() != null) {
			String navigateString = "";
			if (request.isUserInRole("admin")) {
				navigateString = "/admin/AdminHome.xhtml";

			} else if (request.isUserInRole("user")) {
				navigateString = "/user/NewMessage.xhtml";
			}
			response.sendRedirect(request.getContextPath() + navigateString);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}