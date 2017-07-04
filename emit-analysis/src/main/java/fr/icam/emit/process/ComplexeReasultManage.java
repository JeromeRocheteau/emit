package fr.icam.emit.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;
import com.google.gson.Gson;

import fr.icam.emit.entities.Measurement;
import fr.icam.emit.entities.Result;

public class ComplexeReasultManage extends JdbcServlet{
	
	private static final long serialVersionUID = 201706280922L;


	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		//response.getWriter().write("début");
		//this.doCall(request, response, "ResultUnprocessed");
		//List<Result> result = (List<Result>)request.getAttribute("result_plan");
		//
		// retrive teh tata fom client
		Gson gson = new Gson();		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);		 
		Result result = gson.fromJson(reader, Result.class);
		
		//retrieve the list of context 
		request.setAttribute("result_plan", result);
		this.doCall(request, response, "complexe-context-list");		
		List<Measurement> measurement = (List<Measurement>) request.getAttribute("measurement");
		//this.doWrite(measurement, response.getWriter());
		//response.getWriter().write("fin");
		
		
		//Si on a bien un resultat
		if (measurement.size() >0){
			String context = "";
			for (int i = 0; i<measurement.size()-1;i++){
				context = context + measurement.get(i).getId()+",";
				}
			context = context + measurement.get(measurement.size()-1).getId();
			result.setContext(context);
			
			
			response.getWriter().write(context);
			
			request.setAttribute("result_plan", result);
			this.doCall(request, response, "ComplexeResultCreate");		
			
		}
				
	
	}
	
	

}
