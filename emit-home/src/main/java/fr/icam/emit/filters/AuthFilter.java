package fr.icam.emit.filters;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {
	
	private String landing;
	
	private String[] filtering;
	
	public void init(FilterConfig config) throws ServletException {
		landing = config.getInitParameter("landing");
		filtering = config.getInitParameter("filtering").split(",");
	}
	
	private boolean canFilter(String path) {
		if (path.equals(File.separator + landing)) {
			return true;
		}
		for (String filter : filtering) {
			if (path.equals(filter)) {
				return true;
			}
		}
		return false;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (this.canFilter(path)) {
        	chain.doFilter(request, response);
        } else {
        	String username = (String) httpRequest.getSession().getAttribute("username");
        	if (username == null) {
        		httpResponse.sendRedirect(landing);
        	} else {
        		chain.doFilter(request, response);
        	}
		}
	}

	public void destroy() { }

}
