package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.entities.Experiment;


public class ExperimentUpdate extends JdbcUpdateServlet<Boolean> {
	private static final long serialVersionUID = 201705050825L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Experiment experiment = (Experiment) request.getAttribute("experiment");
		
		statement.setLong(2, experiment.getId());		
		statement.setTimestamp(1, new Timestamp(experiment.getStopped()));
		
			/*
		statement.setLong(2, 10);		
		statement.setTimestamp(1, new Timestamp(500000));
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
