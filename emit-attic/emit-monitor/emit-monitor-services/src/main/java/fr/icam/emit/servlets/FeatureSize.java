package fr.icam.emit.servlets;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;


public class FeatureSize extends Sizer {

	private static final long serialVersionUID = 201708251500000L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long instrument = Long.valueOf(request.getParameter("instrument"));
		statement.setLong(1, instrument);
	}
	
}