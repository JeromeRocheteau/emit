package fr.icam.emit.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcUpdateServlet;
import com.google.gson.Gson;

import fr.icam.emit.entities.Measure;

public class MeasureCreation extends JdbcUpdateServlet<Boolean>{
	private static final long serialVersionUID = 201703061104L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
	
		 // Measure measure = gson.fromJson(request.getInputStream().toString(), Measure.class);
		Measure measure = gson.fromJson(jb.toString(), Measure.class);
		statement.setString(1, measure.getName());
		statement.setString(2, measure.getUnit());
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
