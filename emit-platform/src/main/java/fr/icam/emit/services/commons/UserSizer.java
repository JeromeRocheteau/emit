package fr.icam.emit.services.commons;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class UserSizer extends Sizer {

	private static final long serialVersionUID = 201708211614005L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		statement.setString(1, user);
	}

}
