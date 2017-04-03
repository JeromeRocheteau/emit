package fr.icam.emit.poweranalyzer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class PowerAnalyzerServlet extends HttpServlet {

	private static final long serialVersionUID = 20160407000L;

	protected static PowerAnalyzer meter;
	
	@Override
	public void init() throws ServletException {
		try {
			if (meter == null) {
				meter = new PowerAnalyzer();
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
