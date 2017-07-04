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

import fr.icam.emit.entities.Measurement;
import fr.icam.emit.entities.Result;



public class ComplexeContextList  extends JdbcQueryServlet<List<Measurement>> {
private static final long serialVersionUID = 201706130922L;

		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			response.getWriter().write("hello");
			List<Measurement> measurment = this.doProcess(request);
			request.setAttribute("measurment", measurment);
			this.doWrite(measurment, response.getWriter());
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
			/*
			Result result = (Result)request.getAttribute("result_plan");
			statement.setString(3, result.getMeasurand());
			statement.setString(1, result.getEnvironment());
			statement.setInt(2, result.getFeatures());
			*/
				
			
			statement.setString(3, "http://172.16.220.253:8080/emitlauncher/");
			statement.setString(1,"/var/local/emit/hanoi-tower 17" );
			statement.setInt(2, 8);
			
		}
		
		@Override
		protected List<Measurement> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
			List<Measurement> measurement = new LinkedList<Measurement>();
			while (resultSet.next()) {
				long id = resultSet.getLong("id");
				//long measurementSet = resultSet.getLong("measure");
				long measurementSet = 0;
				//String data = resultSet.getString("data");
				String data = "";
				//String measure = resultSet.getString("measure");
				String measure = "";
				
				
				//long id,long measurementSet, String data, String measure
				measurement.add(new Measurement(id,measurementSet, data, measure));
			}
			return measurement;
}
	

	

}
