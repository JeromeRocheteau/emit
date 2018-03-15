package fr.icam.emit.services.callbacks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.MqttCallback;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.callbacks.CallbackFactory;
import fr.icam.emit.entities.Callback;
import fr.icam.emit.listeners.MqttClientListener;

public class Builder extends JdbcServlet {

	private static final long serialVersionUID = 201711141212014L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("uuid");
		String category = request.getParameter("category");
		if (category.equals("type")) {
			this.doCall(request, response, "type-callback-item");
		} else if (category.equals("topic")) {
			this.doCall(request, response, "topic-callback-item");
		} else if (category.equals("feature")) {
			this.doCall(request, response, "feature-callback-item");
		} else if (category.equals("storage")) {
			this.doCall(request, response, "storage-callback-item");
		} else {
			throw new ServletException("undefined callback category '" + category + "'");
		}
		try {
			Callback cb = (Callback) request.getAttribute("callback");
			MqttCallback callback = CallbackFactory.from(listener, id, cb);
			request.setAttribute("mqtt-callback", callback);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
