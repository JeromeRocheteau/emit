package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Feature;
import fr.icam.emit.entities.Instrument;
import fr.icam.emit.entities.Measure;

public class FeatureItem extends JdbcQueryServlet<Feature> {

	private static final long serialVersionUID = 201708251500000L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Feature feature = this.doProcess(request);
		request.setAttribute("feature", feature);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, id);
	}
	
	@Override
	protected Feature doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		Feature feature = null;
		if (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String name = resultSet.getString("name");
			Integer factor = resultSet.getInt("factor");

			Long measureId = resultSet.getLong("measureId");
			String measureName = resultSet.getString("measureName");
			String measureUnit = resultSet.getString("measureUnit");

			Long instrumentId = resultSet.getLong("instrumentId");
			String instrumentUri = resultSet.getString("instrumentUri");
			String instrumentName = resultSet.getString("instrumentName");
			
			Instrument instrument = new Instrument(instrumentId, null, instrumentUri, instrumentName); 
			Measure measure = new Measure(measureId, null, measureName, measureUnit);
			feature = new Feature(id, null, name, factor, measure, instrument);
		}
		return feature;
	}
	
}