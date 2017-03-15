package fr.icam.emit.servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

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
		
		this.doPrint(this.read_file(response), response);
		
		
	}
	
	
	public String read_file(HttpServletResponse response){
		String s;
		s= "";
		 
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream("WEB-INF/classes/emit-1464192786377.json");
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
			String text = "";
			
			//hi
			// We read the file line by line and later will be displayed on the 
			// browser page.
			//
			try {
				while ((text = reader.readLine()) != null) {
					writer.println(text);
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
