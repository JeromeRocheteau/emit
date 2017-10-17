package fr.icam.emit.services.clients;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Connected extends JdbcQueryServlet<Boolean> {

	private static final long serialVersionUID = 201710161616010L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uuid = request.getParameter("client");
		Boolean connected = this.doProcess(request);
		if (connected) {
			try {
				if (listener.isConnected(uuid)) {
					this.doWrite(connected, response.getWriter());
				} else {
					throw new IllegalStateException(uuid);	
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} else {
			try {
				if (listener.isConnected(uuid)) {
					throw new IllegalStateException(uuid);	
				} else {
					this.doWrite(connected, response.getWriter());
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uuid = request.getParameter("client");
		Boolean connected = this.doProcess(request);
		if (connected) {
			try {
				if (!listener.isConnected(uuid)) {
					throw new IllegalStateException(uuid);	
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		request.setAttribute("connected", connected);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("client");
		statement.setString(1, uuid);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		int index = 0;
		while (resultSet.next()) {
			index++;
		}
		if (index > 1) {
			throw new IllegalStateException();
		}
		return index > 0;
	}

}
