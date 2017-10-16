package fr.icam.emit.services.clients;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Client;

public class Pager extends fr.icam.emit.services.commons.Pager<Client> {

	private static final long serialVersionUID = 201710161616002L;

    @Override
    protected List<Client> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Client> items = new LinkedList<Client>();
    	while (resultSet.next()) {
    		String uuid = resultSet.getString("uuid");
    		String broker = resultSet.getString("broker");
    		Client item = new Client(uuid, broker);
    		items.add(item);
    	}
    	return items;
    }

}
