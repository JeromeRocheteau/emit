package fr.icam.emit.services.connects;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Client;
import fr.icam.emit.entities.Connect;
import fr.icam.emit.listeners.MqttClientListener;

public class Initializer extends fr.icam.emit.services.commons.Lister<Connect> {

	private static final long serialVersionUID = 201710181414002L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
		try {
			List<Connect> connects = this.doProcess(null);
			for (Connect connect : connects) {
				System.out.println("[EMIT] Process Client " + connect.getClient().getUuid());
				if (connect.getStopped() == null) {
					boolean auth = connect.getPassword() != null;
					listener.doConnect(connect.getClient().getUuid(), auth ? connect.getUsername() : null, auth ? connect.getPassword() : null);
					System.out.println("[EMIT] Connect Client " + connect.getClient().getUuid() + " " + (auth ? connect.getUsername() : "") + (auth ? connect.getPassword() : ""));	
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
    @Override
    protected List<Connect> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Connect> items = new LinkedList<Connect>();
    	while (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		Timestamp started = resultSet.getTimestamp("started");
    		Timestamp stopped = resultSet.getTimestamp("stopped");
    		if (resultSet.wasNull()) {
    			stopped = null;
    		}
    		String user = resultSet.getString("user");
    		String username = resultSet.getString("username");
    		if (resultSet.wasNull()) {
    			username = null;
    		}
    		String password = resultSet.getString("password");
    		if (resultSet.wasNull()) {
    			password = null;
    		}
    		String clientUuid = resultSet.getString("clientUuid");
    		String clientBroker = resultSet.getString("clientBroker");
    		String clientUser = resultSet.getString("clientUser");
    		Client client = new Client(clientUuid, clientBroker, clientUser);
    		Connect item = new Connect(id, started.getTime(), stopped == null ? null : stopped.getTime(), user, username, password, client);
    		items.add(item);
    	}
    	return items;
    }

}
