package fr.icam.emit.services;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class PassDelete extends JdbcUpdateServlet<Boolean> {

	private static final long serialVersionUID = 24L;

	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String passphrase = request.getParameter("passphrase");
		statement.setString(1, passphrase);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean deleted = this.doProcess(request);
		request.setAttribute("deleted", deleted);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Boolean deleted = this.doProcess(request);
		request.setAttribute("deleted", deleted);
	}

	@Override
	protected Boolean doMap(HttpServletRequest request, int count, ResultSet resultset) throws Exception {
		return count > 0;
	}
	
}
