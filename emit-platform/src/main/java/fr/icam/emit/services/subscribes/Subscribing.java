package fr.icam.emit.services.subscribes;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Client;

public class Subscribing extends JdbcQueryServlet<List<fr.icam.emit.entities.Subscribe>> {

	private static final long serialVersionUID = 201710161616020L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String uuid = request.getParameter("client");
			List<fr.icam.emit.entities.Subscribe> subscribes = this.doProcess(request);
			if (subscribes.size() == 0) {
				this.doWrite(null, response.getWriter());
			} else if (subscribes.size() == 1) {
				fr.icam.emit.entities.Subscribe subscribe = subscribes.get(0);
				this.doWrite(subscribe, response.getWriter());
			} else {
				throw new IllegalStateException(uuid + ": " + subscribes.size());
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String uuid = request.getParameter("client");
			List<fr.icam.emit.entities.Subscribe> subscribes = this.doProcess(request);
			if (subscribes.size() == 0) {
				request.setAttribute("subscribing", Boolean.FALSE);	
			} else if (subscribes.size() == 1) {
				fr.icam.emit.entities.Subscribe subscribe = subscribes.get(0);
				request.setAttribute("subscribing", subscribe.getStopped() == null);
			} else {
				throw new IllegalStateException(uuid + ": " + subscribes.size());
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("client");
		statement.setString(1, uuid);
	}

	@Override
	protected List<fr.icam.emit.entities.Subscribe> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<fr.icam.emit.entities.Subscribe> items = new LinkedList<fr.icam.emit.entities.Subscribe>();
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
			  fr.icam.emit.entities.Subscribe item = new fr.icam.emit.entities.Subscribe(id, started.getTime(), stopped == null ? null : stopped.getTime(), user, topic, client);
			  items.add(item);
		}
		return items;
	}

}
