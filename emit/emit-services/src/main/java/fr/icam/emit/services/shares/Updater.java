package fr.icam.emit.services.shares;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Updater extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201710171230004L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* Integer count = */ this.doProcess(request);
		// this.doWrite(count, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String principal = request.getUserPrincipal().getName();
		String client = (String) request.getAttribute("client");
		if (client == null) {
			client = request.getParameter("client");
		}
		String user = (String) request.getAttribute("user");
		if (user == null) {
			user = request.getParameter("user");
			if (user == null) {
				user = request.getUserPrincipal().getName();
			}
		}
		statement.setString(2, client);
		statement.setString(3, user);
		Boolean control = (Boolean) request.getAttribute("control");
		if (control == null) {
			String ctl = request.getParameter("control");
			if (ctl == null) {
				if (principal.equals(user)) {
					throw new Exception(principal + " == " + user);
				} else {
					statement.setNull(1, Types.TINYINT);
				}
			} else {
				control = Boolean.valueOf(ctl);
				if (control) {
					statement.setBoolean(1, control);
				} else {
					if (principal.equals(user)) {
						throw new Exception(principal + " == " + user);
					} else {
						statement.setBoolean(1, control);
					}
				}
			}
		} else {
			statement.setBoolean(1, control);
		}
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
