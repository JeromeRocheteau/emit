package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class ExperimentCheck extends JdbcServlet {

	private static final long serialVersionUID = 201709041420006L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getParameter("uri");
		String process = request.getParameter("process");
		String command = process.split("\\s+")[0];
		URL url = URI.create(uri + "?command=" + command).toURL();
		InputStream stream = url.openStream();
		InputStreamReader reader = new InputStreamReader(stream);
		Boolean checked = this.doRead(reader, Boolean.class);
		this.doWrite(checked, response.getWriter());
	}
	
}