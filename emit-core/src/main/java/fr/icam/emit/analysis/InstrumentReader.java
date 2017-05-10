package fr.icam.emit.analysis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class InstrumentReader {

	public InstrumentReader() {
		
		
	}
	
	List<List<String>> lines = new ArrayList<List<String>>();

	public List<List<String>> Read(String data) {
		String my_new_str = data.replaceAll("	", ";");
		my_new_str = my_new_str.replaceAll(",", ".");
		Scanner inputStream = new Scanner(my_new_str);
		
		while (inputStream.hasNext()) {

			String line = inputStream.next();
			String[] values = line.split(";");
			lines.add(Arrays.asList(values));
		}
		inputStream.close();
		return lines;
	}

	public void Afficher(HttpServletResponse response) throws IOException {

		int lineNo = 1;
		for (List<String> line : lines) {
			int columnNo = 1;
			//response.getWriter().write("Line " + lineNo + "timestamp" +line.get(lineNo));
			
			
			for (String value : line) {
				response.getWriter().write("Line " + lineNo + " Column " + columnNo + ": " + value);			
				columnNo++;
			
			}
			response.getWriter().println("");
			lineNo++;
		}

	}
	
	public String create_json(List<Serie> data){
		Gson gson = new Gson();
		String json = gson.toJson(data);
		return json;
	}
	
	
}