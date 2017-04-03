package fr.icam.emit.spy;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpyLaunchServlet extends SpyServlet {

	private static final long serialVersionUID = 2849318140512478886L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			String timeout = request.getParameter("timeout");
			Long to = (timeout == null || timeout.isEmpty()) ? 0L : Long.valueOf(timeout);
			String cmd = request.getParameter("command");
			String[] args = request.getParameterValues("arguments");
			meter.doLaunch(to, cmd, args);
			OutputStream out = response.getOutputStream();
			meter.doRetrieve(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
}
