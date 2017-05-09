package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class FeatureDeleteOneInstrument extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 201705090822L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String instrument = request.getParameter("instrument");
        statement.setString(1,instrument);	
	}


	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}

}