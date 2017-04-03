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

import fr.icam.emit.entities.Measurand;

public class MeasurandList extends JdbcQueryServlet<List<Measurand>> {

	private static final long serialVersionUID = 201703011638L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {

	}

	@Override
	protected List<Measurand> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Measurand> measurands = new LinkedList<Measurand>();
		while (resultSet.next()) {
			String process = resultSet.getString("process");
			String name = resultSet.getString("name");
			Boolean deleted = resultSet.getBoolean("deleted");
			measurands.add(new Measurand(process, name, deleted));
		}
		return measurands;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Measurand> measurands = this.doProcess(request);
		this.doWrite(measurands, response.getWriter());
	}

}
