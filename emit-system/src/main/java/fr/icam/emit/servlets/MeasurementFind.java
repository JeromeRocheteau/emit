package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.github.jeromerocheteau.JdbcQueryServlet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import fr.icam.emit.entities.Access;
import fr.icam.emit.entities.Feature;
import fr.icam.emit.entities.Instrument;
import fr.icam.emit.entities.Measure;
import fr.icam.emit.entities.Measurement;

public class MeasurementFind extends JdbcQueryServlet<List<Measurement>> {

	private static final long serialVersionUID = 201709041107006L;

	private MongoDatabase database;
	
	@Override
	public void init() throws ServletException {
		super.init();
		database = (MongoDatabase) this.getServletContext().getAttribute("mongodb-database");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Measurement> measurements = this.doProcess(request);
		for (Measurement measurement : measurements) {
			this.getData(measurement);
		}
		this.doWrite(measurements, response.getWriter());
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Access access = (Access) request.getAttribute("access");
		statement.setLong(1, access.getToken().getFeature().getId());
		statement.setTimestamp(2, new Timestamp(access.getIssued()));
	}
	
	@Override
	protected List<Measurement> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Measurement> measurements = new LinkedList<Measurement>();
		while (resultSet.next()) {
			Long id = resultSet.getLong("id");
			Timestamp started = resultSet.getTimestamp("started");
			Timestamp stopped = resultSet.getTimestamp("stopped");
			String uuid = resultSet.getString("uuid");

			Long featureId = resultSet.getLong("featureId");
			String featureName = resultSet.getString("featureName");
			Integer featureFactor = resultSet.getInt("featureFactor");

			Long measureId = resultSet.getLong("measureId");
			String measureName = resultSet.getString("measureName");
			String measureUnit = resultSet.getString("measureUnit");

			Long instrumentId = resultSet.getLong("instrumentId");
			String instrumentUri = resultSet.getString("instrumentUri");
			String instrumentName = resultSet.getString("instrumentName");
			
			Instrument instrument = new Instrument(instrumentId, null, instrumentUri, instrumentName); 
			Measure measure = new Measure(measureId, null, measureName, measureUnit);
			Feature feature = new Feature(featureId, null, featureName, featureFactor, measure, instrument);
			Measurement measurement = new Measurement(id, null, started.getTime(), stopped.getTime(), uuid, feature);
			measurements.add(measurement);
		}
		return measurements;
	}
	
	private void getData(Measurement measurement) {
		Map<Long, Double> data = new TreeMap<Long, Double>();
		MongoCollection<Document> collection = database.getCollection(measurement.getUuid());
		MongoCursor<Document> cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			Document object = cursor.next();
			Long time = object.getLong("time");
			Double value = object.getDouble("value");
			data.put(time, value);
		}
		measurement.setData(data);
	}

}