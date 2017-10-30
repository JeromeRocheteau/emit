package fr.icam.emit.services.shares;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class UserSizer extends fr.icam.emit.services.commons.UserSizer {

	private static final long serialVersionUID = 201710171230001L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		statement.setString(1, user);
		statement.setString(2, user);
	}

}
