package fr.icam.emit.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.analysis.File_handler;
import fr.icam.emit.analysis.InstrumentReader;
import fr.icam.emit.api.Meter;

public class TestEnregistrement extends JdbcServlet{
	/*
	 * 
	 */
	
	private final static String HOST = "http://172.16.220.250:8088/arduinometer/";
	private static final int PROC_TIME = 3000; 
	private static final long serialVersionUID = 2017041201530L;



	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		String content = "";
		try {
			content = this.get_arduino_file();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File_handler handler = new File_handler();
		handler.write_file("arduino-file.csv", content);		
		response.getWriter().write("done");
		*/
		String content = "";
		File_handler handler = new File_handler();
		try {
			content = handler.read_file("arduino-file.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.getWriter().write(content);
		InstrumentReader reader = new InstrumentReader();
		reader.Read(content);
		reader.Afficher(response);
		
		
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
	
	public String read_from_server() throws IOException{
		String result = "";
		BufferedReader br = new BufferedReader(new FileReader("/var/lib/emit/emit-1493121766097.csv"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    result = sb.toString();
		} 
		finally {
		    br.close();
		}
		return result;
	}
	
	public void Afficher(List<List<String>> lines,HttpServletResponse response ) {

		int lineNo = 1;
		for (List<String> line : lines) {
			int columnNo = 1;
			for (String value : line) {
				try {
					response.getWriter().println("Line " + lineNo + " Column " + columnNo + ": " + value);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				columnNo++;
			}
			lineNo++;
		}

	}
	
	public String get_arduino_file() throws Exception{	
		
		URI uri = URI.create(HOST);
    	Meter app = new Meter(uri);
    	long time = Calendar.getInstance().getTimeInMillis();
    	long stop = time;		
    	app.doStart();
    	
    	while ((time - stop) < PROC_TIME) {
    		time = Calendar.getInstance().getTimeInMillis();
    	}
    	
    	app.doStop();
    	
    	String reponce =app.doRetrieve(System.out);    	
		return reponce;
	}
	
	
	
	
}
