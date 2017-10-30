package fr.icam.emit.services.brokers;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Broker;

public class Pager extends fr.icam.emit.services.commons.UserPager<Broker> {

	private static final long serialVersionUID = 201710301015002L;

    @Override
    protected List<Broker> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Broker> items = new LinkedList<Broker>();
    	while (resultSet.next()) {
    		String uri = resultSet.getString("uri");
    		String user = resultSet.getString("user");
    		String username = resultSet.getString("username");
    		String password = resultSet.getString("password");
    		Broker item = new Broker(uri, user, username, password);
    		items.add(item);
    	}
    	return items;
    }

}
