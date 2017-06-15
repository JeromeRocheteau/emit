package fr.icam.emit.process;

import java.io.IOException;
import java.util.ArrayList;
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
		try {
			this.doCalculation(result, request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	
	}
	
	public void doCalculation(List<Result> result,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		File_handler filehandler = new File_handler();
		
		//recupérer le context
		String file_context = result.get(0).getContext();
		String context = filehandler.read_file(file_context);
		String[] list = context.split(",");
		List<Long> context_num = new ArrayList<Long>();
		
		for (int i = 0; i <list.length;i++){
			context_num.add(Long.parseLong(list[i]));
		}
		
		double somme = 0;
		
		for (int i = 0;i<context_num.size();i++){
			//etape 1: recupérer le ficher Json
			request.setAttribute("context", context_num.get(i));
			@SuppressWarnings("unchecked")
			List<Measurement> measurment =  (List<Measurement>) request.getAttribute("measurements");
			String Json_file = measurment.get(0).getData();
			String content = filehandler.read_file(Json_file);
			
			//etape 2:eenveyer le contenue au serveur			
			String response_agent = ConnexionAgent.executePost(result.get(0).getAnalysis(), content);
			double value =Long.parseLong(response_agent);
			
			somme = value + somme;		
					
			
		}
		
		double resultf = somme/context_num.size();
		//enregistrer le resultat
		result.get(0).setResult(resultf);
		request.setAttribute("Result", result);
		this.doCall(request, response, "ResultUpdate");
		
		
	}


}
