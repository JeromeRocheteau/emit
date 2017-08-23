package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public class AccountFind extends JdbcQueryServlet<String> {

	private static final long serialVersionUID = 201708221657001L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = this.doProcess(request);
		request.setAttribute("username", username);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		statement.setString(1, username);
	}

	@Override
	protected String doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		String username = null;
		if (resultSet.next()) {
			username = resultSet.getString("username");			
		} else {
			throw new Exception("username not found");			
		}
		return username;
	}

}