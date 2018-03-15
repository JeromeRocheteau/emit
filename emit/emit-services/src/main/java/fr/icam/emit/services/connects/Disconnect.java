package fr.icam.emit.services.connects;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Disconnect extends JdbcUpdateServlet<Integer> {

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
			this.doCall(request, response, "client-connected");
			Boolean connected = (Boolean) request.getAttribute("connected");
			if (connected) {
				listener.doDisconnect(uuid);
				Integer count = this.doProcess(request);
				this.doWrite(count, response.getWriter());
			} else {
				throw new IllegalStateException("state conflict for '" + uuid +"' MQTT client");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long connect = Long.valueOf(request.getParameter("connect"));
		statement.setLong(1, connect);
	}

	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
