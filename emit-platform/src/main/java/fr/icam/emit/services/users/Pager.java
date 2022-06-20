package fr.icam.emit.services.users;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.User;

public class Pager extends fr.icam.emit.services.commons.UserPager<User> {

	private static final long serialVersionUID = 201711031836002L;

    @Override
    protected List<User> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<User> items = new LinkedList<User>();
    	while (resultSet.next()) {
    		String username = resultSet.getString("username");
    		String rolename = resultSet.getString("rolename");
    		User item = new User(username, null, rolename);
    		items.add(item);
    	}
    	return items;
    }

}
