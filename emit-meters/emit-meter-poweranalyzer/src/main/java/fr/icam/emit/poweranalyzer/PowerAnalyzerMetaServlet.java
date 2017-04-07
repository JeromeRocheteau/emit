package fr.icam.emit.poweranalyzer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.icam.emit.api.Emit;
import fr.icam.emit.api.Feature;
import fr.icam.emit.api.Measure;

public class PowerAnalyzerMetaServlet extends HttpServlet {

	private List<Feature> features;
	
	private static final long serialVersionUID = 20160523001L;

	private static Feature feature(String name, Measure measure) { 
		Feature feature = new Feature();
		feature.setName(name);
		feature.setMeasure(measure);
		return feature;
	}
	
	private ObjectMapper mapper;
	
	@Override
	public void init()  {
		mapper = new ObjectMapper();
		features = Arrays.asList(
				feature("power",Emit.POWER), 
				feature("voltage",Emit.VOLTAGE), 
				feature("intensity",Emit.INTENSITY), 
				feature("factor",Emit.FACTOR)
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
