package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcUpdateServlet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.icam.emit.entities.Measure;

public class MeasureUpdate extends JdbcUpdateServlet<Boolean>{
	private static final long serialVersionUID = 201703080932L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();		
		//String Json = this.getStringJson(request); 		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<List<Measure>>(){}.getType();
		 // Measurand Measurand = gson.fromJson(request.getInputStream().toString(), Measurand.class);
		List<Measure> observer = gson.fromJson(reader,listType);
		
		statement.setString(3, observer.get(0).getName());
		statement.setString(1, observer.get(1).getName());
		statement.setString(2, observer.get(1).getUnit());
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
