package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public class AccountCheck extends JdbcQueryServlet<String> {

	private static final long serialVersionUID = 201708221657002L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "account-find");
		String username = this.doProcess(request);
		request.getSession().setAttribute("username", username);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		statement.setString(1, username);
		statement.setString(2, password);
	}

	@Override
	protected String doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		String username = null;
		if (resultSet.next()) {
			username = resultSet.getString("username");
		} else {
			throw new Exception("password doesn't match");			
		}
		return username;
	}

}