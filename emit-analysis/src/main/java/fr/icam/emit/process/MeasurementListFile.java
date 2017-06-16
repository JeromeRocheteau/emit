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

public class MeasurementListFile extends JdbcQueryServlet<List<Measurement>> {
	


		private static final long serialVersionUID = 201703011438L;

		@Override
		protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
			 //String fkey = request.getParameter("context"); 
			 String fkey = (String) request.getAttribute("context");
	         statement.setString(1,fkey);
		}

		@Override
		protected List<Measurement> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
			List<Measurement> measurements = new LinkedList<Measurement>();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String data = resultSet.getString("data");
	            String name = resultSet.getString("measure");
	            int measurementSetId = resultSet.getInt("measurementset");
	            measurements.add(new Measurement(id, measurementSetId,data, name));
	        }
	        return measurements;
		}
		
		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			List<Measurement> measurements = this.doProcess(request);
			request.setAttribute("measurements", measurements);
	        this.doWrite(measurements, response.getWriter());
		}

	

}
