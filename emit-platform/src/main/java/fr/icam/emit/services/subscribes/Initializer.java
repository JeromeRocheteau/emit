package fr.icam.emit.services.subscribes;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Client;
import fr.icam.emit.entities.Subscribe;
import fr.icam.emit.listeners.MqttClientListener;

public class Initializer extends fr.icam.emit.services.commons.Lister<Subscribe> {

	private static final long serialVersionUID = 201710181414003L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
		try {
			List<Subscribe> subscribes = this.doProcess(null);
			for (Subscribe subscribe : subscribes) {
				if (subscribe.getStopped() == null) {
					listener.doSubscribe(subscribe.getClient().getUuid(), subscribe.getTopic());
					System.out.println("[EMIT] Subscribe Client " + subscribe.getClient().getUuid() + " to " + subscribe.getTopic());
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
    @Override
    protected List<Subscribe> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Subscribe> items = new LinkedList<Subscribe>();
    	while (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		Timestamp started = resultSet.getTimestamp("started");
    		Timestamp stopped = resultSet.getTimestamp("stopped");
    		if (resultSet.wasNull()) {
    			stopped = null;
    		}
    		String user = resultSet.getString("user");
    		String topic = resultSet.getString("topic");
    		String clientUuid = resultSet.getString("clientUuid");
    		String clientName = resultSet.getString("clientName");
    		String clientUser = resultSet.getString("clientUser");
    		Boolean clientOpen = resultSet.getBoolean("clientOpen");
    		String brokerUri = resultSet.getString("brokerUri");
    		String brokerName = resultSet.getString("brokername");
    		String brokerUser = resultSet.getString("brokerUser");
    		Broker broker = new Broker(brokerUri, brokerName, brokerUser, null, null);
    		Client client = new Client(clientUuid, clientName, broker, clientUser, clientOpen);
    		Subscribe item = new Subscribe(id, started.getTime(), stopped == null ? null : stopped.getTime(), user, topic, client);
    		items.add(item);
    	}
    	return items;
    }

}
