package fr.icam.emit.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcUpdateServlet;



public class ImportTest extends JdbcUpdateServlet<Boolean>{
private static final long serialVersionUID = 201703141635L;


	
	

	
	@Override
	protected Boolean doMap(HttpServletRequest request, int count) throws Exception {
		
		return count > 0;
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String file_name = request.getParameter("file_name");
		this.doPrint(this.read_file(response,file_name), response);
		
		
	}
	
	
	public String read_file(HttpServletResponse response,String file_name){
		String s;
		s= "";
		 
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream("WEB-INF/classes/"+file_name);
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//hi
			// We read the file line by line and later will be displayed on the 
			// browser page.
			//
			try {
				while ((s = reader.readLine()) != null) {
					writer.println(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			s = context.toString();
		}
		
	
		return s;
	};
}
