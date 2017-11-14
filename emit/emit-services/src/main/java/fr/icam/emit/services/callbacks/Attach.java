package fr.icam.emit.services.callbacks;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.MqttCallback;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Attach extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201711141212010L;
	
	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doAttach(request, response);
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}

	private void doAttach(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uuid = request.getParameter("uuid");
		this.doCall(request, response, "callback-build");
		MqttCallback callback = (MqttCallback) request.getAttribute("mqtt-callback");
		listener.doAttach(uuid, callback);
	}

	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String client = request.getParameter("uuid");
		Long callback = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, callback);
		statement.setString(2, client);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
