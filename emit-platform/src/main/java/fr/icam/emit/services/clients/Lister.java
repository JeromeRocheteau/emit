package fr.icam.emit.services.clients;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Broker;
import fr.icam.emit.entities.Client;

public class Lister extends fr.icam.emit.services.commons.UserLister<Client> {

	private static final long serialVersionUID = 201806041730001L;

    @Override
    protected List<Client> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Client> items = new LinkedList<Client>();
    	while (resultSet.next()) {
    		String uuid = resultSet.getString("uuid");
    		String name = resultSet.getString("name");
    		String brokerUri = resultSet.getString("brokerUri");
    		String brokerName = resultSet.getString("brokerName");
    		String brokerUser = resultSet.getString("brokerUser");
    		String user = resultSet.getString("user");
    		Boolean open = resultSet.getBoolean("open");
    		Broker broker = new Broker(brokerUri, brokerName, brokerUser, null, null);
    		Client item = new Client(uuid, name, broker, user, open);
    		items.add(item);
    	}
    	return items;
    }

}
