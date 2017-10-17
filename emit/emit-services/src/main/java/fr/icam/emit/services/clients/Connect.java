package fr.icam.emit.services.clients;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Connect extends JdbcUpdateServlet<Boolean> {

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
			this.doCall(request, response, "client-connected");
			Boolean connected = (Boolean) request.getAttribute("connected");
			if (connected) {
				listener.doConnect(uuid);
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
		String user = request.getUserPrincipal().getName();
		String client = request.getParameter("client");
		statement.setString(1, client);
		statement.setString(2, user);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count > 0;
	}

}
