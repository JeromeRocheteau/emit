package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Measurement;

public class MeasurementItem extends JdbcQueryServlet<Measurement> {

	private static final long serialVersionUID = 201709041107001L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Measurement measurement = this.doProcess(request);
		this.doWrite(measurement, response.getWriter());
	}

	
	@Override
	protected Measurement doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		Measurement measurement = null;
		if (resultSet.next()) {
			Long id = resultSet.getLong("id");
			Timestamp started = resultSet.getTimestamp("started");
			Timestamp stopped = resultSet.getTimestamp("stopped");
			if (resultSet.wasNull()) {
				stopped = null;
			}
			String uuid = resultSet.getString("uuid");
			measurement = new Measurement(id, null, started.getTime(), stopped == null ? null : stopped.getTime(), uuid, null);
		}
		return measurement;
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, id);
	}
	
}