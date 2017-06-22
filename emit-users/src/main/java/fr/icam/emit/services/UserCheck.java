package fr.icam.emit.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public class UserCheck extends JdbcQueryServlet<String> {

	private static final long serialVersionUID = 22L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		String paswword = request.getParameter("password");
		statement.setString(1, username);
		statement.setString(2, paswword);
	}
	
	@Override
	protected String doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		if (resultSet.next()) {
			String username = resultSet.getString("username");
			return username;
		} else {
			throw new ServletException("password");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doProcess(request);
	}
	
}
