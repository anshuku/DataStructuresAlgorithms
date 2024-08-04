package OOPSConcepts;

public class Parent {

	int value = 0;
	int anotherValue = 0;

	private int val = 0;

	protected int incrementVal(int i) {
		System.out.println("The parent method");
		return i + 1;
	}

	// private method cannot be overridden
	void says() {
		System.out.println("The parent method says");
	}

	// The static method of parent cannot be overridden in child
	public static int incrementVal(int i, int j) {
		System.out.println("The parent static method - incrementVal");
		return i + j;
	}

	public static void main(String[] args) {

		Parent p1 = new Parent();
		p1.val = 1;

		p1.says();
	}
}
