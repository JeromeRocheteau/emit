package fr.icam.emit.servlets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Environment;
import fr.icam.emit.entities.Experiment;
import fr.icam.emit.entities.Measurand;

public class ExperimentList extends Lister<Experiment> {

	private static final long serialVersionUID = 201709041420001L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, id);
	}
	
	@Override
	protected List<Experiment> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Experiment> experiments = new LinkedList<Experiment>();
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
			Long measurandId = resultSet.getLong("measurandId");
			String measurandProcess = resultSet.getString("measurandProcess");
			Long environementId = resultSet.getLong("environmentId");
			String environementUri = resultSet.getString("environementUri");
			String environementArch = resultSet.getString("environementArch");
			String environementOs = resultSet.getString("environementOs");
			String environementVersion = resultSet.getString("environementVersion");
			Measurand measurand = new Measurand(measurandId, null, measurandProcess);
			Environment environment = new Environment(environementId, null, environementUri, environementArch, environementOs, environementVersion);
			Experiment experiment = new Experiment(id, null, started == null ? null : started.getTime(), stopped == null ? null : stopped.getTime(), measurand, environment);
			experiments.add(experiment);
		}
		return experiments;
	}
	
}