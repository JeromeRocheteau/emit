package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcQueryServlet;

import fr.icam.emit.entities.Observee;

public class ObserveePage extends JdbcQueryServlet<List<Observee>> {

	private static final long serialVersionUID = 201703171631L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		int offset = Integer.parseInt(request.getParameter("offset"));
		statement.setInt(1, offset);
	}

	@Override
	protected List<Observee> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Observee> observees = new LinkedList<Observee>();
        while (resultSet.next()) {
            String uri = resultSet.getString("uri");
            String name = resultSet.getString("name");
            observees.add(new Observee(uri, name));
        }
        return observees;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Observee> observees = this.doProcess(request);
        this.doPrint(observees, response);
	}
}
