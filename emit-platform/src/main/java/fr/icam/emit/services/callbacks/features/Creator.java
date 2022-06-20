package fr.icam.emit.services.callbacks.features;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Creator extends JdbcUpdateServlet<Long> {

	private static final long serialVersionUID = 201711160900003L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "callback-create");
		Long id = this.doProcess(request);
		this.doWrite(id, response.getWriter());
	}

	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = (Long) request.getAttribute("id");
		String symbol = request.getParameter("symbol");
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		statement.setLong(1, id);
		statement.setString(2, symbol);
		statement.setString(3, type);
		statement.setString(4, value);
	}
	
	@Override
	protected Long doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return (Long) request.getAttribute("id");
	}

}
