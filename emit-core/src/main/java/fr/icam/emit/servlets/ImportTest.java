package fr.icam.emit.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.servlet.jdbc.JdbcServlet;




public class ImportTest extends JdbcServlet{
private static final long serialVersionUID = 201703151635L;	
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String file_name = request.getParameter("file_name");
		this.doPrint(this.read_file(file_name), response);
		
		
	}
	
	
	public String read_file(String file_name){
		String s;
		s= "";
		String file_content = "";
		 
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream("WEB-INF/classes/"+file_name);
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
						
			try {
				while ((s = reader.readLine()) != null) {
					//writer.println(s);
					file_content = s+ file_content;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			file_content = "le fichier n'existe pas";
		}
		
	
		return file_content;
	};
}
