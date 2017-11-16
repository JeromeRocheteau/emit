package fr.icam.emit.services.symbols;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.icam.emit.entities.Symbol;

public class Lister extends fr.icam.emit.services.commons.Lister<Symbol> {

	private static final long serialVersionUID = 201711161140001L;

    @Override
    protected List<Symbol> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
    	List<Symbol> items = new LinkedList<Symbol>();
    	while (resultSet.next()) {
    		String name = resultSet.getString("name");
    		Symbol item = new Symbol(name);
    		items.add(item);
    	}
    	return items;
    }

}
