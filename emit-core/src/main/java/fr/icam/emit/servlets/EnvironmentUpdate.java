package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.icam.emit.entities.Environment;

public class EnvironmentUpdate extends JdbcUpdateServlet<Boolean>{
private static final long serialVersionUID = 201703080901L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<List<Environment>>(){}.getType();
		List<Environment> environment = gson.fromJson(reader,listType);
		
		statement.setString(3, environment.get(0).getUri());
		statement.setString(1, environment.get(1).getUri());
		statement.setString(2, environment.get(1).getName());
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