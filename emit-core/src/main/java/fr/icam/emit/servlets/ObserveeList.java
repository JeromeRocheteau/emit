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

public class ObserveeList extends JdbcQueryServlet<List<Environment>>{
	private static final long serialVersionUID = 201703011440L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected List<Environment> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Environment> Observees = new LinkedList<Environment>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String uri = resultSet.getString("uri");
            Observees.add(new Environment(name, uri));
        }
        return Observees;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Environment> Observees = this.doProcess(request);
        this.doWrite(Observees, response.getWriter());
	}

}
