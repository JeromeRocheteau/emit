package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Feature;

public class FeatureListOneInstrument extends JdbcQueryServlet<List<Feature>> {

	private static final long serialVersionUID = 201705090805L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String instrument = request.getParameter("instrument");
        statement.setString(1,instrument);	
	}

	@Override
	protected List<Feature> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Feature> features = new LinkedList<Feature>();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String measure = resultSet.getString("measure");
			String instrument = resultSet.getString("instrument");
			long order = resultSet.getLong("no_order");
			String name = resultSet.getString("name");
			int factor = resultSet.getInt("factor");
			features.add(new Feature(id, measure, instrument, order,name,factor));
		}
		return features;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Feature> features = this.doProcess(request);
		this.doWrite(features, response.getWriter());
	}

}