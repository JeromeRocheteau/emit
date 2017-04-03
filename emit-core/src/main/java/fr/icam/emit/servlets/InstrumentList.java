package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Instrument;

public class InstrumentList extends JdbcQueryServlet<List<Instrument>> {

	private static final long serialVersionUID = 201703011515L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {

	}

	@Override
	protected List<Instrument> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Instrument> instruments = new LinkedList<Instrument>();
		while (resultSet.next()) {
			String uri = resultSet.getString("uri");
			String name = resultSet.getString("name");
			Boolean deleted = resultSet.getBoolean("deleted");
			instruments.add(new Instrument(uri, name, deleted));
		}
		return instruments;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Instrument> instruments = this.doProcess(request);
		this.doWrite(instruments, response.getWriter());
	}

}