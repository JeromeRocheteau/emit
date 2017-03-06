package fr.icam.emit.servlets;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
        	long id = resultSet.getInt("id");
        	String data = resultSet.getString("data");
        	Timestamp achieved = resultSet.getTimestamp("achieved");
        	String uri = resultSet.getString("observer");
        	long experimentId =resultSet.getInt("experiment");        	
            MeasurementSets.add(new MeasurementSet(id, data,achieved,uri,experimentId));
            //MeasurementSets.add(new MeasurementSet(id, data,uri,experimentId));
        }
        return MeasurementSets;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<MeasurementSet> MeasurementSets = this.doProcess(request);
        this.doPrint(MeasurementSets, response);
	}
}
