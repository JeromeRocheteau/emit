package fr.icam.emit.services.callbacks;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Callback;

public class Itemizer extends JdbcQueryServlet<Callback> {

	private static final long serialVersionUID = 201711141212013L;

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

}
