package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class ExperimentLaunch extends JdbcServlet {

	private static final long serialVersionUID = 201709041420007L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getParameter("uri");
		String process = request.getParameter("process");
		String content = this.getRequestContent(process);
		URL url = URI.create(uri).toURL();
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(content.getBytes());
		outputStream.flush();
		InputStream inputStream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		Long duration = this.doRead(reader, Long.class);
		this.doWrite(duration, response.getWriter());
	}

	private String getRequestContent(String process) {
		String[] items = process.split("\\s+");
		String command = items[0];
		String[] parameters = this.getParameters(items);
		StringBuffer buffer = new StringBuffer(1024);
		buffer.append("command=");
		buffer.append(command);
		for (String parameter : parameters) {
			buffer.append('&');
			buffer.append("arguments=");
			buffer.append(parameter);
		}
		return buffer.toString();
	}

	private String[] getParameters(String[] parameters) {
		return Arrays.copyOfRange(parameters, 1, parameters.length);
	}
	
}