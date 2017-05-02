package fr.icam.emit.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.entities.MeasurementSet_plan;

public class MeasurementSetLaunch extends JdbcServlet {
	
	private static final long serialVersionUID = 201705021156L;


	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "measurementSet-unprocessed");
		List<MeasurementSet_plan> measurementSet = (List<MeasurementSet_plan>) request.getAttribute("measurementSet_plan");
		this.doWrite(measurementSet, response.getWriter());
	}
	
}
