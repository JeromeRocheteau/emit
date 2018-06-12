package fr.icam.emit.servlets;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Instrument;

public class InstrumentList extends Lister<Instrument> {

	private static final long serialVersionUID = 201708241500001L;

	@Override
	protected List<Instrument> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Instrument> instruments = new LinkedList<Instrument>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String uri = resultSet.getString("uri");
			String name = resultSet.getString("name");
			Instrument instrument = new Instrument(id, null, uri, name);
			instruments.add(instrument);
		}
		return instruments;
	}
	
}