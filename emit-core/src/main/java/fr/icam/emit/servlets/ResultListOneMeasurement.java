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

import fr.icam.emit.entities.Result;

public class ResultListOneMeasurement extends JdbcQueryServlet<List<Result>> {

	private static final long serialVersionUID = 201705090805L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String instrument = request.getParameter("instrument");
        statement.setString(1,instrument);	
	}

	@Override
	protected List<Result> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Result> results = new LinkedList<Result>();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String analysis = resultSet.getString("analysis");
			Long measurement = resultSet.getLong("measurement");
			String measure = resultSet.getString("measure");
			Double value = resultSet.getDouble("value");
			results.add(new Result(id, analysis, measurement, measure,value));
		}
		return results;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Result> results = this.doProcess(request);
		this.doWrite(results, response.getWriter());
	}

}