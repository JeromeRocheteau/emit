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

import fr.icam.emit.entities.Environment;

public class EnvironmentList extends JdbcQueryServlet<List<Environment>> {

	private static final long serialVersionUID = 201703011440L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {

	}

	@Override
	protected List<Environment> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Environment> Environments = new LinkedList<Environment>();
		while (resultSet.next()) {
			String name = resultSet.getString("name");
			String uri = resultSet.getString("uri");
			Boolean deleted = resultSet.getBoolean("deleted");
			Environments.add(new Environment(name, uri, deleted));
		}
		return Environments;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Environment> Environments = this.doProcess(request);
		this.doWrite(Environments, response.getWriter());
	}

}
