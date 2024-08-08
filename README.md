# The Ultimate Core Java Question Repository

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> What is Predicate in Java 8?

The Predicate in Java 8 is one of the types of Functional Interfaces. Functional Interfaces were introduced in Java 8 to support functional programming in the object-oriented world of Java. The Predicate is one of them, which allows programmers to test a specific condition imposed on values or objects. Such a condition will always return a boolean value.
To understand better what is Predicate interface, you must first understand what the word "predicate" mean. The term "predicate" implies a statement that evaluates whether a value is true or false.
The next thing that you need to understand is Functional Interface. Functional Interface is nothing but just an interface with a "single abstract method", also commonly termed as SAM. They are annotated with @FuctionalInterface annotation. These interfaces also act as a target type for lambda expressions.

`Note: Abstract methods are common to interfaces and abstract classes. Such methods don't require implementation for their declaration. They are defined using the abstract keyword, but in interfaces, all methods are implicitly abstract if they are not declared as default or static. So it is optional to use the abstract keyword for interface methods.`

```java
@FuncionalInterface
public interface Predicate<T> {
    ...
    boolean test(T t);
    
    // rest methods
    ...
}
```

Hence, we need to override the test predicate method in Java 8 to utilize them. The below example shows how:

```java
import java.util.function.Predicate;

public class MyPredicate implements Predicate<Integer> {
	@Override
	public boolean test(Integer t) {
		return t >= 20 && t < 25;
	}

	public static void main(String[] args) {
		MyPredicate myPredicate = new MyPredicate();

		System.out.println(myPredicate.test(10)); // false
		System.out.println(myPredicate.test(22)); // true

		// another way to use predicates is by lambda expressions
		Predicate<String> isContainsA = str -> str.contains("A") || str.contains("a");
		System.out.println(isContainsA.test("abc")); // true
		System.out.println(isContainsA.test("ABC")); // true
	}
}

```

`Explanation: You can create your class by implementing the Predicate interface and overriding the test method or create a more concise code by lamdba expressions. Notice the target assignment of the lambda expression is Predicate<Integer> as it runs a test condition on an integer value.`
