package fr.icam.emit.services.clients;

import java.io.IOException;
import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Creator extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201710161616003L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			this.doCreate(request);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		Integer count = this.doProcess(request);
		if (count > 0) {
			this.doCall(request, response, "share-create");
			this.doCall(request, response, "share-update");
		}
		String uuid = (String) request.getAttribute("client");
		this.doWrite(uuid, response.getWriter());
	}

	private void doCreate(HttpServletRequest request) throws Exception {
		String client = this.getClientUUID(request);
		String broker = URI.create(request.getParameter("broker")).toString();
		request.setAttribute("client", client);
		request.setAttribute("broker", broker);
		listener.doCreate(broker, client);
	}

	private String getClientUUID(HttpServletRequest request) {
		String uuid = request.getParameter("uuid");
		if (uuid == null || uuid.isEmpty()) {
			return UUID.randomUUID().toString();
		} else {
			return UUID.fromString(uuid).toString();	
		}
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		request.setAttribute("user", user);
		String client = (String) request.getAttribute("client");
		request.setAttribute("control", Boolean.TRUE);
		String name = request.getParameter("name");
		String broker = (String) request.getAttribute("broker");
		Boolean open = Boolean.valueOf(request.getParameter("open"));
		statement.setString(1, client);
		statement.setString(2, name);
		statement.setString(3, broker);
		statement.setString(4, user);
		statement.setBoolean(5, open);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
