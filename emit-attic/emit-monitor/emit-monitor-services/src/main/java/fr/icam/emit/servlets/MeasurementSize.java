package fr.icam.emit.servlets;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;


public class MeasurementSize extends Sizer {

	private static final long serialVersionUID = 201709041107000L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long feature = Long.valueOf(request.getParameter("feature"));
		statement.setLong(1, feature);
	}
	
}