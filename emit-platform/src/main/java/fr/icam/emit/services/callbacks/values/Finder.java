package fr.icam.emit.services.callbacks.values;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Type;
import fr.icam.emit.entities.callbacks.ValueCallback;

public class Finder extends JdbcQueryServlet<ValueCallback> {

	private static final long serialVersionUID = 202207041250001L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ValueCallback callback = this.doProcess(request);
		this.doWrite(callback, response.getWriter());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ValueCallback callback = this.doProcess(request);
		request.setAttribute("callback", callback);
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
    protected ValueCallback doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	ValueCallback item = null;
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
    		String value = resultSet.getString("value");
    		item = new ValueCallback(id, name, issued.getTime(), user, atomic, category, type, value);
    	}
    	return item;
    }

}
