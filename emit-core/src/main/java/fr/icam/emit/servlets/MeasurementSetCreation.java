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

import fr.icam.emit.entities.MeasurementSet;

public class MeasurementSetCreation extends JdbcUpdateServlet<Boolean>{
	private static final long serialVersionUID = 201703071416L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
		Gson gson = new Gson();	
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		 // Measure measure = gson.fromJson(request.getInputStream().toString(), Measure.class);
		MeasurementSet measurementSet = gson.fromJson(reader, MeasurementSet.class);
		Timestamp time = new Timestamp(measurementSet.getAchieved());
		statement.setLong(1, measurementSet.getId());
		statement.setString(2, measurementSet.getData());
		statement.setTimestamp(3, time);
		statement.setLong(4, measurementSet.getExperiment());
		statement.setString(5, measurementSet.getObserver());		
		
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
