package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public class FeatureUsable extends JdbcQueryServlet<Boolean> {

	private static final long serialVersionUID = 201708251500006L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean usable = this.doProcess(request);
		this.doWrite(usable, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, id);
	}
	
	@Override
	protected Boolean doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		String feature = null;
		if (resultSet.next()) {
			feature = resultSet.getString("measurement");
			if (resultSet.wasNull()) 
				feature = null;
		}
		return feature == null;
	}
	
}