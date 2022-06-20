package fr.icam.emit.services.clients;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Deleter extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201710161616005L;

	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			this.doDelete(request);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}

	private void doDelete(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		listener.doDelete(uuid);
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		statement.setString(1, uuid);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
