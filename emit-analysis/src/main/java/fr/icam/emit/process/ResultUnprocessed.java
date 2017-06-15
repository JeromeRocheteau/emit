package fr.icam.emit.process;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Result;





public class ResultUnprocessed  extends JdbcQueryServlet<List<Result>> {   
	private static final long serialVersionUID = 201706130922L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Result> result_plan = this.doProcess(request);
		request.setAttribute("result_plan", result_plan);
		this.doWrite(result_plan, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {

	}

	@Override
	protected List<Result> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Result> result = new LinkedList<Result>();
		while (resultSet.next()) {
			String analysis = resultSet.getString("analysis");
			String measure = resultSet.getString("measure");
			String context = resultSet.getString("context");
			String condition = "";
			Long id = (long) 0;
			
			result.add(new Result(id,analysis, measure, context,0,condition));
		}
		return result;
	}
	

}
