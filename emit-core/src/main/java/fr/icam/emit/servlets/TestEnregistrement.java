package fr.icam.emit.servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;

import fr.icam.emit.analysis.InstrumentReader;
import fr.icam.emit.analysis.Serie;

public class TestEnregistrement extends JdbcServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2017041201530L;



	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//String data = this.read_file("emit-1464192786377.csv");
		/*
		try{
		    PrintWriter writer = new PrintWriter("/var/lib/emit/the-file-name.txt", "UTF-8");
		    writer.println("The first line");
		    writer.println("The second line");
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
		*/
		String message = "false";
	
		File f = new File("/var/lib/emit/emit-1493121766097.csv");
		if (f.exists() /*&& f.isDirectory()*/) {		
		   message = "true";
		}
		
		

		
		
		/*
		List<String> lines = Arrays.asList("var/lib/emit/The first line", "The second line");
		Path file = Paths.get("var/lib/emit/the-file-name.txt");
		Files.write(file, lines, Charset.forName("UTF-8"));
		*/
		String message2 = this.read_from_server();
		InstrumentReader reader = new InstrumentReader();
		List<List<String>> list = new ArrayList<List<String>>();
		List<Serie> data = new ArrayList<Serie>();
		list = reader.Read(message2);
		//this.Afficher(list, response);
		for (int i = 2; i<list.size();i++){
			data.add(new Serie( Double.parseDouble(list.get(i).get(0)),Double.parseDouble(list.get(i).get(1))));
		}
		
		String json = reader.create_json(data);
		response.getWriter().write(json);
		//response.getWriter().write(message);
		//response.getWriter().write(message2);
		//response.getWriter().write(message);
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
					response.getWriter().write("Line " + lineNo + " Column " + columnNo + ": " + value);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				columnNo++;
			}
			lineNo++;
		}

	}
	
	
}
