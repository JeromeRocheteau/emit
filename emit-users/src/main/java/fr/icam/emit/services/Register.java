package fr.icam.emit.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class Register extends JdbcServlet {

	private static final long serialVersionUID = 28L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "user-create");
		this.doCheck(request, "user");
		this.doCall(request, response, "account-create");
		this.doCheck(request, "account");
		this.doWrite(Boolean.TRUE, response.getWriter());
	}

	private void doCheck(HttpServletRequest request, String name) throws ServletException {
		Boolean done = (Boolean) request.getAttribute(name);
		if (done == null) {
			throw new ServletException(name);
		} else if (done) {
			return;
		} else {
			throw new ServletException(name);
		}
	}
	
}
