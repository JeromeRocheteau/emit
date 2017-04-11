package fr.icam.emit.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.entities.Experiment;
import fr.icam.emit.entities.Experiment_plan;

public class ExperimentLaunch extends JdbcServlet {


	private static final long serialVersionUID = 201704061657L;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "experiment-unprocessed");
		List<Experiment_plan> experiment = (List<Experiment_plan>) request.getAttribute("experiment_plan");
		this.doWrite(experiment, response.getWriter());
	}
	
}
