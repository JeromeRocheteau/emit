package fr.icam.emit.servlets;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.entities.MeasurementSet;


public class MeasurementSetUpdate extends JdbcUpdateServlet<Boolean>{	
	private static final long serialVersionUID = 201704241124L;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		MeasurementSet measurementSet = (MeasurementSet) request.getAttribute("measurementSet");
		
		statement.setLong(3, measurementSet.getId());		
		statement.setString(1, measurementSet.getData());
		
		if (measurementSet.getAchieved()==null){
			statement.setNString(2, null);
		}else{
			statement.setTimestamp(2, (new Timestamp(measurementSet.getAchieved())));
		}
		
		
		/*
		statement.setLong(3, 23);		
		statement.setString(1,"bonjour");
		Long num = (long) 253456;
		if (num==null){
			statement.setNString(2, null);
		}else{
			statement.setTimestamp(2, (new Timestamp(num)));
		}
		*/
		
	
		
		
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean done = this.doProcess(request);
		this.doWrite(done, response.getWriter());
	}
}
