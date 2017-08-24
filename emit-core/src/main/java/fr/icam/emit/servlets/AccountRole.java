package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class AccountRole extends JdbcServlet {

	private static final long serialVersionUID = 201708221657008L;
	
	private String admin;
	
	@Override
	public void init() throws ServletException {
		super.init();
		admin = this.getInitParameter("admin-name");
	}
		
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String rolename = (String) request.getSession().getAttribute("rolename");
		this.doWrite(rolename.equals(admin), response.getWriter());
	}
	
}