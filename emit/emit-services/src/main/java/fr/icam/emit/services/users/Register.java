package fr.icam.emit.services.users;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Register extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201711031818001L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		String rolename = request.getParameter("rolename");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if (password == null || password.isEmpty()) {
			throw new ServletException("empty-password");
		} else if (confirmPassword == null || confirmPassword.isEmpty()) {
			throw new ServletException("empty-confirm-password");
		} else if (password.equals(confirmPassword)) {
			statement.setString(1, username);			
			statement.setString(2, rolename);			
			statement.setString(3, password);
		} else {
			throw new ServletException("password-match-clash");
		}
	}
	
	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}

}
