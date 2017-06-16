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
private Result result;	
HttpServletResponse responsebis;
	@SuppressWarnings("unchecked")
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
		
		
		Gson gson = new Gson();		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);		 
		result = gson.fromJson(reader, Result.class);
		request.setAttribute("reasult_plan", result);
		
		statement.setString(1,result.getAnalysis());
		statement.setString(2,result.getMeasure());
		
		this.doCall(request, responsebis, "ComplexeContextList");
		List<Measurement> measurement = (List<Measurement>) request.getAttribute("measurement");
		
		File_handler file_handler = new File_handler();
		
		String file_name = "Context-"+this.retourner_date()+".txt";
		
		String context = "";
		for (int i = 0;i<measurement.size()-1;i++){
			context = context +measurement.get(i).getId() ;
			context = context +","; 
		}
		context = context + measurement.get(measurement.size()-1).getId(); 
		
		file_handler.write_file(file_name,context);		
		statement.setString(3,file_name);
		statement.setString(4,result.getCondition());
		
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		responsebis =  response;
		
		boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
	
	public Long retourner_date(){
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		 return timestamp.getTime();
	}	

}
