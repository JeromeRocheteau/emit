package fr.icam.emit.arduino;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ArduinoServlet extends HttpServlet {

	private static final long serialVersionUID = 20160407000L;

	protected static ArduinoMeter meter;
	
	@Override
	public void init() throws ServletException {
		meter = new ArduinoMeter();
	}
	
	@Override
	public void destroy() {
		try {
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
