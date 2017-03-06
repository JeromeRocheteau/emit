package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcQueryServlet;

import fr.icam.emit.entities.Experiment;

public class ExperimentList  extends JdbcQueryServlet<List<Experiment>> { 
	
	private static final long serialVersionUID = 201703011635L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected List<Experiment> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Experiment> experiments = new LinkedList<Experiment>();
        while (resultSet.next()) {
        	int id = resultSet.getInt("id");        	
        	Timestamp started = resultSet.getTimestamp("started");
        	Timestamp stopped = resultSet.getTimestamp("stopped");
            String process = resultSet.getString("measurand");
            String uri = resultSet.getString("observee");
            experiments.add(new Experiment(id,started,stopped,process,uri));
        }
        return experiments;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Experiment> experiments = this.doProcess(request);
        this.doPrint(experiments, response);
	}	

}
