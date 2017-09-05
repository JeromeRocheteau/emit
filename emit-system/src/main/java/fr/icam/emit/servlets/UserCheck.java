package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class UserCheck extends JdbcServlet {

	private static final long serialVersionUID = 2017090417000001L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getUserPrincipal().getName();
		this.doWrite(username, response.getWriter());
	}

}
