package fr.icam.emit.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

import fr.icam.emit.entities.Account;

public class AccountCheck extends JdbcQueryServlet<Account> {

	private static final long serialVersionUID = 201708221657002L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "account-find");
		Account account = this.doProcess(request);
		request.getSession().setAttribute("username", account.getUsername());
		request.getSession().setAttribute("rolename", account.getRolename());
		System.out.println(account.getUsername() + " / " + account.getRolename());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		statement.setString(1, username);
		statement.setString(2, password);
	}

	@Override
	protected Account doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		Account account = null;
		if (resultSet.next()) {
			String username = resultSet.getString("username");
			String rolename = resultSet.getString("rolename");
			account = new Account(username, rolename, null, null);
		} else {
			throw new Exception("password doesn't match");			
		}
		return account;
	}

}