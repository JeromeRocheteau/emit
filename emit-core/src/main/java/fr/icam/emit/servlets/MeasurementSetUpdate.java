package fr.icam.emit.servlets;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.entities.MeasurementSet;


public class MeasurementSetUpdate extends JdbcUpdateServlet<Boolean>{	
	private static final long serialVersionUID = 201704241124L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		MeasurementSet measurementSet = (MeasurementSet) request.getAttribute("measurementSet");
		
		statement.setLong(2, measurementSet.getId());
		statement.setString(1, measurementSet.getData());
		
		/*
		statement.setLong(2, 22);
		statement.setString(1, "bonjour");
		*/
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
}
