package fr.icam.emit.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.analysis.File_handler;
import fr.icam.emit.api.Launcher;
import fr.icam.emit.api.Meter;
import fr.icam.emit.entities.Experiment;
import fr.icam.emit.entities.Experiment_plan;
import fr.icam.emit.entities.MeasurementSet;

public class ExperimentLaunch extends JdbcServlet {


	private static final long serialVersionUID = 201704061657L;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "experiment-unprocessed");
		List<Experiment_plan> experiment = (List<Experiment_plan>) request.getAttribute("experiment_plan");
		try {
			this.handle_experiement(experiment,request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.doWrite(experiment, response.getWriter());
	}
	
	public void handle_experiement(List<Experiment_plan> experiment,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<MeasurementSet> measurementSets = new ArrayList<MeasurementSet>();
		boolean canLaunch = false;
		String string_uri = experiment.get(0).getEnvironment().getUri();
		URI uri = new URI(string_uri);
		Launcher launcher= new Launcher(uri);
		//String proc = "/var/local/emit/hanoi-tower-java 16";
		String proc = experiment.get(0).getExperiment().getMeasurand().getProcess();
		String[] items = proc.split("\\s+");
		String[] args = Arrays.copyOfRange(items, 1, items.length);
		String cmd = items[0];
		canLaunch = this.check_tools(launcher,cmd);	
		
		if (canLaunch == true){
			
			measurementSets = this.execut_measure(experiment, launcher,response,cmd,args);			
			for (MeasurementSet measurementSet:measurementSets){
				
				request.setAttribute("measurementSet",measurementSet );
				this.doCall(request, response, "measurementSet-update");
			}//else renvoi erreur
			//fin de l'experiment
			Experiment experiment_end = new Experiment(experiment.get(0).getExperiment().getId(),null,this.retourner_date(),null,"");
			request.setAttribute("experiment",experiment_end );
			this.doCall(request, response, "experiment-update");
		}else{
			Experiment experiment_end = new Experiment(experiment.get(0).getExperiment().getId(),null,(long)0,null,"");
			request.setAttribute("experiment",experiment_end );
			this.doCall(request, response, "experiment-update");
			
		}
	};
	
	
	public List<MeasurementSet> execut_measure(List<Experiment_plan> experiment,Launcher launcher,HttpServletResponse response, String command,String... parameter) throws Exception{
		//liste des instrument
		List<Meter> instruments = new ArrayList<Meter>();
		//liste des resulats (sous forme de MeasurementSet)
		List<MeasurementSet> measurementSets = new ArrayList<MeasurementSet>();
		
		//String command = "/var/local/emit/hanoi-tower-java";
		
		
		//lancement des instruments
		for (int i = 0; i<experiment.size();i++){
			
			URI host = new URI(experiment.get(i).getMeasurementSet().getInstrument());
			instruments.add(new Meter(host));
		}
		
		for (int i = 0; i<instruments.size();i++){
			instruments.get(i).doStart();			
		}
		//execution mesure
		Thread.sleep(5000);		
		launcher.doLaunch(0L, command, parameter);
		Thread.sleep(5000);
		
		//stopper les instruments
		for (int i = 0; i<instruments.size();i++){
			instruments.get(i).doStop();			
		}
		
		//enregistrer les resultats (update measurementSet)
		for (int i = 0; i<instruments.size();i++){
			String data = instruments.get(i).doRetrieve(System.out);
			String data_name = "emit-"+this.retourner_date()+".csv";
			response.getWriter().write(data);
			response.getWriter().write(data_name);
			File_handler file_handler = new File_handler();
			file_handler.write_file(data_name, data);
			measurementSets.add(new MeasurementSet(experiment.get(i).getMeasurementSet().getId(),data_name,null,null,0));
		}
		
		return measurementSets;
		
	}
	
	public boolean check_tools(Launcher launcher, String command) throws Exception{
		boolean launch = false;
		
		launch = launcher.canLaunch(command);
		
		/*
		for (int i = 0; i<experiment.size();i++){
			String host = experiment.get(i).getMeasurementSet().getInstrument();
			
		}
		*/
		return launch;
		
	}
	
	public Long retourner_date(){
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 return timestamp.getTime();
	}
	
}


