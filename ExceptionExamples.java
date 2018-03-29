/**
 * The APCS exam tests for the following exceptions.
 *	- ArithmeticException
 *	- NullPointerException
 *	- IndexOutOfBoundsException
 *	- ArrayIndexOutOfBoundsException
 *	- IllegalArgumentException
 */
 

public class ExceptionExamples {
	public static void main(String[] args) {
		ExceptionExamples ee = new ExceptionExamples();
		ee.run();
	}
	
	public void run() {
		arithmetic();
		nullPointer();
		indexBounds();
		arrayIndexBounds();
		illegalArgument();
	}
	
	public void arithmetic() {
		int x = 1;
		int y = 0;
		try {int z = x / y;}
			catch(ArithmeticException e) {
				System.err.println("ERROR: ArithmeticException");
			}
	}
	public void nullPointer() {
		Integer[]arr = new Integer[10];
		try {
			int a = arr[0].intValue();
		}
		catch (NullPointerException e) {
			System.err.println("ERROR: NullPointerException");
		}
	}
	
	public void indexBounds() {
		String hello = "Hello";
		try {
			char c = hello.charAt(hello.length());
		}
		catch (IndexOutOfBoundsException e) {
			System.err.println("ERROR: IndexOutOfBoundsException");
		}
	}
	
	public void arrayIndexBounds() {
		Integer[]arr = new Integer[10];
		try {
			int k = arr[10];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("ERROR: ArrayIndexOutOfBoundsException");
		}
	}
	
	public void illegalArgument() {
		String str = "hello";
		try {
			System.out.printf("%d\n", str);
		}
		catch (IllegalArgumentException e){
			System.err.println("ERROR: IllegalArgumentException");
		}
	}
	
}