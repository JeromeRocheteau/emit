package fr.icam.emit.services.topics;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Topic;

public class Lister extends fr.icam.emit.services.commons.Lister<Topic> {

	private static final long serialVersionUID = 2017102310952001L;

    @Override
    protected List<Topic> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Topic> items = new LinkedList<Topic>();
    	while (resultSet.next()) {
    		String name = resultSet.getString("name");
    		String prefix = resultSet.getString("prefix");
    		if (resultSet.wasNull()) {
    			prefix = null;
    		}
    		String suffix = resultSet.getString("suffix");
    		Boolean leaf = resultSet.getBoolean("leaf");
    		Topic item = new Topic(name, prefix, suffix, leaf);
    		items.add(item);
    	}
    	return items;
    }

}
