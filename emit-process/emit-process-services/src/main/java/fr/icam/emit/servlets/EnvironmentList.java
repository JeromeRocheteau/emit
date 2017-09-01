package fr.icam.emit.servlets;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Environment;

public class EnvironmentList extends Lister<Environment> {

	private static final long serialVersionUID = 201708241533001L;

	@Override
	protected List<Environment> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Environment> environments = new LinkedList<Environment>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			String uri = resultSet.getString("uri");
			String name = resultSet.getString("name");
			String arch = resultSet.getString("arch");
			String os = resultSet.getString("os");
			String version = resultSet.getString("version");
			Environment environment = new Environment(id, uri, name, arch, os, version, null);
			environments.add(environment);
		}
		return environments;
	}
	
}