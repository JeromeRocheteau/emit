package fr.icam.emit.services.brokers;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Lister extends fr.icam.emit.services.commons.UserLister<String> {

	private static final long serialVersionUID = 201710301015001L;

    @Override
    protected List<String> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<String> items = new LinkedList<String>();
    	while (resultSet.next()) {
    		String item = resultSet.getString("uri");
    		items.add(item);
    	}
    	return items;
    }

}
