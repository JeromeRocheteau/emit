package fr.icam.emit.services.connects;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Client;
import fr.icam.emit.entities.Connect;

public class Pager extends fr.icam.emit.services.commons.UserPager<Connect> {

	private static final long serialVersionUID = 201710171200002L;

    @Override
    protected List<Connect> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Connect> items = new LinkedList<Connect>();
    	while (resultSet.next()) {
    		Long id = resultSet.getLong("id");
    		Timestamp started = resultSet.getTimestamp("started");
    		Timestamp stopped = resultSet.getTimestamp("stopped");
    		if (resultSet.wasNull()) {
    			stopped = null;
    		}
    		String user = resultSet.getString("user");
    		String clientUuid = resultSet.getString("clientUuid");
    		String clientBroker = resultSet.getString("clientBroker");
    		String clientUser = resultSet.getString("clientUser");
    		Client client = new Client(clientUuid, clientBroker, clientUser);
    		Connect item = new Connect(id, started.getTime(), stopped == null ? null : stopped.getTime(), user, client);
    		items.add(item);
    	}
    	return items;
    }

}
