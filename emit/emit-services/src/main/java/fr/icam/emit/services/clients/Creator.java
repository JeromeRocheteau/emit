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
		}
		this.doWrite(count, response.getWriter());
	}

	private void doCreate(HttpServletRequest request) throws Exception {
		String uuid = UUID.randomUUID().toString();
		String broker = URI.create(request.getParameter("broker")).toString();
		request.setAttribute("uuid", uuid);
		request.setAttribute("broker", broker);
		listener.doCreate(uuid, broker);
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		request.setAttribute("user", user);
		String uuid = (String) request.getAttribute("uuid");
		request.setAttribute("client", uuid);
		request.setAttribute("control", Boolean.TRUE);
		String broker = (String) request.getAttribute("broker");
		statement.setString(1, uuid);
		statement.setString(2, broker);
		statement.setString(3, user);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
