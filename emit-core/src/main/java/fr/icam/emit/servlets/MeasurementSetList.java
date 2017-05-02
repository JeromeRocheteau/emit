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

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.MeasurementSet;


public class MeasurementSetList extends JdbcQueryServlet<List<MeasurementSet>> {
	private static final long serialVersionUID = 201704011619L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		  String fkey = request.getParameter("fkey");
          statement.setString(1,fkey);
	}

	@Override
	protected List<MeasurementSet> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<MeasurementSet> MeasurementSets = new LinkedList<MeasurementSet>();
        while (resultSet.next()) {
        	long id = resultSet.getInt("id");
        	String data = resultSet.getString("data");
        	Timestamp timestamp = resultSet.getTimestamp("achieved");
        	long achieved = 0;
        	if (timestamp != null){
        		achieved = timestamp.getTime();
        	}        	
        	String instrument = resultSet.getString("instrument");
        	long experiment =resultSet.getInt("experiment");        	
            MeasurementSets.add(new MeasurementSet(id, data,achieved,instrument,experiment));
        }
        return MeasurementSets;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<MeasurementSet> MeasurementSets = this.doProcess(request);
        this.doWrite(MeasurementSets, response.getWriter());
	}
}
