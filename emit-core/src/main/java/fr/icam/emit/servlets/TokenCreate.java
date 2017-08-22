package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class TokenCreate extends JdbcUpdateServlet<String> {

	private static final long serialVersionUID = 201708221753004L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String passphrase = this.doProcess(request);
		this.doWrite(passphrase, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = (String) request.getSession().getAttribute("username");
		Long measurand = Long.valueOf(request.getParameter("measurand"));
		String passphrase = UUID.randomUUID().toString();
		request.setAttribute("passphrase", passphrase);
		statement.setString(1, passphrase);
		statement.setString(2, username);
		statement.setLong(3, measurand);
	}

	@Override
	protected String doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return (String) request.getAttribute("passphrase");
	}
	
}