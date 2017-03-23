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

import fr.icam.emit.entities.Observer;

public class ObserverUpdate extends JdbcUpdateServlet<Boolean>{
	private static final long serialVersionUID = 201703080934L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();		
		//String Json = this.getStringJson(request); 		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<List<Observer>>(){}.getType();
		 // Measurand Measurand = gson.fromJson(request.getInputStream().toString(), Measurand.class);
		List<Observer> observer = gson.fromJson(reader,listType);
		
		statement.setString(3, observer.get(0).getUri());
		statement.setString(1, observer.get(1).getUri());
		statement.setString(2, observer.get(1).getName());
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
