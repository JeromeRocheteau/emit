package fr.icam.emit.analysis;

public class Serie {

	private double x;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	private double y;

	public Serie(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
}
