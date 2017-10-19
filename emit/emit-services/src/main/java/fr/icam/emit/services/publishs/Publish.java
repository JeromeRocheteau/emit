package fr.icam.emit.services.publishs;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Publish extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201710161616011L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String uuid = request.getParameter("client");
			String topic = request.getParameter("topic");
			Integer qos = Integer.valueOf(request.getParameter("qos"));
			if (qos < 0) {
				throw new IllegalArgumentException(uuid + " qos: " + qos);
			} else if (qos > 2) {
				throw new IllegalArgumentException(uuid + " qos: " + qos);
			}
			Boolean retained = Boolean.valueOf(request.getParameter("retained"));
			byte[] payload = request.getParameter("payload").getBytes();
			listener.doPublish(uuid, topic, qos.intValue(), retained.booleanValue(), payload);
			Boolean done = this.doProcess(request);
			this.doWrite(done, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String client = request.getParameter("client");
		String user = request.getUserPrincipal().getName();
		String topic = request.getParameter("topic");
		statement.setString(1, client);
		statement.setString(2, user);
		statement.setString(3, topic);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}

}
