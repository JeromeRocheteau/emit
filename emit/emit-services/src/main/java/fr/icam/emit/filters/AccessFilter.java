package fr.icam.emit.filters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.github.jeromerocheteau.JdbcFilter;

import fr.icam.emit.entities.Access;
import fr.icam.emit.entities.Token;

public class AccessFilter extends JdbcFilter {

	@Override
	protected void doFill(PreparedStatement statement, ServletRequest request) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String uuid = httpRequest.getHeader("X-Access-Token");
		statement.setString(1, uuid);
	}
	
	@Override
	protected void doMap(ServletRequest request, ResultSet resultSet) throws Exception {
		if (resultSet.next()) {
			Long id = resultSet.getLong("id");
			Timestamp issued = resultSet.getTimestamp("issued");
			String uuid = resultSet.getString("uuid");
			String username = resultSet.getString("username");
			Token token = new Token(uuid, username);
			Access access = new Access(id, issued.getTime(), token);			
			request.setAttribute("access", access);
		} else {
			throw new ServletException("token-access");
		}
	}
	
}
