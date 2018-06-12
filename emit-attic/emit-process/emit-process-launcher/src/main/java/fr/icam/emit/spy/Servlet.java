package fr.icam.emit.spy;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 2849318140512478885L;

	protected Meter meter;
	
	@Override
	public void init() throws ServletException {
		try {
			if (meter == null) {
				meter = new Meter();
				meter.setUp();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	@Override
	public void destroy() {
		try {
			meter.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		OutputStream out = response.getOutputStream();
		String cmd = request.getParameter("command");
		if (cmd == null) {
			meter.doInfo(out);				
		} else {
			try {
				meter.doCheck(cmd);
				out.write(Boolean.TRUE.toString().getBytes());
			} catch (Exception e) {
				out.write(Boolean.FALSE.toString().getBytes());
			}
		}
		out.flush();
	}
	
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
