package fr.icam.emit.services.clients;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Disconnect extends JdbcUpdateServlet<Boolean> {

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
				throw new IllegalStateException(uuid);
			} else {
				listener.doDisconnect(uuid);
				Boolean done = this.doProcess(request);
				this.doWrite(done, response.getWriter());
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String client = request.getParameter("client");
		statement.setString(1, client);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}

}
