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

public class ObserveeList extends JdbcQueryServlet<List<Observee>>{
	private static final long serialVersionUID = 201703011440L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected List<Observee> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Observee> Observees = new LinkedList<Observee>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String uri = resultSet.getString("uri");
            Observees.add(new Observee(name, uri));
        }
        return Observees;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Observee> Observees = this.doProcess(request);
        this.doPrint(Observees, response);
	}

}
