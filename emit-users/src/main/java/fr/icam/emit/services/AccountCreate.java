package fr.icam.emit.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class AccountCreate extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 27L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		statement.setString(1, username);
		statement.setString(2, password);
	}
	
	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultset) throws Exception {
		return count > 0;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean done = this.doProcess(request);
		request.setAttribute("account", done);
	}
	
}