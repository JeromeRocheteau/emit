package fr.icam.emit.services.callbacks;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.listeners.MqttClientListener;

public class Detach extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201711141212011L;
	
	private MqttClientListener listener;
	
	@Override
	public void init() throws ServletException {
		super.init();
		listener = (MqttClientListener) this.getServletContext().getAttribute("mqtt-client-listener");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doDetach(request);
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}

	private void doDetach(HttpServletRequest request) {
		String id = request.getParameter("uuid");
		listener.doDetach(id);
	}

	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String client = request.getParameter("uuid");
		statement.setString(1, client);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
