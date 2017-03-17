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

import fr.icam.emit.entities.Observer;

public class ObserverPage extends JdbcQueryServlet<List<Observer>> {

	private static final long serialVersionUID = 201703171417L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		int offset = Integer.parseInt(request.getParameter("offset"));
		statement.setInt(1, offset); // 1 = premier "?", offset = la valeur que prend le "?"
	}

	@Override
	protected List<Observer> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Observer> observers = new LinkedList<Observer>();
        while (resultSet.next()) {
            String uri = resultSet.getString("uri");
            String name = resultSet.getString("name");
            observers.add(new Observer(uri, name));
        }
        return observers;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Observer> observers = this.doProcess(request);
        this.doPrint(observers, response);
	}
}
