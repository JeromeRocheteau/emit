package fr.icam.emit.process;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

import fr.icam.emit.entities.Result;

public class ResultUpdate extends JdbcUpdateServlet<Boolean>{
	
private static final long serialVersionUID = 201704241124L;
Result result;
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
		List<Result> result = (List<Result>)request.getAttribute("results");
		
		
		statement.setLong(2, result.get(0).getId());		
		statement.setDouble(1, result.get(0).getValue());	
		
		
		/*
		statement.setLong(2, 81);		
		statement.setDouble(1, 10.5);
		*/	
		
		
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count,ResultSet resultSet) throws Exception {
		return count > 0;
	}
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.getWriter().write("update");
		
		//response.getWriter().write("end of the prog");
		boolean done = this.doProcess(request);
		
		this.doWrite(done, response.getWriter());
		
	}

}
