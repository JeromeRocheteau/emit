package fr.icam.emit.services.callbacks.guards;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Creator extends JdbcUpdateServlet<Long> {

	private static final long serialVersionUID = 201711131345003L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doCall(request, response, "callback-create");
		Long id = this.doProcess(request);
		this.doWrite(id, response.getWriter());
	}

	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = (Long) request.getAttribute("id");
		Long test = Long.valueOf(request.getParameter("test"));
		Long success = Long.valueOf(request.getParameter("success"));
		String fail = request.getParameter("failure");
		statement.setLong(1, id);
		statement.setLong(2, test);
		statement.setLong(3, success);
		if (fail == null || fail.isEmpty()) {
			statement.setNull(4, Types.BIGINT);
		} else {
			Long failure = Long.valueOf(fail);
			statement.setLong(4, failure);
		}
	}
	
	@Override
	protected Long doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return (Long) request.getAttribute("id");
	}

}
