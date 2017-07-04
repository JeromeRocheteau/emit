package fr.icam.emit.process;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;


import fr.icam.emit.entities.Measurement;
import fr.icam.emit.entities.Result;
import fr.icam.emit.tools.ConnexionAgent;
import fr.icam.emit.tools.File_handler;



public class ResultCalculate extends JdbcServlet {
	
private static final long serialVersionUID = 201706131132L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "ResultUnprocessed");
		List<Result> result = (List<Result>)request.getAttribute("result_plan");
		response.getWriter().write("hallo connard réveil toi!!!!!");
		try {
			this.doCalculation(result, request,response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch blockv
			e.printStackTrace();
		}
		
				
	
	}
	
	public void doCalculation(List<Result> result,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		File_handler filehandler = new File_handler();
		
		
		//recupérer le context
		String file_context = result.get(0).getContext();
		response.getWriter().write("j'ai besoin de toi!!!!!");
		String context = filehandler.read_file(file_context);
		
		String[] list = context.split(",");
		response.getWriter().write("context = "+list[0]);
		response.getWriter().write("longuer = "+list.length);
		
		
		
		
		double somme = 0;
		
		for (int i = 0;i<list.length;i++){
			response.getWriter().write("debut boucle");
			//etape 1: recupérer le ficher Json
			request.setAttribute("context",list[0]);
			this.doCall(request, response, "MeasurementListFile");
			@SuppressWarnings("unchecked")
			List<Measurement> measurment =  (List<Measurement>) request.getAttribute("measurements");
			response.getWriter().write("measurment");
			String Json_file = measurment.get(0).getData();
			
			String content = filehandler.read_file(Json_file);
			response.getWriter().write("content = "+content);
			//etape 2:eenveyer le contenue au serveur			
			String response_agent = ConnexionAgent.executePost(result.get(0).getAnalysis(), content);
			response.getWriter().write("content" );
			double value =Double.parseDouble(response_agent);
			response.getWriter().write("responce: "+value);		
			somme = value + somme;		
					
			
		}
		
		double resultf = somme/(list.length);
		response.getWriter().write("responce final: "+resultf);
		
		//enregistrer le resultat
		result.get(0).setValue(resultf);
		this.doWrite(result, response.getWriter());
		request.setAttribute("results", result);
		List<Result> result2 = (List<Result>)request.getAttribute("results");
		response.getWriter().write("test de la mort");
		this.doWrite(result2, response.getWriter());
		response.getWriter().write("enregistrement final:");
		this.doCall(request, response, "ResultUpdate");
		
		
	}


}
