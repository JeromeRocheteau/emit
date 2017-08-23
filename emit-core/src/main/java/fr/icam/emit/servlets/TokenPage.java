package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Measurand;
import fr.icam.emit.entities.Token;

public class TokenPage extends JdbcQueryServlet<List<Token>> {

	private static final long serialVersionUID = 201708221753002L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Token> tokens = this.doProcess(request);
		this.doWrite(tokens, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = (String) request.getSession().getAttribute("username");
		Integer offset = Integer.valueOf(request.getParameter("offset"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		statement.setString(1, username);
		statement.setInt(2, offset);
		statement.setInt(3, length);
	}

	@Override
	protected List<Token> doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		List<Token> tokens = new LinkedList<Token>();
		while (resultSet.next()) {
			String passphrase = resultSet.getString("passphrase");
			Timestamp issued = resultSet.getTimestamp("issued");
			String name = resultSet.getString("measurand");
			Measurand measurand = new Measurand(null, name, null);
			Token token = new Token(passphrase, null, null, measurand, issued.getTime());
			tokens.add(token);
		}
		return tokens;
	}
	
}