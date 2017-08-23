package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class AccountGrab extends JdbcServlet {

	private static final long serialVersionUID = 201708221657007L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = (String) request.getSession().getAttribute("username");
		this.doWrite(username, response.getWriter());
	}
	
}