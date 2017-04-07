package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Experiment;
import fr.icam.emit.entities.Measurand;

public class ExperimentUnprocessed  extends JdbcQueryServlet<Experiment> { 
	
	private static final long serialVersionUID = 201704061652L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Experiment experiment = this.doProcess(request);
		request.setAttribute("experiment", experiment);
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected Experiment doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		Experiment experiment = null;
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			Timestamp started = resultSet.getTimestamp("started");
			if (resultSet.wasNull()) {
				started = null;
			}
			Timestamp stopped = resultSet.getTimestamp("stopped");
			if (resultSet.wasNull()) {
				stopped = null;
			}
			String process = resultSet.getString("measurand");
			String environment = resultSet.getString("environment");
			Measurand measurand = new Measurand(process, null, null);
			experiment = new Experiment(id, started == null ? null : started.getTime(), stopped == null ? null : stopped.getTime(), measurand, environment);
		}
		return experiment;
	}
}