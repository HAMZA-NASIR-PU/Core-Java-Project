import java.util.Optional;

public class MyPractice {
	private int value;

	MyPractice(int value) {
		this.value = value;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		MyPractice m1 = new MyPractice(1);
		Optional<MyPractice> o1 = Optional.of(m1);
		System.out.println("Optional o1 value = " + o1);
		System.out.println("MyPractice m1 value = " + m1.value);

		// MyPractice m2 = null;
		// Optional<MyPractice> o2 = Optional.of(m2); //Immediately throws a
		// NullPointerException

		Optional<MyPractice> o3 = Optional.empty();
		System.out.println("Optional empty value = " + o3);

		MyPractice m3 = null;
		Optional<MyPractice> o4 = Optional.ofNullable(m3);
		System.out.println("MyPractice m3 value = " + m3);
		System.out.println("Optional o4 value = " + o4);
		MyPractice m4 = o4.orElse(new MyPractice(1000));
		System.out.println("MyPractice m4 value = " + m4.value);

		MyPractice m5 = new MyPractice(1000);
		Optional<MyPractice> o5 = Optional.ofNullable(m5);
		MyPractice m6 = o5.orElseThrow(() -> new ClassNotFoundException("orElseThrow method working..."));

		MyPractice m7 = null;
		Optional<MyPractice> o6 = Optional.ofNullable(m7);
		// MyPractice m8 = o6.orElseThrow(() -> new ClassNotFoundException("orElseThrow
		// method working..."));


		
		// Understanding Optional.map instance method signature
		// public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
		Optional<String> o7 = Optional.of("HELLO WORLD");
		// Optional<Integer> o8 = o7.<Integer>map(str -> str.length());
		Optional<Integer> o8 = o7.map(str -> str.length());
		//Both above are valid
		System.out.println("Length of " + o7.get() + " = " + o8.get());
	}
}