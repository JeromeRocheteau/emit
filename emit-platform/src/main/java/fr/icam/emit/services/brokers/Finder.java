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

	private static final long serialVersionUID = 202206201030001L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Broker broker = this.doProcess(request);
		this.doWrite(broker, response.getWriter());
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		String uri = request.getParameter("uri");
		statement.setString(1, user);
		statement.setString(2, uri);
	}

    @Override
    protected Broker doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	Broker item = null;
    	if (resultSet.next()) {
    		String uri = resultSet.getString("uri");
    		String name = resultSet.getString("name");
    		String user = resultSet.getString("user");
    		String username = resultSet.getString("username");
    		String password = resultSet.getString("password");
    		item = new Broker(uri, name, user, username, password);
    	}
    	return item;
    }

}
