package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class EnvironmentUpdate extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201708241533004L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		String arch = (String) request.getAttribute("uri");
		String os = (String) request.getAttribute("uri");
		String version = (String) request.getAttribute("name");
		statement.setString(1, arch);
		statement.setString(2, os);
		statement.setString(3, version);
		statement.setLong(4, id);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
}