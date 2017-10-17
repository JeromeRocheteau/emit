package fr.icam.emit.services.commons;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public abstract class UserPager<T> extends Pager<T> {

	private static final long serialVersionUID = 201708211614007L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		Integer offset = Integer.valueOf(request.getParameter("offset"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		statement.setString(1, user);
		statement.setInt(2, offset);
		statement.setInt(3, length);
	}

}
