package fr.icam.emit.services.shares;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Remover extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201710171230009L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		String client = request.getParameter("client");
		statement.setString(1, client);
		statement.setString(2, user);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
