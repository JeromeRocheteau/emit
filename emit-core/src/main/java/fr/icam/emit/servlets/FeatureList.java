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

import fr.icam.emit.entities.Feature;

public class FeatureList extends JdbcQueryServlet<List<Feature>> {

	private static final long serialVersionUID = 201704261450L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {

	}

	@Override
	protected List<Feature> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Feature> features = new LinkedList<Feature>();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String measure = resultSet.getString("measure");
			String instrument = resultSet.getString("instrument");
			long order = resultSet.getLong("order");
			features.add(new Feature(id, measure, instrument, order));
		}
		return features;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Feature> features = this.doProcess(request);
		this.doWrite(features, response.getWriter());
	}

}