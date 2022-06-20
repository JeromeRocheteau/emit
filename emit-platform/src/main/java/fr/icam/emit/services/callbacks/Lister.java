package fr.icam.emit.services.callbacks;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Callback;

public class Lister extends fr.icam.emit.services.commons.UserLister<Callback> {

	private static final long serialVersionUID = 201711101212001L;

    @Override
    protected List<Callback> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Callback> items = new LinkedList<Callback>();
    	while (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		String name = resultSet.getString("name");
    		Boolean atomic = resultSet.getBoolean("atomic");
    		String category = resultSet.getString("category");
    		Timestamp issued = resultSet.getTimestamp("issued");
    		String user = resultSet.getString("user");
    		Callback item = new Callback(id, name, issued.getTime(), user, atomic, category);
    		items.add(item);
    	}
    	return items;
    }

}
