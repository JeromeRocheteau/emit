package fr.icam.emit.services.shares;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Creator extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201710171230003L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String client = (String) request.getAttribute("client");
		if (client == null) {
			client = request.getParameter("client");
		}
		String user = (String) request.getAttribute("user");
		if (user == null) {
			user = request.getParameter("user");
		}
		Boolean control = (Boolean) request.getAttribute("control");
		if (control == null) {
			control = Boolean.valueOf(request.getParameter("control"));
		}
		statement.setString(1, client);
		statement.setString(2, user);
		statement.setBoolean(3, control);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
