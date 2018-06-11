package fr.icam.emit.services.shares;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class UserPager extends Pager {

	private static final long serialVersionUID = 201710171230007L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		Integer offset = Integer.valueOf(request.getParameter("offset"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		statement.setString(1, user);
		statement.setString(2, user);
		statement.setInt(3, offset);
		statement.setInt(4, length);
	}

}
