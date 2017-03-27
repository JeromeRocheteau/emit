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

import fr.icam.emit.entities.Environment;

public class ObserveeDelete extends JdbcUpdateServlet<Boolean>{
private static final long serialVersionUID = 201703070926L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);		 
		Environment observee = gson.fromJson(reader, Environment.class);
		statement.setString(1, observee.getUri());
		
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
