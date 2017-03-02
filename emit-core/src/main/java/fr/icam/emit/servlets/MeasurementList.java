package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcQueryServlet;

import fr.icam.emit.entities.Measurement;

public class MeasurementList extends JdbcQueryServlet<List<Measurement>> {

	private static final long serialVersionUID = 201703011438L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected List<Measurement> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Measurement> measurements = new LinkedList<Measurement>();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String data = resultSet.getString("data");
            String name = resultSet.getString("name");
            measurements.add(new Measurement(id, data, name));
        }
        return measurements;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Measurement> measurements = this.doProcess(request);
        this.doPrint(measurements, response);
	}

}