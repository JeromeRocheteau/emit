package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class FeatureEnable extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201708251500005L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doProcess(request);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		Long measurement = (Long) request.getAttribute("measurement");
		statement.setLong(1, measurement);
		statement.setLong(2, id);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
}