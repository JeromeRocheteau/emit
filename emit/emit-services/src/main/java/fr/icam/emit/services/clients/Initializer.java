package fr.icam.emit.services.clients;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Client;
import fr.icam.emit.listeners.MqttClientListener;

public class Initializer extends fr.icam.emit.services.commons.Lister<Client> {

	private static final long serialVersionUID = 201710181414001L;


	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
		try {
			List<Client> clients = this.doProcess(null);
			for (Client client : clients) {
				listener.doCreate(client.getBroker().getUri(), client.getUuid());
				System.out.println("[EMIT] Create Client " + client.getUuid() + " on " +  client.getBroker());
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
    @Override
    protected List<Client> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Client> items = new LinkedList<Client>();
    	while (resultSet.next()) {
    		String uuid = resultSet.getString("uuid");
    		String name = resultSet.getString("name");
    		String brokerUri = resultSet.getString("brokerUri");
    		String brokerName = resultSet.getString("brokerName");
    		String brokerUser = resultSet.getString("brokerUser");
    		String user = resultSet.getString("user");
    		Boolean open = resultSet.getBoolean("open");
    		Broker broker = new Broker(brokerUri, brokerName, brokerUser, null, null);
    		Client item = new Client(uuid, name, broker, user, open);
    		items.add(item);
    	}
    	return items;
    }

}
