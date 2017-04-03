package fr.icam.emit.arduino;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ArduinoServlet extends HttpServlet {

	private static final long serialVersionUID = 20160407000L;

	protected static ArduinoMeter meter;
	
	@Override
	public void init() throws ServletException {
		try {
			if (meter == null) {
				meter = new ArduinoMeter();
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
