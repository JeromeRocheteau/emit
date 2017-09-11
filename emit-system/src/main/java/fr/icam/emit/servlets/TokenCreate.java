package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class TokenCreate extends JdbcUpdateServlet<Long> {

	private static final long serialVersionUID = 201709111112003L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = this.doProcess(request);
		request.setAttribute("token", id);
		this.doCall(request, response, "access-create");
		Boolean done = (Boolean) request.getAttribute("access");
		this.doWrite(done, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String uuid = UUID.randomUUID().toString();
		Long feature = Long.valueOf(request.getParameter("feature"));
		String username = request.getUserPrincipal().getName();
		statement.setString(1, uuid);
		statement.setLong(2, feature);
		statement.setString(3, username);
	}
	
	@Override
	protected Long doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		Long id = null;
		if (resultSet.next()) {
			id = resultSet.getLong(1);
		}
		return id;
	}

}
