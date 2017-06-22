package fr.icam.emit.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class PassCreate extends JdbcUpdateServlet<String> {

	private static final long serialVersionUID = 23L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String passphrase = UUID.randomUUID().toString();
		request.setAttribute("passphrase", passphrase);
		String username = request.getParameter("username");
		statement.setString(1, passphrase);
		statement.setString(2, username);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doProcess(request);
	}

	@Override
	protected String doMap(HttpServletRequest request, int count, ResultSet resultset) throws Exception {
		return (String) request.getAttribute("passphrase");
	}
	
}
