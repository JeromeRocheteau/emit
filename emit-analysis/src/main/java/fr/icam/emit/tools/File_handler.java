package fr.icam.emit.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class File_handler {
	
public File_handler(){
		
	}
	
	public void write_file(String file_name,String content){
		
		
		try{
		    PrintWriter writer = new PrintWriter("/var/lib/emit/"+file_name, "UTF-8");
		    writer.print(content);		   
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	public String read_file(String file_name) throws Exception{
		String content = "";
		BufferedReader br = new BufferedReader(new FileReader("/var/lib/emit/"+file_name));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    content = sb.toString();
		} 
		finally {
		    br.close();
		}
		
		return content;
		
	}

}
