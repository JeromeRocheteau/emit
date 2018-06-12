package fr.icam.emit.services.commons;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcQueryServlet;

public class Sizer extends JdbcQueryServlet<Integer> {

	private static final long serialVersionUID = 201708211614001L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer size = this.doProcess(request);
		this.doWrite(size, response.getWriter());
	}
	
	@Override
	protected void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception { }

	@Override
	protected Integer doMap(HttpServletRequest request, ResultSet resultSet) throws Exception {
		int size = 0;
		while (resultSet.next()) {
			size = resultSet.getInt("size");
		}
		return size;
	}

}
