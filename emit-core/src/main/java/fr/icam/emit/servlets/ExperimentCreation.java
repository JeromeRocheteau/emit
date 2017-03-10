package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcUpdateServlet;
import com.google.gson.Gson;


import fr.icam.emit.entities.Experiment;

public class ExperimentCreation extends JdbcUpdateServlet<Boolean> {
	private static final long serialVersionUID = 201703071406L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();  
               
		
		
		// String Json = this.getStringJson(request);
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		 // Measure measure = gson.fromJson(request.getInputStream().toString(), Measure.class);
		Experiment experiment = gson.fromJson(reader, Experiment.class);
		Timestamp started = new Timestamp(experiment.getStarted());
		Timestamp stopped = new Timestamp(experiment.getStopped());
		statement.setLong(1, experiment.getId());
		statement.setTimestamp(2, started);
		statement.setTimestamp(3, stopped);
		statement.setString(4, experiment.getMeasurand());
		statement.setString(5, experiment.getObservee());		
		
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean done = this.doProcess(request);
		this.doPrint(done, response);
	}
}
