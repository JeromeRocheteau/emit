package fr.icam.emit.services.callbacks.guards;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Updater extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201711131345004L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Integer count = this.doProcess(request);
		this.doWrite(count, response.getWriter());
	}

	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		Long id = Long.valueOf(request.getParameter("id"));
		Long test = Long.valueOf(request.getParameter("test"));
		Long success = Long.valueOf(request.getParameter("success"));
		String fail = request.getParameter("failure");
		statement.setLong(1, test);
		statement.setLong(2, success);
		if (fail == null || fail.isEmpty()) {
			statement.setNull(3, Types.BIGINT);
		} else {
			Long failure = Long.valueOf(fail);
			statement.setLong(3, failure);
		}
		statement.setLong(4, id);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		return count;
	}

}
