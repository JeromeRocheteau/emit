package fr.icam.emit.servlets;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Measurand;

public class MeasurandPage extends Pager<Measurand> {

	private static final long serialVersionUID = 201708221826002L;

	@Override
	protected List<Measurand> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Measurand> measurands = new LinkedList<Measurand>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String name = resultSet.getString("name");
			Measurand measurand = new Measurand(id, name, null);
			measurands.add(measurand);
		}
		return measurands;
	}
	
}