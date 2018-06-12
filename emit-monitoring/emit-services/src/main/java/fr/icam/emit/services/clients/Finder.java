package fr.icam.emit.services.clients;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Client;

public class Finder extends JdbcQueryServlet<Client> {

	private static final long serialVersionUID = 201710161616006L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Client client = this.doProcess(request);
		this.doWrite(client, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		String client = request.getParameter("client");
		statement.setString(1, user);
		statement.setString(2, client);
	}

    @Override
    protected Client doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	Client item = null;
    	if (resultSet.next()) {
    		String uuid = resultSet.getString("uuid");
    		String name = resultSet.getString("name");
    		String brokerUri = resultSet.getString("brokerUri");
    		String brokerName = resultSet.getString("brokerName");
    		String brokerUser = resultSet.getString("brokerUser");
    		String user = resultSet.getString("user");
    		Boolean open = resultSet.getBoolean("open");
    		Broker broker = new Broker(brokerUri, brokerName, brokerUser, null, null);
    		item = new Client(uuid, name, broker, user, open);
    	}
    	return item;
    }

}
