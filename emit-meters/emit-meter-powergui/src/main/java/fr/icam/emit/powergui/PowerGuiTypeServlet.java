package fr.icam.emit.powergui;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PowerGuiTypeServlet extends PowerGuiServlet {

	private static final long serialVersionUID = 20160407002L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			OutputStream out = response.getOutputStream();
			out.write(Boolean.TRUE.toString().getBytes());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
}
