package fr.icam.emit.services.connects;

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

import fr.icam.emit.entities.Client;
import fr.icam.emit.listeners.MqttClientListener;

public class Connected extends JdbcQueryServlet<List<fr.icam.emit.entities.Connect>> {

	private static final long serialVersionUID = 201710161616010L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String uuid = request.getParameter("client");
			List<fr.icam.emit.entities.Connect> connects = this.doProcess(request);
			if (connects.size() == 0) {
				this.doWrite(null, response.getWriter());
			} else if (connects.size() == 1) {
				fr.icam.emit.entities.Connect connect = connects.get(0);
				if (listener.isConnected(uuid)) {
					this.doWrite(connect, response.getWriter());
				} else {
					throw new IllegalStateException(uuid);	
				}
			} else {
				throw new IllegalStateException(uuid + ": " + connects.size());
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String uuid = request.getParameter("client");
			List<fr.icam.emit.entities.Connect> connects = this.doProcess(request);
			if (connects.size() == 0) {
				request.setAttribute("connected", Boolean.FALSE);	
			} else if (connects.size() == 1) {
				fr.icam.emit.entities.Connect connect = connects.get(0);
				if (listener.isConnected(uuid)) {
					request.setAttribute("connected", connect.getStopped() == null);
				} else {
					throw new IllegalStateException(uuid);
				}
			} else {
				throw new IllegalStateException(uuid + ": " + connects.size());
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
	protected List<fr.icam.emit.entities.Connect> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<fr.icam.emit.entities.Connect> items = new LinkedList<fr.icam.emit.entities.Connect>();
		while (resultSet.next()) {
			  Long id = resultSet.getLong("id");
			  Timestamp started = resultSet.getTimestamp("started");
			  Timestamp stopped = resultSet.getTimestamp("stopped");
			  if (resultSet.wasNull()) {
				  stopped = null;
			  }
			  String user = resultSet.getString("user");
			  String clientUuid = resultSet.getString("clientUuid");
			  String clientBroker = resultSet.getString("clientBroker");
			  String clientUser = resultSet.getString("clientUser");
			  Client client = new Client(clientUuid, clientBroker, clientUser);
			  fr.icam.emit.entities.Connect item = new fr.icam.emit.entities.Connect(id, started.getTime(), stopped == null ? null : stopped.getTime(), user, client);
			  items.add(item);
		}
		return items;
	}

}
