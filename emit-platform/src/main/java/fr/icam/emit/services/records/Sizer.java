package fr.icam.emit.services.records;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sizer extends fr.icam.emit.services.commons.Sizer {

	private static final long serialVersionUID = 201710191845002L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer size = this.doProcess(request);
		Integer sum = (Integer) request.getAttribute("size");
		request.setAttribute("size", size + sum);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String client = request.getParameter("client");
		statement.setString(1, client);
	}

}
