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

import fr.icam.emit.entities.Feature;

public class FeatureUpdate extends JdbcUpdateServlet<Boolean>{
	
	private static final long serialVersionUID = 201704261500L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Gson gson = new Gson();		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<List<Feature>>(){}.getType();
		List<Feature> feature = gson.fromJson(reader,listType);
		
		statement.setLong(7, feature.get(0).getId());
		statement.setLong(1, feature.get(1).getId());
		statement.setString(2, feature.get(1).getMeasure());
		statement.setString(3, feature.get(1).getInstrument());
		statement.setLong(4, feature.get(1).getNo_order());
		statement.setString(5, feature.get(1).getName());
		statement.setInt(6, feature.get(1).getFactor());
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
