package scpbasicteam;

/**
 * MyClass - a class by (your name here)
 */
public class Enemy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	double x;
	double y;
	String name;
	boolean alive;

	public Enemy(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
		alive = true;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	public String getName(){
		return name;
	}
	boolean isAlive(){
		return alive;
	}
}