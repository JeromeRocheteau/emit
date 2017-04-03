package fr.icam.emit.arduino;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.icam.emit.api.Emit;
import fr.icam.emit.api.Feature;

public class ArduinoMetaServlet extends ArduinoServlet {

	private List<Feature> features;
	
	private static final long serialVersionUID = 20160407001L;
	
	private ObjectMapper mapper;
	
	@Override
	public void init()  {
		mapper = new ObjectMapper();
		features = Arrays.asList(
				Emit.feature("voltage", 1.0f, Emit.VOLTAGE), 
				Emit.feature("intensity-1", 1.0f, Emit.INTENSITY), 
				Emit.feature("power-1", 1.0f, Emit.POWER), 
				Emit.feature("factor-1", 1.0f, Emit.FACTOR), 
				Emit.feature("intensity-2", 1.0f, Emit.INTENSITY), 
				Emit.feature("power-2", 1.0f, Emit.POWER), 
				Emit.feature("factor-2", 1.0f, Emit.FACTOR), 
				Emit.feature("intensity-3", 1.0f, Emit.INTENSITY), 
				Emit.feature("power-3", 1.0f, Emit.POWER), 
				Emit.feature("factor-3", 1.0f, Emit.FACTOR)
		);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			OutputStream out = response.getOutputStream();
			mapper.writeValue(out, features);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
}
