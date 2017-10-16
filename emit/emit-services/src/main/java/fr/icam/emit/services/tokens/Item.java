package fr.icam.emit.services.tokens;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Token;

public class Item extends JdbcQueryServlet<Token> {

	private static final long serialVersionUID = 201709111112003L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Token token = this.doProcess(request);
		this.doWrite(token, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getUserPrincipal().getName();
		statement.setString(1, username);
	}
	
	@Override
	protected Token doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		Token token = null;
		while (resultSet.next()) {
			String uuid = resultSet.getString("uuid");
			String username = resultSet.getString("username");
			token = new Token(uuid, username);
		}
		return token;
	}

}
