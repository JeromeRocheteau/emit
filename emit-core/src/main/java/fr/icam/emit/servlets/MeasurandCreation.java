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

import fr.icam.emit.entities.Measurand;

public class MeasurandCreation extends JdbcUpdateServlet<Boolean> {
private static final long serialVersionUID = 201703070922L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();		
		// String Json = this.getStringJson(request);
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		 // Measure measure = gson.fromJson(request.getInputStream().toString(), Measure.class);
		Measurand measurand = gson.fromJson(reader, Measurand.class);
		statement.setString(1, measurand.getProcess());
		statement.setString(2, measurand.getName());
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
