package fr.icam.emit.services.brokers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Updater extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201710301015004L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		String uri = request.getParameter("uri");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			statement.setNull(1, Types.VARCHAR);
			statement.setNull(2, Types.VARCHAR);
		} else {
			statement.setString(1, username);
			statement.setString(2, password);
		}
		statement.setString(3, uri);
		statement.setString(4, user);		
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
