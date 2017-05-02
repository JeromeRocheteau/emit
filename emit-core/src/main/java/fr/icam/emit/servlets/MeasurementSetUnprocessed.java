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

import fr.icam.emit.entities.Feature;
import fr.icam.emit.entities.Instrument;
import fr.icam.emit.entities.MeasurementSet_plan;

public class MeasurementSetUnprocessed  extends JdbcQueryServlet<List<MeasurementSet_plan>> {
	
	private static final long serialVersionUID = 201705020946L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<MeasurementSet_plan> measurementSet_plan = this.doProcess(request);
		request.setAttribute("measurementSet_plan", measurementSet_plan);
	}
	
	protected List<MeasurementSet_plan> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<MeasurementSet_plan> measurementSet_plan = new LinkedList<MeasurementSet_plan>();
		while (resultSet.next()) {
			long id =  resultSet.getLong("id");
			String data = resultSet.getString("data");
			Timestamp achieved = resultSet.getTimestamp("achieved");
			if (resultSet.wasNull()) {
				achieved = null;
			}
			long experiment = resultSet.getLong("experiment");
			
			String name = resultSet.getString("name");
			String uri = resultSet.getString("uri");
			Instrument instrument = new Instrument(uri,name,false);
			long id_feature =  resultSet.getLong("id_feature");
			String measure = resultSet.getString("measure");
			String unit = resultSet.getString("unit");
			Feature feature = new Feature(id_feature,measure,uri,unit);
			long time = 0;
			measurementSet_plan.add(new MeasurementSet_plan(id,data,achieved == null ? null : achieved.getTime(),experiment,instrument,feature));
			/*achieved == null ? null : achieved.getTime()*/			
			
		}		
		return measurementSet_plan;
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}
	

}
