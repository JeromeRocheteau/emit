package fr.icam.emit.services.records;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class Sumer extends JdbcServlet {

	private static final long serialVersionUID = 201710191845003L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("size", new Integer(0));
		this.doCall(request, response, "connect-size");
		this.doCall(request, response, "subscribe-size");
		this.doCall(request, response, "publish-size");
		Integer size = (Integer) request.getAttribute("size");
		this.doWrite(size, response.getWriter());
	}

}
