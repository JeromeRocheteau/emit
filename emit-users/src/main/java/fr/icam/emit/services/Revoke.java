package fr.icam.emit.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class Revoke extends JdbcServlet {

	private static final long serialVersionUID = 26L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "pass-delete");
		Boolean deleted = (Boolean) request.getAttribute("deleted");
		this.doWrite(deleted, response.getWriter());
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "pass-delete");
		Boolean deleted = (Boolean) request.getAttribute("deleted");
		this.doWrite(deleted, response.getWriter());
	}
	
}
