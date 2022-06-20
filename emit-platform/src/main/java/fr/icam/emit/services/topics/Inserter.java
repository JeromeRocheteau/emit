package fr.icam.emit.services.topics;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Inserter extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 2017102310952003L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer count = this.doProcess(request);
		request.setAttribute("count", count);
	}

	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String name = (String) request.getAttribute("name");
		String prefix = (String) request.getAttribute("prefix");
		String suffix = (String) request.getAttribute("suffix");
		Boolean leaf = (Boolean) request.getAttribute("leaf");
		statement.setString(1, name);
		if (prefix == null) {
			statement.setNull(2, Types.VARCHAR);
		} else {
			statement.setString(2, prefix);
		}
		statement.setString(3, suffix);
		statement.setBoolean(4, leaf);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
