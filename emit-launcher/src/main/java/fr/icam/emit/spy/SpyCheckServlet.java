package fr.icam.emit.spy;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpyCheckServlet extends SpyServlet {

	private static final long serialVersionUID = 2849318140512478886L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			OutputStream out = response.getOutputStream();
			String cmd = request.getParameter("command");
			try {
				meter.doCheck(cmd);
				out.write(Boolean.TRUE.toString().getBytes());
			} catch (Exception e) {
				out.write(Boolean.FALSE.toString().getBytes());
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
}
