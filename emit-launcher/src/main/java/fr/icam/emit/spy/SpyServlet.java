package fr.icam.emit.spy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SpyServlet extends HttpServlet {

	private static final long serialVersionUID = 2849318140512478885L;

	protected SpyMeter meter;
	
	@Override
	public void init() throws ServletException {
		try {
			if (meter == null) {
				meter = new SpyMeter();
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
	
}
