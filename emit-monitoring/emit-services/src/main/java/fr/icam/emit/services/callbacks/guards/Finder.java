package fr.icam.emit.services.callbacks.guards;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.callbacks.GuardCallback;

public class Finder extends JdbcQueryServlet<GuardCallback> {

	private static final long serialVersionUID = 201711131345001L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GuardCallback callback = this.doProcess(request);
		this.doWrite(callback, response.getWriter());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GuardCallback callback = this.doProcess(request);
		request.setAttribute("mqtt-callback", callback);
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, id);
	}

    @Override
    protected GuardCallback doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
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
