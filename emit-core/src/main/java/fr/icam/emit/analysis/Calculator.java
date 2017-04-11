package fr.icam.emit.analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;


import com.github.jeromerocheteau.JdbcServlet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Calculator extends JdbcServlet {

	private static final long serialVersionUID = 201704110909L;
	private static List<Serie> serie;

	public Calculator(String fichier_source) {
		String input = read_file(fichier_source);
		serie = serialize_gson(input);
	}

	public Double average() {

		double sum = 0;

		for (int i = 0; i < serie.size(); i++) {
			sum += serie.get(i).getY();
		}

		double avg = sum / serie.size();
		return avg;
	}

	public Double standardDeviation() {
		int n = serie.size();
		if (n == 0)
			return 0.0;
		double sum = 0;
		double sq_sum = 0;
		for (int i = 0; i < n; ++i) {
			sum += serie.get(i).getY();
			sq_sum += serie.get(i).getY() * serie.get(i).getY();
		}
		double mean = sum / n;
		double variance = sq_sum / n - mean * mean;
		return Math.sqrt(variance);
	}

	public Double minimum() {

		double min = serie.get(0).getY();

		for (int i = 0; i < serie.size(); i++) {
			double number = serie.get(i).getY();
			if (number < min)
				min = number;
		}
		return min;
	}

	public Double maximum() {

		double max = 0;

		for (int i = 0; i < serie.size(); i++) {
			double number = serie.get(i).getY();
			if (number > max)
				max = number;
		}
		return max;
	}

	public Double integral() {

		List<Double> dy_array = new ArrayList<Double>();

		double dx = serie.get(1).getX() - serie.get(0).getX();

		for (int i = 0; i < serie.size(); i++) {
			dy_array.add(maximum() - serie.get(i).getY());
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
		System.out.println("input from serialize === " + input);
		Gson gson = new Gson();
		
		Type listType = new TypeToken<List<Serie>>() {
		}.getType();
		List<Serie> serie = gson.fromJson(input, listType);

		System.out.println("return serie === " + serie.get(1).getX());
		return serie;
	}

	public String read_file(String file_name) {
		// String s;
		// s = "";
		String file_content = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/webapp/WEB-INF/classes/" + file_name));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			file_content = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("return file_content === " + file_content);
		return file_content;
	};
}