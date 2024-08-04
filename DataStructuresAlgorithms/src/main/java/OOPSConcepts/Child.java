package OOPSConcepts;

public class Child extends Parent {

	int newVal = 0;

	int anotherNewVal = 0;

	// can only increase the visibility of inherited method
	public int incrementVal(int i) {
		System.out.println("The child method - incrementVal");
		return i + 1;
	}

	// the private method cannot be overridden
	public void says() {
		System.out.println("The child method says");
	}

	// static method can be overloaded with non static method
	// no compile time error even though parent already have the same method
	public static int incrementVal(int i, int j) {
		System.out.println("The child static method - incrementVal");
		return i + 1;
	}

	// instance method cannot override the static method from parent - compile time
	// error
//	public int incrementVal(int i, int j) {
//		System.out.println("The child non static method - incrementVal");
//		return i + 1;
//	}

	// static methods can be overloaded
	private static int incrementValue(int i) {
		System.out.println("The child static method - incrementVal 1");
		return i + 1;
	}

	private static int incrementValue(int i, int j) {
		System.out.println("The child static method - incrementVal 2");
		return i + j;
	}

	public void value(String s) {
		System.out.println("The child has a string value");
	}

	public void value(Object o) {
		System.out.println("The child has an object value");
	}

	public static void main(String[] args) {

		Parent p = new Child();

		// private method of parent can't be accessed from child object.
		p.says();

		p.incrementVal(1);

		p.incrementVal(0, 1);

		Child c = new Child();

		c.says();

		c.incrementVal(0, 1);

		c.incrementVal(1);

		c.incrementValue(1);

		c.incrementValue(0, 1);

		c.value("S");

		c.value(1);

		c.value(1.0);

		c.value(true);
	}

}
