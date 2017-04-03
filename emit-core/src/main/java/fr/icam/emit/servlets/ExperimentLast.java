package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public class ExperimentLast  extends JdbcQueryServlet<Integer> { 
	
	private static final long serialVersionUID = 201703011635L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer last = this.doProcess(request);
		this.doWrite(last, response.getWriter());
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		
	}

	@Override
	protected Integer doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		int last = 0;
		while (resultSet.next()) {
			last= resultSet.getInt("id");
		}
		return last;
	}
}