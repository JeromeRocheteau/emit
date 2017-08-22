package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class AccountStore extends JdbcServlet {

	private static final long serialVersionUID = 201708221657003L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "account-find");
		this.doCall(request, response, "account-check");
		String username = (String) request.getAttribute("username");
		request.getSession().setAttribute("username", username);
		this.doWrite(username, response.getWriter());
	}
	
}