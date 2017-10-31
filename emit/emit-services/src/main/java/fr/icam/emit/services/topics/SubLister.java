package fr.icam.emit.services.topics;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

public class SubLister extends Lister {

	private static final long serialVersionUID = 2017102310952009L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String topic = request.getParameter("topic");
		statement.setString(1, topic);
	}

}
