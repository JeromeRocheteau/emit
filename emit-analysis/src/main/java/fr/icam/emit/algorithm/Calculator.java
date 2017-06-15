package fr.icam.emit.algorithm;

import java.util.ArrayList;
import java.util.List;

import fr.icam.emit.entities.Serie;


// bonjour
public class Calculator {
	
	private List<Serie> serie;

	public Calculator(List<Serie> serie) {		
		this.serie = serie;
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
		double sum = 0;
		double sq_sum = 0;
		
		if (n == 0)
			return 0.0;
		
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

}
