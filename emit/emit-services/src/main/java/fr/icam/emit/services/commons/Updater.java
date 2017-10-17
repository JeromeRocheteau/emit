package fr.icam.emit.services.commons;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Updater extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201708211614008L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}
	
	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		statement.setLong(1, id);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
