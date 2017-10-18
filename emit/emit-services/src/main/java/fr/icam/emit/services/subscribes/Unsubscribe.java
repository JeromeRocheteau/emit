package fr.icam.emit.services.subscribes;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Unsubscribe extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201710161616012L;

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
			this.doCall(request, response, "client-subscribing");
			Boolean subscribing = (Boolean) request.getAttribute("subscribing");
			if (subscribing) {
				listener.doUnsubscribe(uuid, topic);
				Boolean done = this.doProcess(request);
				this.doWrite(done, response.getWriter());
			} else {
				throw new IllegalStateException(uuid);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long subscribe = Long.valueOf(request.getParameter("subscribe"));
		statement.setLong(1, subscribe);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}

}
