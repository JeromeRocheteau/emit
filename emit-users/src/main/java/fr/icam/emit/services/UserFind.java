package fr.icam.emit.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public class UserFind extends JdbcQueryServlet<String> {

	private static final long serialVersionUID = 21L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		statement.setString(1, username);
	}
	
	@Override
	protected String doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		if (resultSet.next()) {
			String username = resultSet.getString("username");
			return username;
		} else {
			throw new ServletException("username");
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doProcess(request);
	}

	
	
}
