package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class MeasureUpdate extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201708241432004L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String unit = request.getParameter("unit");
		statement.setString(1, name);
		statement.setString(2, unit);
		statement.setLong(3, id);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
}