package ru.t_systems.demail.web.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SecurePageFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	    HttpServletResponse hsr = (HttpServletResponse) res;
	    hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    hsr.setDateHeader("Expires", 0); // Proxies.
	    chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}