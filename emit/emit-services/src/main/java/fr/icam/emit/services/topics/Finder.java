package fr.icam.emit.services.topics;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Topic;

public class Finder extends JdbcQueryServlet<Topic> {

	private static final long serialVersionUID = 2017102310952002L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Topic topic = this.doProcess(request);
		request.setAttribute("found", topic != null);
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String topic = (String) request.getAttribute("topic");
		statement.setString(1, topic);
	}

    @Override
    protected Topic doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	Topic item = null;
    	while (resultSet.next()) {
    		String name = resultSet.getString("name");
    		String prefix = resultSet.getString("prefix");
    		if (resultSet.wasNull()) {
    			prefix = null;
    		}
    		String suffix = resultSet.getString("suffix");
    		Boolean leaf = resultSet.getBoolean("leaf");
    		item = new Topic(name, prefix, suffix, leaf);
    	}
    	return item;
    }

}
