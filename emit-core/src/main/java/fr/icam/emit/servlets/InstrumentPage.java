package fr.icam.emit.servlets;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Instrument;

public class InstrumentPage extends Pager<Instrument> {

	private static final long serialVersionUID = 201708241500002L;

	@Override
	protected List<Instrument> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Instrument> instruments = new LinkedList<Instrument>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String uri = resultSet.getString("uri");
			String name = resultSet.getString("name");
			Instrument instrument = new Instrument(id, uri, name, null);
			instruments.add(instrument);
		}
		return instruments;
	}
	
}