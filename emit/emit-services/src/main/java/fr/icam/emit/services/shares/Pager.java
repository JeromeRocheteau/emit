package fr.icam.emit.services.shares;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Share;

public class Pager extends fr.icam.emit.services.commons.UserPager<Share> {

	private static final long serialVersionUID = 201710171230002L;

    @Override
    protected List<Share> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Share> items = new LinkedList<Share>();
    	while (resultSet.next()) {
    		String uuid = resultSet.getString("uuid");
    		String broker = resultSet.getString("broker");
    		String user = resultSet.getString("user");
    		Boolean control = resultSet.getBoolean("control");
    		if (resultSet.wasNull()) {
    			control = null;
    		}
    		Share item = new Share(uuid, broker, user, control);
    		items.add(item);
    	}
    	return items;
    }

}
