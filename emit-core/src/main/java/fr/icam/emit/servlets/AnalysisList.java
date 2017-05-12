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

import fr.icam.emit.entities.Analysis;

public class AnalysisList extends JdbcQueryServlet<List<Analysis>> {

	private static final long serialVersionUID = 201705121110L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {

	}

	@Override
	protected List<Analysis> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Analysis> Analyses = new LinkedList<Analysis>();
		while (resultSet.next()) {
			String url = resultSet.getString("url");
			String name = resultSet.getString("name");
			Boolean deleted = resultSet.getBoolean("deleted");
			Analyses.add(new Analysis(url, name, deleted));
		}
		return Analyses;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Analysis> Analyses = this.doProcess(request);
		this.doWrite(Analyses, response.getWriter());
	}

}
