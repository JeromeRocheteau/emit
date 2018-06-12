package fr.icam.emit.services.records;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.icam.emit.entities.Action;

public class Pager extends fr.icam.emit.services.commons.Pager<Action> {

	private static final long serialVersionUID = 201710191845001L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			List<Action> actions = this.doProcess(request);
			this.doWrite(actions, response.getWriter());
		} catch (Exception e) {
			throw new ServletException(e);
		}			
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String client = request.getParameter("client");
		Long offset = Long.valueOf(request.getParameter("offset"));
		Long length = Long.valueOf(request.getParameter("length"));
		statement.setString(1, client);
		statement.setString(2, client);
		statement.setString(3, client);
		statement.setLong(4, offset);
		statement.setLong(5, length);
	}

	@Override
	protected List<Action> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Action> items = new LinkedList<Action>();
    	while (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		Timestamp started = resultSet.getTimestamp("started");
    		Timestamp stopped = resultSet.getTimestamp("stopped");
    		if (resultSet.wasNull()) {
    			stopped = null;
    		}
    		String user = resultSet.getString("user");
    		String client = resultSet.getString("client");
    		String type = resultSet.getString("type");
    		String topic = resultSet.getString("topic");
    		Action item = new Action(id, started.getTime(), stopped == null ? null : stopped.getTime(), user, client, type, topic);
    		items.add(item);
    	}
    	return items;

	}

}
