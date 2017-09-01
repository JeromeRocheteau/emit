package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class FeatureCreate extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201708251500002L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		Integer factor = Integer.valueOf(request.getParameter("factor"));
		Long measure = Long.valueOf(request.getParameter("measure"));
		Long instrument = Long.valueOf(request.getParameter("instrument"));
		statement.setString(1, name);
		statement.setInt(2, factor);
		statement.setLong(3, measure);
		statement.setLong(4, instrument);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
}