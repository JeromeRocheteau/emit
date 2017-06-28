package fr.icam.emit.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.entities.User;

public class Profile extends JdbcServlet {

	private static final long serialVersionUID = 20L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "user-info");
		User user = (User) request.getAttribute("user");
		this.doWrite(user, response.getWriter());
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "user-info");
		User user = (User) request.getAttribute("user");
		this.doWrite(user, response.getWriter());
	}
	
}
