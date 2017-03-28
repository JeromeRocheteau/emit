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

import fr.icam.emit.entities.Measure;

public class MeasureList extends JdbcQueryServlet<List<Measure>> {

	private static final long serialVersionUID = 201703011154L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {

	}

	@Override
	protected List<Measure> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Measure> measures = new LinkedList<Measure>();
		while (resultSet.next()) {
			String name = resultSet.getString("name");
			String unit = resultSet.getString("unit");
			Boolean deleted = resultSet.getBoolean("deleted");
			measures.add(new Measure(name, unit, deleted));
		}
		return measures;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Measure> measures = this.doProcess(request);
		this.doWrite(measures, response.getWriter());
	}

}