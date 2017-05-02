package fr.icam.emit.servlets;

import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletRequest;

public class MeasurePage extends MeasureList {

	private static final long serialVersionUID = 201703171417L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		int offset = Integer.parseInt(request.getParameter("offset"));
		statement.setInt(1, offset);
	}
	
}
