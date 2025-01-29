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

### Java Predicate Interface Examples

1. Predicate Chaining with "and" Method

```java
import java.util.function.Predicate;

public class PredicateChainingWithAndExample {
    public static void main(String[] args) {
        Predicate<String> isNonEmpty = str -> !str.isEmpty();
        Predicate<String> startsWithA = str -> str.startsWith("A");

        Predicate<String> combinedPredicate = isNonEmpty.and(startsWithA);

        System.out.println(combinedPredicate.test("Apple")); // true
        System.out.println(combinedPredicate.test("")); // false
        System.out.println(combinedPredicate.test("Banana")); // false
    }
}
```

2.  Predicate Chaining with or Method

```java
import java.util.function.Predicate;

public class PredicateChainingWithOrExample2 {
    public static void main(String[] args) {
        Predicate<String> lengthGreaterThanFive = str -> str.length() > 5;
        Predicate<String> endsWithZ = str -> str.endsWith("z");

        Predicate<String> combinedPredicate = lengthGreaterThanFive.or(endsWithZ);

        System.out.println(combinedPredicate.test("hello")); // false (length <= 5 and does not end with 'z')
        System.out.println(combinedPredicate.test("worldz")); // true (ends with 'z')
        System.out.println(combinedPredicate.test("java")); // false (length <= 5 and does not end with 'z')
        System.out.println(combinedPredicate.test("amazing")); // true (length > 5)
        System.out.println(combinedPredicate.test("buzz")); // true (ends with 'z')
    }
}
```

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Fail Fast and Fail Safe Iterators in Java

Using iterations we can traverse over the collections objects. The iterators can be either fail-safe or fail-fast. Fail-safe iterators means they will not throw any exception even if the collection is modified while iterating over it. Whereas Fail-fast iterators throw an exception(ConcurrentModificationException) if the collection is modified while iterating over it.

Consider an example:

```java
ArrayList<Integer> integers = new ArrayList<>();
integers.add(1);
integers.add(2);
integers.add(3);
Iterator<Integer> itr = integers.iterator();
while (itr.hasNext()) {
    Integer a = itr.next();
    integers.remove(a);
}
```

As arrayLists are fail-fast above code will throw an exception.
First a will have value = 1, and then 1 will be removed in same iteration.
Next when a will try to get next(), as the modification is made to the list, it will throw an exception here.


However if we use an fail-safe collection e.g. CopyOnWriteArrayList then no exception will occur:

```java
List<Integer> integers = new CopyOnWriteArrayList<>();
integers.add(1);
integers.add(2);
integers.add(3);
Iterator<Integer> itr = integers.iterator();
while (itr.hasNext()) {
    Integer a = itr.next();
    integers.remove(a);
}
```
Here if we print the element a, then all the elements will be printed.

```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FailFastExample {
    public static void main(String[] args)
    {
        Map<String, String> cityCode
            = new HashMap<String, String>();
        cityCode.put("Delhi", "India");
        cityCode.put("Moscow", "Russia");
        cityCode.put("New York", "USA");

        Iterator iterator = cityCode.keySet().iterator();

        while (iterator.hasNext()) {
            System.out.println(
                cityCode.get(iterator.next()));

            // adding an element to Map
            // exception will be thrown on next call
            // of next() method.
            cityCode.put("Istanbul", "Turkey");
        }
    }
}
```

#### Very interesting Code

```java
// Java code to illustrate
// Fail Fast Iterator in Java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Main {
    public static void main(String[] args)
    {
        Map<String, String> cityCode
            = new HashMap<String, String>();
        cityCode.put("Delhi", "India");
        cityCode.put("Moscow", "Russia");
        cityCode.put("New York", "USA");

        Iterator iterator1 = cityCode.keySet().iterator();
        
        cityCode.put("Istanbul", "Turkey");
        
        // This line throws java.util.ConcurrentModificationException exception.
        // If you comment that line, then no exception is thrown.
        // System.out.println("Using iterator 1:" + cityCode.get(iterator1.next()));
        
        Iterator iterator2 = cityCode.keySet().iterator();
        System.out.println("Using iterator 2:" + cityCode.get(iterator2.next()));

        // while (iterator.hasNext()) {
        //     System.out.println(
        //         cityCode.get(iterator.next()));

        //     // adding an element to Map
        //     // exception will be thrown on next call
        //     // of next() method.
        //     cityCode.put("Istanbul", "Turkey");
        // }
    }
}
```
### Important points of fail-fast iterators :

- These iterators throw ConcurrentModificationException if a collection is modified while iterating over it.
- They use original collection to traverse over the elements of the collection.
- These iterators don’t require extra memory.
- Ex : Iterators returned by ArrayList, Vector, HashMap.


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> What's the point of having both Iterator.forEachRemaining() and Iterable.forEach()?

To understand why the two methods both exist, you need to first understand what are Iterator and Iterable.
An Iterator basically is something that has a "next element" and usually, an end.
An Iterable is something that contains elements in a finite or infinite sequence and hence, can be iterated over by keep getting the next element. In other words, Iterables can be iterated over by Iterators.
Now that you understand this, I can talk about what's the difference between the two methods in question.
Let's use an array list as an example. This is what is inside the array list:
```sql
[1, 3, 6, 8, 0]
```
Now if I call Iterable.forEach() and pass in System.out::print(), 13680 will be printed. This is because Iterable.forEach iterates through the whole sequence of elements.
On the other hand, if I get the Iterator of the array list and called next twice, before calling forEachRemaining with System.out::print(), 680 will be printed. The Iterator has already iterated through the first two elements, so the "remaining" ones are 6, 8 and 0.

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> How to throw `java.util.NoSuchElementException`

```java
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("A", "1");
        mp.put("B", "2");
        mp.put("C", "3");
        
        Iterator it = mp.keySet().iterator();
        
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        // Throws java.util.NoSuchElementException
        System.out.println(it.next());
        
    }
}
```
