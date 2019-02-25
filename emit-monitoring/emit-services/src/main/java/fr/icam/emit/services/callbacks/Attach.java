package fr.icam.emit.services.callbacks;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.MqttCallback;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.callbacks.CallbackFactory;
import fr.icam.emit.entities.Callback;
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
		try {
			this.doAttach(request, response);
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void doAttach(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uuid = request.getParameter("uuid");
		this.doCall(request, response, "callback-item");
		Callback cb = (Callback) request.getAttribute("abstract-callback");
		String category = cb.getCategory();
		if (category.equals("type")) {
			this.doCall(request, response, "type-callback-item");
		} else if (category.equals("topic")) {
			this.doCall(request, response, "topic-callback-item");
		} else if (category.equals("storage")) {
			this.doCall(request, response, "storage-callback-item");
		} else if (category.equals("feature")) {
			this.doCall(request, response, "feature-callback-item");
		} else if (category.equals("guard")) {
			this.doCall(request, response, "guard-callback-item");
		} else {
			throw new ServletException("undefined callback category '" + category + "'");
		}
		Callback c = (Callback) request.getAttribute("mqtt-callback");
		MqttCallback callback = CallbackFactory.from(listener, uuid, c);
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
