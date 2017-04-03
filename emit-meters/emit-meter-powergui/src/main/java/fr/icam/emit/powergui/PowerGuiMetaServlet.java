package fr.icam.emit.powergui;

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

public class PowerGuiMetaServlet extends PowerGuiServlet {

	private List<Feature> features;
	
	private static final long serialVersionUID = 20160519001L;
	
	private ObjectMapper mapper;
	
	@Override
	public void init()  {
		mapper = new ObjectMapper();
		features = Arrays.asList(
				Emit.feature("power-1", 1.0f, Emit.POWER), 
				Emit.feature("power-2", 1.0f, Emit.POWER) 
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
