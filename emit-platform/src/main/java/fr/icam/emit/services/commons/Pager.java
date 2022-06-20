package fr.icam.emit.services.commons;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public abstract class Pager<T> extends JdbcQueryServlet<List<T>> {

	private static final long serialVersionUID = 201708211614003L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<T> items = this.doProcess(request);
		this.doWrite(items, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Integer offset = Integer.valueOf(request.getParameter("offset"));
		Integer length = Integer.valueOf(request.getParameter("length"));
		statement.setInt(1, offset);
		statement.setInt(2, length);
	}

}
