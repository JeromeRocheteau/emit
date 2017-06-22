package fr.icam.emit.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.User;

public class UserInfo extends JdbcQueryServlet<User> {

	private static final long serialVersionUID = 29L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String passphrase = request.getParameter("passphrase");
		statement.setString(1, passphrase);
	}
	
	@Override
	protected User doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		if (resultSet.next()) {
			String username = resultSet.getString("username");
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			String type = resultSet.getString("type");
			return new User(username, firstname, lastname, type);
		} else {
			throw new ServletException("user");
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = this.doProcess(request);
		request.setAttribute("user", user);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = this.doProcess(request);
		request.setAttribute("user", user);
	}
	
}
