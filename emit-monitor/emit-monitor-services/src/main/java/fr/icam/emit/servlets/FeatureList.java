package fr.icam.emit.servlets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Feature;
import fr.icam.emit.entities.Instrument;
import fr.icam.emit.entities.Measure;

public class FeatureList extends Lister<Feature> {

	private static final long serialVersionUID = 201708251500002L;

	@Override
	protected List<Feature> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Feature> features = new LinkedList<Feature>();
		while (resultSet.next()) {
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
			Feature feature = new Feature(id, null, name, factor, measure, instrument);
			features.add(feature);
		}
		return features;
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long instrument = Long.valueOf(request.getParameter("instrument"));
		statement.setLong(1, instrument);
	}
	
}