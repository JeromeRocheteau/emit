package fr.icam.emit.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class MonitorStart extends JdbcServlet {

	private static final long serialVersionUID = 201709041223001L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "feature-start");
		this.doCall(request, response, "measurement-create");
		this.doCall(request, response, "feature-enable");
	}
		
}