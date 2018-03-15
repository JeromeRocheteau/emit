package fr.icam.emit.services.callbacks;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcUpdateServlet;

public class Creator extends JdbcUpdateServlet<Integer> {

	private static final long serialVersionUID = 201711101212003L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* Integer count = */ this.doProcess(request);
		// this.doWrite(count, response.getWriter());
	}

	@Override
	public void doFill(PreparedStatement statement, HttpServletRequest request) throws Exception {
		String user = request.getUserPrincipal().getName();
		String name = request.getParameter("name");
		Boolean atomic = Boolean.valueOf(request.getParameter("atomic"));
		String category = request.getParameter("category");
		statement.setString(1, name);
		statement.setBoolean(2, atomic);
		statement.setString(3, category);
		statement.setString(4, user);
	}
	
	@Override
	protected Integer doMap(HttpServletRequest request, int count, ResultSet resultSet) throws Exception {
		if (resultSet.next()) {
			Long id = resultSet.getLong(1);
			request.setAttribute("id", id);
		}
		return count;
	}

}
