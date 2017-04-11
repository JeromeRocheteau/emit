package fr.icam.emit.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import com.github.jeromerocheteau.JdbcServlet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Calculator extends JdbcServlet{
	
	private static final long serialVersionUID = 201704110909L;
	private static List<Serie> serie;

	public Calculator(String fichier_source) {
		String input = read_file(fichier_source);
		serie = serialize_gson(input);
	}

	public static Double average() {

		double sum = 0;

		for (int i = 0; i < serie.size(); i++) {
			sum += serie.get(i).getValue();
		}

		double avg = sum / serie.size();
		return avg;
	}

	public static Double standardDeviation() {
		int n = serie.size();
		if (n == 0)
			return 0.0;
		double sum = 0;
		double sq_sum = 0;
		for (int i = 0; i < n; ++i) {
			sum += serie.get(i).getValue();
			sq_sum += serie.get(i).getValue() * serie.get(i).getValue();
		}
		double mean = sum / n;
		double variance = sq_sum / n - mean * mean;
		return Math.sqrt(variance);
	}

	public static Double minimum() {
		
		double min = 0;
		
		for (int i = 0; i < serie.size(); i++) {
			double number = serie.get(i).getValue();
			if (number < min)
				min = number;
		}
		return min;
	}

	public static Double maximum() {
		
		double max = 0;
		
		for (int i = 0; i < serie.size(); i++) {
			double number = serie.get(i).getValue();
			if (number < max)
				max = number;
		}
		return max;
	}

	public static Double integral() {

		List<Double> dy_array = new ArrayList<Double>(); 

		double dx = serie.get(1).getTimestamp() - serie.get(0).getTimestamp();
		
		for(int i = 0; i < serie.size(); i++) {
			dy_array.add(maximum() - serie.get(i).getValue());
		}
		
		for(int i = 0; i < dy_array.size(); i++) {
			dy_array.add(maximum() - serie.get(i).getValue());
		}
		
	    double profile_integral = 0;
	    
	    int n = dy_array.size();
	    
	    for (int i = 1; i < n; i++) {
	        double dy_init = dy_array.get(i);
	        double dy_end = dy_array.get(i);
	        double darea = dx * (dy_init + dy_end) / 2;
	        profile_integral = profile_integral + darea;
	    }
	    return profile_integral;
	}

	public List<Serie> serialize_gson(String input) {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Serie>>() {
		}.getType();
		List<Serie> serie = gson.fromJson(input, listType);
		return serie;
	}

	public String read_file(String file_name) {
		String s;
		s = "";
		String file_content = "";

		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream("WEB-INF/classes/" + file_name);
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);

			try {
				while ((s = reader.readLine()) != null) {
					// writer.println(s);
					file_content = s + file_content;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			file_content = "le fichier n'existe pas";
		}

		return file_content;
	};
}