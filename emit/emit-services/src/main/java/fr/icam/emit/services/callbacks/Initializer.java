package fr.icam.emit.services.callbacks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.paho.client.mqttv3.MqttCallback;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.callbacks.CallbackFactory;
import fr.icam.emit.entities.Callback;
import fr.icam.emit.listeners.MqttClientListener;

public class Initializer extends JdbcQueryServlet<Map<String, Callback>> {

	private static final long serialVersionUID = 201711141212000L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
		try {
			Map<String, Callback> callbacks = this.doProcess(null);
			for (String id : callbacks.keySet()) {
				Callback cb = callbacks.get(id);
				MqttCallback callback = CallbackFactory.from(listener, id, cb);
				listener.doAttach(id, callback);
				System.out.println("[EMIT] Attach Callback " + cb.getId() + " to Client " + id);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception { }
	
    @Override
    protected Map<String, Callback> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	Map<String, Callback> items = new HashMap<String, Callback>();
    	while (resultSet.next()) {
    		String uuid = resultSet.getString("uuid");
    		Long id = resultSet.getLong("id");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		Callback item = new Callback(id, issued.getTime(), user, atomic, category);
    		items.put(uuid, item);
    	}
    	return items;
    }

}
