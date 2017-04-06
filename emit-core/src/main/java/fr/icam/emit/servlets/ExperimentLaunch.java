package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.entities.Experiment;

public class ExperimentLaunch extends JdbcServlet {


	private static final long serialVersionUID = 201704061657L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "experiment-unprocessed");
		Experiment experiment = (Experiment) request.getAttribute("experiment");
		/* TODO */ 
		this.doWrite(experiment, response.getWriter());
	}
	
}
