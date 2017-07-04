package fr.icam.emit.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;
import com.google.gson.Gson;

import fr.icam.emit.entities.Measurement;
import fr.icam.emit.entities.Result;
import fr.icam.emit.tools.File_handler;

public class ComplexeResultCreate extends JdbcUpdateServlet<Boolean> {
	
private static final long serialVersionUID = 201706141441L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
		
		
		Result result = (Result) request.getAttribute("result_plan");
		
		statement.setString(1,result.getAnalysis());
		statement.setString(2,result.getMeasure());		
		
		
		File_handler file_handler = new File_handler();
		
		String file_name = "Context-"+this.retourner_date()+".txt";
		int i = 0;
		//vérifer que le fichier est bien unique et qu'il nb'y en a pas un autre de créé
		while (file_handler.check_file_existance(file_name)){
			
			Thread.sleep(50);
			file_name = "Context-"+this.retourner_date()+"("+i+").txt";
			i++;
		};
		
		String context = result.getContext();
		
		
		
		file_handler.write_file(file_name,context);		
		statement.setString(3,file_name);
		statement.setString(4,result.getMeasurand()+" "+result.getEnvironment()+" "+result.getFeatures());
		
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		
		boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
		response.getWriter().write("finish");
	}
	
	public Long retourner_date(){
		 Timestamp timestamp = new Timestamp(System.nanoTime());
		 return timestamp.getTime();
	}	

}
