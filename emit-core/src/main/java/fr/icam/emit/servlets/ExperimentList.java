package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Experiment;
import fr.icam.emit.entities.Measurand;

public class ExperimentList  extends JdbcQueryServlet<List<Experiment>> { 
	
	private static final long serialVersionUID = 201703011635L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {		 
	            String fkey = request.getParameter("fkey");
	            statement.setString(1,fkey);		
	}

	@Override
	protected List<Experiment> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Experiment> experiments = new LinkedList<Experiment>();
        while (resultSet.next()) {
        	int id = resultSet.getInt("id");        	
        	long started = (resultSet.getTimestamp("started")).getTime();
        	long stopped = (resultSet.getTimestamp("stopped")).getTime();
            String process = resultSet.getString("measurand");
            String environment = resultSet.getString("environment");
            Measurand measurand = new Measurand(process, null, null);
            experiments.add(new Experiment(id,started,stopped,measurand,environment));
        }
        return experiments;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Experiment> experiments = this.doProcess(request);
        this.doWrite(experiments, response.getWriter());
	}	

}
