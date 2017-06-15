package fr.icam.emit.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.jeromerocheteau.JdbcServlet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fr.icam.emit.algorithm.Calculator;
import fr.icam.emit.entities.Serie;

public class ReturnMin extends JdbcServlet{
	
	private static final long serialVersionUID = 201705121446L;

		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Gson gson = new Gson();		
		InputStream inputStream = request.getInputStream();
		Reader reader = new InputStreamReader(inputStream);
		Type listType = new TypeToken<List<Serie>>(){}.getType();
		List<Serie> serie = gson.fromJson(reader,listType);	
		
		Calculator calculator = new Calculator(serie);
		double value = calculator.minimum();
		response.getWriter().print(value);
	}
}
