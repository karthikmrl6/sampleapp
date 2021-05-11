package academy.learnprog;
public class Singleton {
	private static Singleton single_instance = null;
	// variable of type String
	public String s;

	// private constructor restricted to this class itself
	private Singleton() {
		System.out.println("calling constructor");
		s = "Hello I am a string part of Singleton class";
	}

	// static method to create instance of Singleton class
	public static Singleton getInstance() {
		if (single_instance == null) {
			synchronized (Singleton.class) {
				single_instance = new Singleton();
			}
		}
		return single_instance;
	}
	// https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/
}
