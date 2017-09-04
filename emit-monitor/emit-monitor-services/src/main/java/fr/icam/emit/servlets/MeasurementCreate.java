package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class MeasurementCreate extends JdbcUpdateServlet<Long> {

	private static final long serialVersionUID = 201709041107002L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = this.doProcess(request);
		request.setAttribute("measurement", id);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long feature = Long.valueOf(request.getParameter("id"));
		String uuid = (String) request.getAttribute("uuid");
		statement.setString(1, uuid);
		statement.setLong(2, feature);
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