package fr.icam.emit.services.callbacks;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.Symbol;
import fr.icam.emit.entities.Type;
import fr.icam.emit.entities.callbacks.FeatureCallback;
import fr.icam.emit.entities.callbacks.GuardCallback;
import fr.icam.emit.entities.callbacks.StorageCallback;
import fr.icam.emit.entities.callbacks.TopicCallback;
import fr.icam.emit.entities.callbacks.TypeCallback;

public class Itemizer extends JdbcQueryServlet<Callback> {

	private static final long serialVersionUID = 201711141212013L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Callback callback = this.doProcess(request);
			Callback mqttCallback = this.getCallback(callback);
			this.doWrite(mqttCallback, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Callback callback = this.doProcess(request);
		request.setAttribute("abstract-callback", callback);
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = this.getAttributeOrParameter(request, "id");
		statement.setLong(1, id);
	}
	
	private Long getAttributeOrParameter(HttpServletRequest request, String name) {
		Long value = (Long) request.getAttribute(name);
		if (value == null) {
			return Long.valueOf(request.getParameter(name));
		} else {
			return value;
		}
	}
	
    @Override
    protected Callback doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	Callback item = null;
    	if (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		String name = resultSet.getString("name");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		item = new Callback(id, name, issued.getTime(), user, atomic, category);
    	}
    	return item;
    }
    
	private Callback getCallback(Callback callback) throws Exception {
		Long id = callback.getId();
		String category = callback.getCategory();
		if (category.equals("type")) {
			ResultSet resultSet = this.getResultSet("types", id);
			TypeCallback typeCallback = this.getTypeCallback(resultSet);
			resultSet.close();
			return typeCallback;
		} else if (category.equals("topic")) {
			ResultSet resultSet = this.getResultSet("topics", id);
			TopicCallback topicCallback = this.getTopicCallback(resultSet);
			resultSet.close();
			return topicCallback;
		} else if (category.equals("storage")) {
			ResultSet resultSet = this.getResultSet("storages", id);
			StorageCallback storageCallback = this.getStorageCallback(resultSet);
			resultSet.close();
			return storageCallback;
		} else if (category.equals("feature")) {
			ResultSet resultSet = this.getResultSet("features", id);
			FeatureCallback featureCallback = this.getFeatureCallback(resultSet);
			resultSet.close();
			return featureCallback;
		} else if (category.equals("guard")) {
			ResultSet resultSet = this.getResultSet("guards", id);
			GuardCallback guardCallback = this.getGuardCallback(resultSet);
			Callback testCallback = guardCallback.getTest();
			Callback successCallback = guardCallback.getSuccess();
			Callback failureCallback = guardCallback.getFailure();
			guardCallback.setTest(this.getCallback(testCallback));
			guardCallback.setSuccess(this.getCallback(successCallback));
			if (failureCallback != null) {
				guardCallback.setFailure(this.getCallback(failureCallback));
			}
			resultSet.close();
			return guardCallback;
		} else {
			throw new ServletException("undefined callback category '" + category + "'");
		}
	}

	private ResultSet getResultSet(String name, Long id) throws Exception {
		String query = this.getContent("/fr/icam/emit/queries/callbacks/" + name + "/item.sql");
		Connection connection = this.getConnection();
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setLong(1, id);
		ResultSet resultSet = statement.executeQuery();
		connection.commit();
		return resultSet;
	}

	private TypeCallback getTypeCallback(ResultSet resultSet) throws Exception {
		TypeCallback item = null;
    	if (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		String name = resultSet.getString("name");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		String typeName = resultSet.getString("typeName");
    		String typeCategory = resultSet.getString("typeCategory");
    		Type type = new Type(typeName, typeCategory);
    		item = new TypeCallback(id, name, issued.getTime(), user, atomic, category, type);
    	}
    	return item;
	}

	private TopicCallback getTopicCallback(ResultSet resultSet) throws Exception {
		TopicCallback item = null;
    	if (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		String name = resultSet.getString("name");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		String topic = resultSet.getString("topic");
    		item = new TopicCallback(id, name, issued.getTime(), user, atomic, category, topic);
    	}
    	return item;
	}

	private StorageCallback getStorageCallback(ResultSet resultSet) throws Exception {
		StorageCallback item = null;
    	if (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		String name = resultSet.getString("name");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		String collection = resultSet.getString("collection");
    		item = new StorageCallback(id, name, issued.getTime(), user, atomic, category, collection);
    	}
    	return item;
	}

	private FeatureCallback getFeatureCallback(ResultSet resultSet) throws Exception {
		FeatureCallback item = null;
    	if (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		String name = resultSet.getString("name");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		String symbolName = resultSet.getString("symbolName");
    		String symbolHtml = resultSet.getString("symbolHtml");
    		String typeName = resultSet.getString("typeName");
    		String typeCategory = resultSet.getString("typeCategory");
    		String value = resultSet.getString("value");
    		Symbol symbol = new Symbol(symbolName, symbolHtml);
    		Type type = new Type(typeName, typeCategory);
    		item = new FeatureCallback(id, name, issued.getTime(), user, atomic, category, symbol, type, value);
    	}
    	return item;
	}

	private GuardCallback getGuardCallback(ResultSet resultSet) throws Exception {
		GuardCallback item = null;
    	if (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		String name = resultSet.getString("name");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		Long testId = resultSet.getLong("testId");
    		String testName = resultSet.getString("testName");
    		Boolean testAtomic = resultSet.getBoolean("testAtomic");
    		String testCategory = resultSet.getString("testCategory");
    		Timestamp testIssued = resultSet.getTimestamp("testIssued");
    		String testUser = resultSet.getString("testUser");
    		Long successId = resultSet.getLong("successId");
    		String successName = resultSet.getString("successName");
    		Boolean successAtomic = resultSet.getBoolean("successAtomic");
    		String successCategory = resultSet.getString("successCategory");
    		Timestamp successIssued = resultSet.getTimestamp("successIssued");
    		String successUser = resultSet.getString("successUser");
    		Long failureId = resultSet.getLong("failureId");                   if (resultSet.wasNull()) { failureId = null; }
    		String failureName = resultSet.getString("failureName");   if (resultSet.wasNull()) { failureName = null; }
    		Boolean failureAtomic = resultSet.getBoolean("failureAtomic");     if (resultSet.wasNull()) { failureAtomic = null; }
    		String failureCategory = resultSet.getString("failureCategory");   if (resultSet.wasNull()) { failureCategory = null; }
    		Timestamp failureIssued = resultSet.getTimestamp("failureIssued"); if (resultSet.wasNull()) { failureIssued = null; }
    		String failureUser = resultSet.getString("failureUser");           if (resultSet.wasNull()) { failureUser = null; }
    		Callback test = new Callback(testId, testName, testIssued.getTime(), testUser, testAtomic, testCategory);
    		Callback success = new Callback(successId, successName, successIssued.getTime(), successUser, successAtomic, successCategory);
    		Callback failure = failureId == null ? null : new Callback(failureId, failureName, failureIssued.getTime(), failureUser, failureAtomic, failureCategory);
    		item = new GuardCallback(id, name, issued.getTime(), user, atomic, category, test, success, failure);
    	}
    	return item;
	}

}
