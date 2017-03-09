package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcUpdateServlet;
import com.google.gson.Gson;

import fr.icam.emit.entities.Measurement;

public class MeasurementCreation extends JdbcUpdateServlet<Boolean>{
private static final long serialVersionUID = 201703071443L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
		Gson gson = new Gson();	
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		 // Measure measure = gson.fromJson(request.getInputStream().toString(), Measure.class);
		Measurement measurement = gson.fromJson(reader, Measurement.class);
		statement.setLong(1, measurement.getId());
		statement.setString(2, measurement.getData());
		statement.setLong(3, measurement.getMeasurementSet());
		statement.setString(4, measurement.getMeasure());			
		
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