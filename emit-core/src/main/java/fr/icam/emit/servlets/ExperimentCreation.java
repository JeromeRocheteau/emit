package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;
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
		//Timestamp stopped = new Timestamp(experiment.getStopped());
		//statement.setLong(1, experiment.getId());
		statement.setTimestamp(1, started);
		//statement.setTimestamp(2, stopped);
		statement.setString(2, experiment.getMeasurand());
		statement.setString(3, experiment.getEnvironment());		
		
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
}
