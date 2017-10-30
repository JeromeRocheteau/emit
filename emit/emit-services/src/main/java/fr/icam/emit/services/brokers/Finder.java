package fr.icam.emit.services.brokers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Broker;

public class Finder extends JdbcQueryServlet<Broker> {

	private static final long serialVersionUID = 201710301015006L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Broker broker = this.doProcess(request);
		request.setAttribute("broker", broker);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		String client = request.getParameter("client");
		statement.setString(1, user);
		statement.setString(2, client);
	}

    @Override
    protected Broker doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	Broker item = null;
    	if (resultSet.next()) {
    		String uri = resultSet.getString("uri");
    		String user = resultSet.getString("user");
    		String username = resultSet.getString("username");
    		String password = resultSet.getString("password");
    		item = new Broker(uri, user, username, password);
    	}
    	return item;
    }

}
