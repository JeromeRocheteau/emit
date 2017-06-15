package fr.icam.emit.entities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class JsonSerialiser {
	
	public List<Serie> deserialise_Serie(HttpServletRequest request) throws IOException{
	Gson gson = new Gson();		
	InputStream inputStream = request.getInputStream();
	Reader reader = new InputStreamReader(inputStream);
	Type listType = new TypeToken<List<Serie>>(){}.getType();
	List<Serie> analysis = gson.fromJson(reader,listType);
	return analysis;
	}
	
	
	
}
