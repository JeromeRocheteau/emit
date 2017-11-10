package fr.icam.emit.services.types;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Type;

public class Lister extends fr.icam.emit.services.commons.UserLister<Type> {

	private static final long serialVersionUID = 201711101221001L;

    @Override
    protected List<Type> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Type> items = new LinkedList<Type>();
    	while (resultSet.next()) {
    		String name = resultSet.getString("name");
    		String category = resultSet.getString("category");
    		Type item = new Type(name, category);
    		items.add(item);
    	}
    	return items;
    }

}
