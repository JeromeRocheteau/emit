package fr.icam.emit.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InstrumentReader {

	public InstrumentReader() {
	}
	
	List<List<String>> lines = new ArrayList<List<String>>();

	public List<List<String>> Read(String data) {
		String my_new_str = data.replaceAll("	", ";");
		Scanner inputStream = new Scanner(my_new_str);
		while (inputStream.hasNext()) {

			String line = inputStream.next();
			String[] values = line.split(";");
			lines.add(Arrays.asList(values));
		}
		inputStream.close();
		return lines;
	}

	public void Afficher() {

		int lineNo = 1;
		for (List<String> line : lines) {
			int columnNo = 1;
			for (String value : line) {
				System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
				columnNo++;
			}
			lineNo++;
		}

	}
}