package fr.icam.emit.services.callbacks.features;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Symbol;
import fr.icam.emit.entities.Type;
import fr.icam.emit.entities.callbacks.FeatureCallback;

public class Finder extends JdbcQueryServlet<FeatureCallback> {

	private static final long serialVersionUID = 201711160900001L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		FeatureCallback callback = this.doProcess(request);
		this.doWrite(callback, response.getWriter());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		FeatureCallback callback = this.doProcess(request);
		request.setAttribute("callback", callback);
	}

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, id);
	}

    @Override
    protected FeatureCallback doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	FeatureCallback item = null;
    	if (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		String symbolName = resultSet.getString("symbolName");
    		String typeName = resultSet.getString("typeName");
    		String typeCategory = resultSet.getString("typeCategory");
    		String value = resultSet.getString("value");
    		Symbol symbol = new Symbol(symbolName);
    		Type type = new Type(typeName, typeCategory);
    		item = new FeatureCallback(id, issued.getTime(), user, atomic, category, symbol, type, value);
    	}
    	return item;
    }

}
