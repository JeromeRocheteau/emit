package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Measurement;

public class MeasurementPage extends JdbcQueryServlet<List<Measurement>> {

	private static final long serialVersionUID = 201709041107004L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Measurement> measurements = this.doProcess(request);
		this.doWrite(measurements, response.getWriter());
	}

	
	@Override
	protected List<Measurement> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Measurement> measurements = new LinkedList<Measurement>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			Timestamp started = resultSet.getTimestamp("started");
			Timestamp stopped = resultSet.getTimestamp("stopped");
			String uuid = resultSet.getString("uuid");
			Measurement measurement = new Measurement(id, null, started.getTime(), stopped.getTime(), uuid, null);
			measurements.add(measurement);
		}
		return measurements;
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("feature"));
		Integer offset = Integer.valueOf(request.getParameter("offset"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		statement.setLong(1, id);
		statement.setInt(2, offset);
		statement.setInt(3, length);
	}
	
}