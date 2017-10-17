package fr.icam.emit.services.commons;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public abstract class UserLister<T> extends Lister<T> {

	private static final long serialVersionUID = 201708211614006L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		statement.setString(1, user);
	}
}
