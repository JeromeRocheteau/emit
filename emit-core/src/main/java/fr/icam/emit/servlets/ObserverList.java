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

import fr.icam.emit.entities.Observer;

public class ObserverList extends JdbcQueryServlet<List<Observer>> {

	private static final long serialVersionUID = 201703011515L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
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
        this.doWrite(observers, response.getWriter());
	}

}