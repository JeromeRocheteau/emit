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

import fr.icam.emit.entities.MeasurementSet;


public class MeasurementSetList extends JdbcQueryServlet<List<MeasurementSet>> {
	private static final long serialVersionUID = 201704011619L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected List<MeasurementSet> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<MeasurementSet> MeasurementSets = new LinkedList<MeasurementSet>();
        while (resultSet.next()) {
        	int id = resultSet.getInt("id");
        	String data = resultSet.getString("data");
        	long achieved = resultSet.getLong("achieved");
        	String uri = resultSet.getString("uri");
        	int experimentId =resultSet.getInt("experimentId");
        	int measurementId = resultSet.getInt( "measurementId");
            MeasurementSets.add(new MeasurementSet(id, data,achieved,uri,experimentId,measurementId));
        }
        return MeasurementSets;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<MeasurementSet> MeasurementSets = this.doProcess(request);
        this.doPrint(MeasurementSets, response);
	}
}
