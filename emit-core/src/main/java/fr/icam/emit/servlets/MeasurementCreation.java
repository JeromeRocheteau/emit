package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;
import com.google.gson.Gson;

import fr.icam.emit.entities.Measurement;
import fr.icam.emit.entities.MeasurementSet;

public class MeasurementCreation extends JdbcUpdateServlet<Boolean>{
private static final long serialVersionUID = 201703071443L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
		Measurement measurement = (Measurement) request.getAttribute("measurement");
		
		//statement.setLong(1, measurement.getId());
		
		statement.setString(1, measurement.getData());
		statement.setLong(2, measurement.getMeasurementSet());
		statement.setString(3, measurement.getMeasure());
		statement.setLong(4, measurement.getFeature());
		
		/*
		statement.setString(1, "emit-1493730523049.json");
		statement.setLong(2, 22);
		statement.setString(3, "tention");
		*/
		
		/*
		statement.setString(1, "tu va marcher");
		statement.setLong(2, 22);
		statement.setString(3, "tention");
		*/
		
		
		/*Gson gson = new Gson();	
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		Measurement measurement = gson.fromJson(reader, Measurement.class);
				
		*/
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		boolean done = this.doProcess(request);		
		this.doWrite(done, response.getWriter());
	}
	
}
