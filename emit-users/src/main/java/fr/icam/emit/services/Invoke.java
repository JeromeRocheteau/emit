package fr.icam.emit.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

public class Invoke extends JdbcServlet {

	private static final long serialVersionUID = 25L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	  try {
    	  this.doCall(request, response, "user-find");
      } catch (Exception e) {
    	  throw new ServletException("username", e);
      }
      try {
    	  this.doCall(request, response, "user-check");
      } catch (Exception e) {
    	  throw new ServletException("password", e);
      }
	  this.doCall(request, response, "pass-create");
	  String passphrase = (String) request.getAttribute("passphrase");
	  this.doWrite(passphrase, response.getWriter());
	}
	
}
