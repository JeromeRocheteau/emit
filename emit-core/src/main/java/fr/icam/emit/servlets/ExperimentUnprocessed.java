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

import fr.icam.emit.entities.Environment;
import fr.icam.emit.entities.Experiment;
import fr.icam.emit.entities.Experiment_plan;
import fr.icam.emit.entities.Measurand;
import fr.icam.emit.entities.MeasurementSet;

public class ExperimentUnprocessed  extends JdbcQueryServlet<List<Experiment_plan>> { 
	
	private static final long serialVersionUID = 201704061652L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Experiment_plan> experiment_plan = this.doProcess(request);
		request.setAttribute("experiment_plan", experiment_plan);
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected List<Experiment_plan> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Experiment_plan> experiment_plan = new LinkedList<Experiment_plan>();
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
			
			String name = resultSet.getString("name");
			String uri = resultSet.getString("uri");
			Environment environmentS = new Environment(name,uri,false);
			
			long SetId = resultSet.getLong("exp_set_id");
			Timestamp achieved = resultSet.getTimestamp("achieved");
			if (resultSet.wasNull()) {
				stopped = null;
			}
			String instrument = resultSet.getString("instrument");
			
			MeasurementSet measurementSet = new MeasurementSet(SetId,"",achieved == null ? null : achieved.getTime(),instrument,0);
			
			Experiment experiment = new Experiment(id, started == null ? null : started.getTime(), stopped == null ? null : stopped.getTime(), measurand, environment);
			
			experiment_plan.add(new Experiment_plan(experiment,measurementSet, environmentS));
		}
		return experiment_plan;
	}
}