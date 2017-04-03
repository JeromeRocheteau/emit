package fr.icam.emit.powergui;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class PowerGuiServlet extends HttpServlet {

	private static final long serialVersionUID = 20160407000L;

	protected static PowerGuiMeter meter;
	
	@Override
	public void init() throws ServletException {
		try {
			if (meter == null) {
				meter = new PowerGuiMeter();
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
