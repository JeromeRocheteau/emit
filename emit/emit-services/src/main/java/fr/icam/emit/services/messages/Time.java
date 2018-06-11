package fr.icam.emit.services.messages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class Time extends JdbcServlet {

	private static final long serialVersionUID = 201805151230001L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long timestamp = System.currentTimeMillis();
		this.doWrite(timestamp, response.getWriter());
	}

}
