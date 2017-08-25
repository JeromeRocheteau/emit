package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.entities.Environment;

public class EnvironmentValidate extends JdbcServlet {

	private static final long serialVersionUID = 201708241533005L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		URI uri = URI.create(request.getParameter("uri"));
		InputStream stream = uri.toURL().openStream();
		InputStreamReader reader = new InputStreamReader(stream);
		Environment env = this.doRead(reader, Environment.class);
		request.setAttribute("arch", env.getArch());
		request.setAttribute("os", env.getOs());
		request.setAttribute("version", env.getVersion());
		this.doCall(request, response, "environment-update");
	}
	
}