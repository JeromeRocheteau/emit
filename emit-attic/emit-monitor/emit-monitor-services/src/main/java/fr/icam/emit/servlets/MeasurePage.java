package fr.icam.emit.servlets;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Measure;

public class MeasurePage extends Pager<Measure> {

	private static final long serialVersionUID = 201708241432002L;

	@Override
	protected List<Measure> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Measure> measures = new LinkedList<Measure>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String name = resultSet.getString("name");
			String unit = resultSet.getString("unit");
			Measure measure = new Measure(id, null, name, unit);
			measures.add(measure);
		}
		return measures;
	}
	
}