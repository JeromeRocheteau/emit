package fr.icam.emit.analysis;

public class TestCalculator {

	public static void main(String[] args) {

		Calculator my_calc = new Calculator("emit-1464192786377.json");
		double max =  my_calc.maximum();	
		double min =  my_calc.minimum();
		double StdDev =  my_calc.standardDeviation();
		double average =  my_calc.average();
		double integral =  my_calc.integral();
		
		System.out.println("max === " + max);
		System.out.println("min === " + min);
		System.out.println("StdDev === " + StdDev);
		System.out.println("average === " + average);
		System.out.println("integral " + integral);
	}
}
