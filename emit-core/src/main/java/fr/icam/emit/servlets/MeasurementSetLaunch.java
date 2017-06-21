package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.analysis.File_handler;
import fr.icam.emit.analysis.InstrumentReader;
import fr.icam.emit.analysis.Serie;
import fr.icam.emit.entities.Measurement;
import fr.icam.emit.entities.MeasurementSet;
import fr.icam.emit.entities.MeasurementSet_plan;

public class MeasurementSetLaunch extends JdbcServlet {
	
	private static final long serialVersionUID = 201705021156L;


	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "measurementSet-unprocessed");
		List<MeasurementSet_plan> measurementSet = (List<MeasurementSet_plan>) request.getAttribute("measurementSet_plan");
		this.task_handler(measurementSet, request, response);
		this.doWrite(measurementSet, response.getWriter());
		//indiquer la date de fin
		MeasurementSet measurementSet_over = new MeasurementSet(measurementSet.get(0).getId(), measurementSet.get(0).getData(), this.retourner_date(), null, 0);
		request.setAttribute("measurementSet", measurementSet_over);
		this.doCall(request, response, "measurementSet-update");
	}
	
	
	public void task_handler(List<MeasurementSet_plan> measurementSet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		File_handler file_handler = new File_handler();
		//rechercher le ficher csv
		String content = this.retrive_csv_file(measurementSet.get(0).getData(),file_handler);
		
		//response.getWriter().write(content);
		//mapper les donners dans une variable table
		InstrumentReader reader = new InstrumentReader();
		List<List<String>> table = reader.Read(content);
		
		//generation et enregistrement des measurements et des fichiers json
		List<Serie> data = new ArrayList<Serie>();
		for (int i = 0; i<measurementSet.size();i++){			
			for (int j = 2;j<table.size();j++){
				data.add(new Serie( Double.parseDouble(table.get(j).get(0)),Double.parseDouble(table.get(j).get(i+1))));
			}
			String json = reader.create_json(data);
			data.clear();
			String file_name = "emit-"+this.retourner_date()+".json";
			//response.getWriter().write(json);
			file_handler.write_file(file_name, json);
			Measurement measurement = new Measurement(0,measurementSet.get(i).getId(),file_name,measurementSet.get(i).getFeature().getMeasure(),measurementSet.get(i).getFeature().getId());
			request.setAttribute("measurement", measurement);
			this.doWrite(measurement, response.getWriter());
			this.doCall(request, response, "measurement-create");
			//response.getWriter().write("done");
			//response.getWriter().write(""+i);
		}
		
		
	}
	
	public String retrive_csv_file(String file_name,File_handler file_handler){
		String content = "";
		
		try {
			content = file_handler.read_file(file_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	public Long retourner_date(){
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 return timestamp.getTime();
	}
	
	
	
	
}
