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
        
        Iterator<String> it = mp.keySet().iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        it.forEachRemaining(el -> System.out.println(el));
    }
}
```

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


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Which Java Collection Interfaces Can Stream Like a Pro?

In Java, the **`Stream`** interface is part of the `java.util.stream` package, introduced in **Java 8**. It is **not** implemented by any collection interfaces directly. However, some collection interfaces provide methods that return a **Stream**.

### 1. **Collection Interface (`java.util.Collection`)**
   - The **`Collection`** interface (super interface for `List`, `Set`, and `Queue`) **does not implement `Stream`**, but it provides a **`stream()`** method:
     ```java
     default Stream<E> stream()
     ```
     This allows any collection to generate a stream.

### 2. **Implementations That Provide Streams**
   Since `Collection` provides `stream()`, all of its subinterfaces (e.g., `List`, `Set`, `Queue`) can produce a `Stream`. This includes:
   - `List` (e.g., `ArrayList`, `LinkedList`)
   - `Set` (e.g., `HashSet`, `TreeSet`)
   - `Queue` (e.g., `PriorityQueue`, `ArrayDeque`)

   **Example:**
   ```java
   List<String> list = Arrays.asList("A", "B", "C");
   Stream<String> stream = list.stream();
   ```

### 3. **`Map` Does Not Extend `Collection`**
   - `Map<K, V>` (e.g., `HashMap`, `TreeMap`) does **not** extend `Collection`, so it does **not** have a `stream()` method.
   - However, you can stream its **keys**, **values**, or **entries**:
     ```java
     Map<Integer, String> map = new HashMap<>();
     map.put(1, "One");
     map.put(2, "Two");

     Stream<Integer> keyStream = map.keySet().stream();
     Stream<String> valueStream = map.values().stream();
     Stream<Map.Entry<Integer, String>> entryStream = map.entrySet().stream();
     ```

### **Conclusion**
- **No collection interface directly implements `Stream`.**
- **`Collection` and its subinterfaces provide the `stream()` method.**
- **`Map` does not implement `Collection`, but its keys, values, and entries can be streamed.**

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> List of classes that implements the java.util.Collection interface


Several classes in Java implement the `java.util.Collection` interface.  Here's a list of some of the most common ones, categorized for clarity:

**Core Interface Implementations (Abstract Data Types):**

* **`java.util.ArrayList`:** A dynamic array implementation.  Good for general-purpose use, fast access by index.
* **`java.util.LinkedList`:** A doubly-linked list implementation.  Good for frequent insertions and deletions, but slower random access.
* **`java.util.HashSet`:** Implements the `Set` interface (which extends `Collection`).  Stores unique elements in no particular order.  Uses hashing for fast lookups.
* **`java.util.LinkedHashSet`:**  A `HashSet` that maintains the order in which elements were inserted.
* **`java.util.TreeSet`:** Implements the `SortedSet` interface (which extends `Set`). Stores elements in a sorted order (either natural ordering or according to a `Comparator`).
* **`java.util.ArrayDeque`:** Implements the `Deque` interface (which extends `Collection`).  A double-ended queue.  Can be used as a stack or a queue.

**Classes related to Maps (Note: Maps themselves do *not* implement Collection, but their *values* or *entrySet* can be viewed as Collections):**

While `Map` itself doesn't implement `Collection`, you can get `Collection` views of its data:

* `map.values()`: Returns a `Collection` view of the values in the map.
* `map.entrySet()`: Returns a `Set` view of the key-value pairs (entries) in the map.  Each entry is a `Map.Entry` object.
* `map.keySet()`: Returns a `Set` view of the keys in the map.

**Other Collection-related Classes (Less Frequently Used Directly):**

* `java.util.Vector` (Legacy):  An older, synchronized version of `ArrayList`.  Generally, `ArrayList` is preferred unless thread safety is explicitly required.  For thread safety, `Collections.synchronizedList()` or `CopyOnWriteArrayList` are often better choices.
* `java.util.Stack` (Legacy):  An older class that extends `Vector` and represents a LIFO (Last-In, First-Out) stack. `ArrayDeque` is usually preferred for stack implementations now.
* `java.util.PriorityQueue`:  A queue where elements are ordered according to their priority (using a `Comparator` or natural ordering).

**Important Note about Interfaces:** Remember that `Set`, `SortedSet`, `NavigableSet`, `Queue`, `Deque`, and `List` are *interfaces* that *extend* the `Collection` interface.  The classes listed above are concrete implementations of these interfaces.  For example, `HashSet` *implements* the `Set` interface, which in turn *extends* the `Collection` interface.


## Java Stream Question (Just for Muscle Flexing)

You have a list of integers. Write a code to filter out even numbers, multiply by each by 2, and collect the result into a new list.
Follow-up: How would you approach change if the list contains duplicate integers and the result should contain only unique values ?

``` java
import java.util.List;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // distinct is for removing the duplicates in the array.
        List<Integer> result = nums.stream()
                .distinct()
                .filter(num -> num % 2 == 0)
                .map(num -> num * 2)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Mastering Generics in Java: A Comprehensive Guide to Type-Safe and Flexible Code

Generics enable types (classes and methods) to operate on objects of various types while providing compile-time type safety. They allow you to write flexible, reusable code that works with different data types without sacrificing type safety.

Here’s a breakdown of the generics concept and how it's used in Java:

### 1. **Generic Classes**
Generic classes allow you to define a class with type parameters. These parameters can then be used as placeholders for specific types that are defined when an instance of the class is created.

#### Example:

```java
public class Box<T> {
    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}
```

In the example:
- `T` is a type parameter (a placeholder for the actual type).
- When you create an instance of `Box`, you can specify the type:

```java
Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String value = stringBox.get();  // No need to cast
```

### 2. **Generic Methods**
A method can also have its own type parameters, which are independent of any generic class it might belong to. This is the concept used in the `map` method you saw in the `Stream<T>` interface.

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

#### Example:

```java
public class Util {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
}
```

Here:
- `<T>` is a generic type parameter for the method `printArray`.
- You can call this method with any type of array:

```java
Integer[] intArray = {1, 2, 3};
String[] strArray = {"A", "B", "C"};

Util.printArray(intArray);
Util.printArray(strArray);
```

### 3. **Bounded Type Parameters**
You can restrict the types that can be used as arguments for generic classes or methods by using bounded type parameters. For example, you might want to ensure that a type is a subclass of a particular class or implements a particular interface.

#### Example (Upper Bound):

```java
public <T extends Number> void process(T number) {
    System.out.println(number.doubleValue());
}
```

In this method:
- `<T extends Number>` means `T` must be a subclass of `Number` (e.g., `Integer`, `Double`).
- You can call this method with any type that extends `Number`, such as `Integer`, `Float`, or `Double`.

#### Example (Lower Bound with Wildcards):

```java
public static void printNumbers(List<? super Integer> list) {
    for (Object obj : list) {
        System.out.println(obj);
    }
}
```

- `? super Integer` is a lower-bounded wildcard, meaning the list can contain `Integer` or any superclass of `Integer` (such as `Number` or `Object`).

### 4. **Generic Interfaces**
Just like classes, interfaces can also be generic. A common example is the `Comparable<T>` interface, which allows you to define a comparison method for a specific type.

#### Example:

```java
public class Person implements Comparable<Person> {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
}
```

Here, `Person` implements `Comparable<Person>`, so the `compareTo` method will compare two `Person` objects.

### 5. **Wildcards in Generics**
Wildcards allow you to specify a range of types when working with generics. There are three types of wildcards:
- **Unbounded wildcard (`?`)**: Can be any type.
- **Upper-bounded wildcard (`? extends Type`)**: Can be any type that is a subtype of `Type`.
- **Lower-bounded wildcard (`? super Type`)**: Can be any type that is a supertype of `Type`.

#### Example of Upper-Bounded Wildcard:

```java
public static void printList(List<? extends Number> list) {
    for (Number number : list) {
        System.out.println(number);
    }
}
```

This method accepts a list of `Number` or any of its subclasses (e.g., `Integer`, `Double`).

### Why Learn Generics?
1. **Type Safety**: Generics help catch type-related errors at compile-time, preventing `ClassCastException` at runtime.
2. **Reusability**: You can write code that works with any type, making your code more flexible and reusable.
3. **Readability**: Generics make the intent of your code clearer by specifying exactly what types of objects a class or method can work with.

### Resources to Learn More:
- **Official Documentation**: [Java Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
- **Books**: "Effective Java" by Joshua Bloch has a great section on generics.
- **Practice**: Websites like [LeetCode](https://leetcode.com/) or [HackerRank](https://www.hackerrank.com/domains/tutorials/10-days-of-java) provide exercises that involve generics.

Generics are foundational in modern Java programming, and understanding them will greatly improve your ability to write efficient, type-safe, and maintainable code.

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Understanding variance in Java

In the context of Java generics:

- `? extends T` means "any type that is a subtype of T" (covariant).
- `? super T` means "any type that is a supertype of T" (contravariant).

### Best Example of Understanding that concept:

#### Example 1

```java
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.stream.Collectors;
class Main {
    public static void main(String[] args) {
        Stream<Number> numberStream = Stream.of(1, 2L);
        Function<Number, Integer> function = n -> Integer.valueOf(n.intValue());
        Stream<Number> numberStream2 = numberStream.map(function);
        System.out.println(numberStream2.collect(Collectors.toList()));
    }
}
```

### Example 2: Without Covariance (`? extends T`)
Imagine you're trying to read items from a list of numbers (`Integer`), but you're passing a list of a more general type (`Number`). Without using `? extends T`, you would run into a compilation error.

#### Code without `? extends T` (Covariance):

```java
import java.util.List;

public class CovarianceExample {

    // Method that tries to accept a list of a more general type (Number)
    public static void printNumbers(List<Number> numbers) {
        for (Number number : numbers) {
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4);

        // This line causes a compilation error
        printNumbers(integers);  // Error: List<Integer> cannot be converted to List<Number>
    }
}
```

#### Compilation Error:
```
Error: incompatible types: List<Integer> cannot be converted to List<Number>
```

- `List<Integer>` is not the same as `List<Number>`, even though `Integer` is a subtype of `Number`. Java treats `List<Integer>` and `List<Number>` as **incompatible types** unless you use covariance (`? extends T`).

#### Correct Code with `? extends T`:
```java
import java.util.List;

public class CovarianceExample {

    // Use ? extends Number to allow reading from any subtype of Number
    public static void printNumbers(List<? extends Number> numbers) {
        for (Number number : numbers) {
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4);
        
        // This works because ? extends Number allows passing List<Integer>
        printNumbers(integers);  // Works fine
    }
}
```

Now the code compiles and runs without error because `? extends Number` allows the method to accept any list whose elements are subtypes of `Number`, such as `List<Integer>`.

---

### Example 3: Without Contravariance (`? super T`)
Now imagine you're trying to write items into a list of `Dog`, but you want to pass a list of a more general type (`Animal`). Without using `? super T`, you'll run into a compilation error because you're trying to insert a specific subtype (`Dog`) into a general type (`Animal`).

#### Code without `? super T` (Contravariance):

```java
import java.util.List;
import java.util.ArrayList;

class Animal { }
class Dog extends Animal { }

public class ContravarianceExample {

    // Method that tries to add a Dog into a list of animals
    public static void addDog(List<Animal> animals) {
        animals.add(new Dog());
    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        
        // This line causes a compilation error
        addDog(dogs);  // Error: List<Dog> cannot be converted to List<Animal>
    }
}
```

#### Compilation Error:
```
Error: incompatible types: List<Dog> cannot be converted to List<Animal>
```

- Even though `Dog` is a subclass of `Animal`, `List<Dog>` is not a subclass of `List<Animal>`. This causes a compilation error unless you use contravariance (`? super T`).

#### Correct Code with `? super T`:
```java
import java.util.List;
import java.util.ArrayList;

class Animal { }
class Dog extends Animal { }

public class ContravarianceExample {

    // Use ? super Dog to allow adding Dogs to the list
    public static void addDog(List<? super Dog> animals) {
        animals.add(new Dog());
    }

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        
        // This works because ? super Dog allows passing List<Animal>
        addDog(animals);  // Works fine
    }
}
```

Now the code compiles and runs without error because `? super Dog` allows adding `Dog` instances to any list that accepts `Dog` or its supertypes (`Animal` in this case).

---

### Conclusion:

- **Covariance (`? extends T`)** is used when you need to **read** from a data structure but don't need to modify it. It allows you to work with subtypes of a given type (`T`).
- **Contravariance (`? super T`)** is used when you need to **write** to a data structure but don't care about reading from it. It allows you to work with supertypes of a given type (`T`).

If you don’t follow the concepts of covariance and contravariance, you may encounter type incompatibility errors, as demonstrated in the examples above.

For more info: 

- https://medium.com/@alxkm/understanding-variance-in-java-covariant-and-contravariant-types-explained-31d569f2c9af

- https://stackoverflow.com/questions/8481301/covariance-invariance-and-contravariance-explained-in-plain-english

- https://docs.oracle.com/javase/tutorial/java/generics/upperBounded.html

- https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html

- https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html


## Some Example Codes for understanding the internals of `Wilcard ?`

```java
import java.util.Arrays;
import java.util.List;

class Main {
    
    public static void printNumbers(List<? extends Number> numbers) {
        for(Number number: numbers) {
            System.out.println(number);
        }
    }
    
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3, 4.4);
        List<Float> floatList = Arrays.asList(1.5f, 2.3f, 3.7f, 4.1f);
        
        printNumbers(intList);
        printNumbers(doubleList);
        printNumbers(floatList);
    }
}
```


---

**Question:**
Consider the following Java code, which uses a `List<? extends CharSequence>`. The list contains elements of type `String`. Carefully analyze the code and determine what the output will be when the `main` method is executed. Explain why the output is produced based on the methods available in the `CharSequence` interface.

```java
import java.util.Arrays;
import java.util.List;

class Main {
    
    public static void printStringsSize(List<? extends CharSequence> charSequence) {
        
        for(CharSequence ch : charSequence) {
            System.out.println(ch.length());
        }
        
    }
    
    public static void main(String[] args) {
        List<String> styrList = Arrays.asList("HELLO", "WORLD", "UNITED KINGDOM");
        printStringsSize(styrList);
    }
}
```

**Questions:**
1. What is the output of the above code when run?
2. Why are we able to call the `length()` method on the elements of the list?
3. Explain what would happen if you tried to pass a list of `StringBuilder` objects to the `printStringsSize` method instead of a list of `String`. Would it work? Why or why not?

---

This question tests the students' understanding of the upper-bounded wildcard `<? extends CharSequence>`, and it encourages them to think about interfaces, inheritance, and method availability across different classes that implement `CharSequence`.


### Example 3

The following code gives error:

```java
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<Integer>();
        List<? extends Number> l2 = l1;
        List<Number> l3 = l1; // Compile-Time Error: incompatible types: List<Integer> cannot be converted to List<Number>
        System.out.println(l2);
    }
}
```

## Java Generic Wildcard Error

The issue with the following line:
```java
List<? extends Number> l1 = new ArrayList<? extends Number>();
```
Java does **not** allow using wildcard generics (`? extends Number`) in the instantiation of a generic object.

### **Why is This an Error?**
1. **Wildcard `? extends Number` is only for reference types, not instantiations**  
   - When declaring `List<? extends Number>`, Java understands that `l1` can hold a list of objects that are **subtypes of `Number`** (e.g., `Integer`, `Double`).
   - However, you **cannot** use `? extends Number` on the right-hand side (during object creation) because Java does not know what specific type to instantiate.
   - Java expects a concrete type, such as `ArrayList<Number>` or `ArrayList<Integer>`, on the right-hand side.

### **Corrected Code**
#### **Solution 1: Use a Concrete Type for Instantiation**
```java
import java.util.List;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        List<? extends Number> l1 = new ArrayList<Number>(); // ✅ Correct
    }
}
```
Or, if you want a specific subtype:
```java
List<? extends Number> l1 = new ArrayList<Integer>(); // ✅ Also Correct
```
Here, `ArrayList<Integer>` is a valid instantiation because `Integer` extends `Number`.

#### **Solution 2: Use Explicit Generic Type Without Wildcard**
If you want a list of `Number` itself:
```java
List<Number> l1 = new ArrayList<Number>(); // ✅ Works fine
```

---


## ❌ Compilation Error: Invariant Generic Type Mismatch (`List<Number> ≠ List<Integer>)`

### **Understanding the Compilation Error in Code**
```java
import java.util.List;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        List<Number> l1 = new ArrayList<Integer>(); // ❌ Compilation Error
    }
}
```
**This code will not compile** because Java **does not allow** assigning `ArrayList<Integer>` to `List<Number>`. 

---

### **1️⃣ Why Does It Fail? (Invariance in Generics)**
In Java, **generics are invariant**. This means:
- `List<Number>` **is not the same as** `List<Integer>`, even though `Integer` is a subclass of `Number`.
- Java treats `List<Number>` and `List<Integer>` as completely **different types**.

🚨 **Incorrect assumption:**  
You might think `ArrayList<Integer>` is a `List<Number>` because `Integer extends Number`, but Java **does not allow this** for generic collections.

---

## **2️⃣ Example of Invariance Issue**
If Java allowed:
```java
List<Number> l1 = new ArrayList<Integer>(); // Hypothetically allowed
l1.add(10.5); // Adding a Double to a List<Integer>? ❌ Type Safety Violation!
```
🚨 **Problem:**  
- `l1` is supposed to be a `List<Number>`, but internally, it's actually a `List<Integer>`.
- If Java allowed this, someone could **add a `Double` (`10.5`)** into `l1`, which is actually an `ArrayList<Integer>`, **causing a runtime error**.

✅ **Java prevents this at compile-time** to **ensure type safety**.

---

### **3️⃣ Correct Solutions**
#### ✅ **Solution 1: Use `List<? extends Number>`**
If you need a `List` that can hold `Integer`, `Double`, etc., use a wildcard:
```java
List<? extends Number> l1 = new ArrayList<Integer>(); // ✅ Allowed
```
- **Why does this work?** `? extends Number` means **it can hold any subclass of `Number`**.
- **Limitation:** You cannot add new elements except `null`.

---

#### ✅ **Solution 2: Use `List<Number>` and Store `Integer` and `Double`**
If you want to store both `Integer` and `Double`, declare it as `List<Number>` but initialize it with `ArrayList<Number>`:
```java
List<Number> l1 = new ArrayList<>(); // ✅ Works fine
l1.add(10);     // Integer ✅
l1.add(10.5);   // Double ✅
System.out.println(l1); // Output: [10, 10.5]
```
- **Why does this work?** Since the list is explicitly declared as `List<Number>`, it can hold **both `Integer` and `Double`**.

---

#### ✅ **Solution 3: Use `List<? super Integer>` for Adding Integers**
If you need to **ensure only `Integer` (or its superclasses) can be added**, use `? super Integer`:
```java
List<? super Integer> l1 = new ArrayList<Number>(); // ✅ Allowed
l1.add(10);  // ✅ Allowed
// l1.add(10.5); // ❌ ERROR: Only Integers (or their superclasses) are allowed
```
- **Why does this work?**  
  - `? super Integer` means **it can hold Integer and its superclasses** (`Number`, `Object`).
  - **Allows adding integers** but prevents adding `Double`.

---

### **4️⃣ Summary**
| **Code** | **Compiles?** | **Why?** |
|-----------|------------|--------|
| `List<Number> l1 = new ArrayList<Integer>();` | ❌ No | Generics are invariant (`List<Integer>` ≠ `List<Number>`) |
| `List<? extends Number> l1 = new ArrayList<Integer>();` | ✅ Yes | Can hold any subtype of `Number`, but **cannot add elements** |
| `List<Number> l1 = new ArrayList<>();` | ✅ Yes | Proper type matching (`List<Number>` for `ArrayList<Number>`) |
| `List<? super Integer> l1 = new ArrayList<Number>();` | ✅ Yes | Allows adding `Integer`, but **not `Double`** |

Invariance means that even if one type is a subtype of another, generic types of those types do not inherently maintain the same relationship.

Here’s a beautifully crafted question with relevant icons to make it visually appealing:  

---

## 🚀 **Java 8 Challenge: Remove Whitespaces from a String!** 🧑‍💻  

🔹 **Task:** Write a **Java 8** program to remove all whitespace characters from a given string.  
🔹 **Restrictions:** Use **Java 8 Stream API only** (No regex, no loops!).  
🔹 **Input Example:** `"I am a Java developer"`  
🔹 **Expected Output:** `"IamaJavadeveloper"`  

✅ **Bonus Points if your code is concise and readable!**  

**🔗 Hint:** Explore `.chars()`, `.filter()`, `.mapToObj()`, and `.collect(Collectors.joining())`.  

💡 **Can you crack this challenge using Java 8 only?** Drop your solution below! ⬇️  

---

### Solution

```java
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String input = "I am a Java developer";

        String result = input.chars()
                .filter(c -> !Character.isWhitespace(c))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        System.out.println("Original String: " + input);
        System.out.println("String without spaces: " + result);
    }
}
```

## 🚀 Static Methods in Generic Classes – What You Must Know! ⚡

A **generic class** in Java can contain **static methods**, but the static methods **cannot** access the class's generic type parameters.  

### Why?  
Generics in Java work at the instance level, meaning the type parameter `<T>` is associated with an instance of the class. However, **static methods belong to the class itself, not to a specific instance**, and they exist independently of any generic type parameter.

### Example:  
```java
public class GenericClass<T> {
    
    // Instance method - Can use T
    public void instanceMethod(T value) {
        System.out.println("Value: " + value);
    }

    // Static method - Cannot use T
    public static void staticMethod() {
        System.out.println("This is a static method.");
    }

    // ❌ Compile-time error: Cannot use 'T' in a static context
    /*
    public static void invalidStaticMethod(T value) {
        System.out.println(value);
    }
    */
}

public class Main {
    public static void main(String[] args) {
        GenericClass<Integer> obj = new GenericClass<>();
        obj.instanceMethod(100); // ✅ Allowed

        GenericClass.staticMethod(); // ✅ Allowed, but does not use T
    }
}
```

### Alternative: Using Type Parameters in Static Methods
If you need to use generics inside a static method, **declare the type parameter inside the method itself** rather than relying on the class’s type parameter:

```java
public class Utility {
    public static <T> void printValue(T value) {
        System.out.println("Value: " + value);
    }
}

public class Main {
    public static void main(String[] args) {
        Utility.printValue(123);       // ✅ Works with Integer
        Utility.printValue("Hello");   // ✅ Works with String
    }
}
```

### Key Takeaways:
1. **Static methods can exist in generic classes.**
2. **They cannot access the class’s type parameter (`T`).**
3. **You can declare type parameters inside static methods separately if needed.**

## Why Are Arrays Covariant but Generics Invariant in Java?

https://stackoverflow.com/questions/18666710/why-are-arrays-covariant-but-generics-are-invariant

In Java, **arrays are covariant**, while **generics are invariant**. This design choice is due to the differences in how arrays and generics handle type safety at runtime.

---

### 1. **Arrays are Covariant**
Arrays are covariant in Java, meaning that a **subtype array can be assigned to a supertype array reference**.

#### Example:
```java
Integer[] intArray = {1, 2, 3};
Number[] numArray = intArray; // Allowed due to covariance
numArray[0] = 3.14; // Runtime error: ArrayStoreException
```
#### Why?
- Arrays **retain type information at runtime**.
- The JVM performs **runtime type checks** to prevent type violations.
- If an invalid assignment is attempted (like storing a `Double` in an `Integer[]`), **an `ArrayStoreException` is thrown**.

---

### 2. **Generics are Invariant**
Generics are **invariant**, meaning that `List<Integer>` is **not** a subtype of `List<Number>`, even though `Integer` is a subtype of `Number`.

#### Example:
```java
List<Integer> intList = new ArrayList<>();
List<Number> numList = intList; // Compilation error
```
#### Why?
- Generics **do not retain type information at runtime** due to **type erasure**.
- Java's generics are implemented with **compile-time type checking**.
- Allowing covariance would introduce potential runtime type safety issues.

If covariance were allowed, this would be possible:
```java
List<Integer> intList = new ArrayList<>();
List<Number> numList = intList; // Hypothetically allowed
numList.add(3.14); // This would break type safety!
Integer value = intList.get(0); // Type mismatch at runtime!
```
Since Java removes generic type information at runtime (due to **type erasure**), it cannot detect and prevent such type violations like it does with arrays.

---

### **Conclusion:**
- Arrays are covariant because **they have runtime type checks**.
- Generics are invariant because **they rely on compile-time checks** and **type erasure** makes runtime checks impossible.

## 🔄 Multiple Inheritance in Java 🚀  

#### ❓ What is Multiple Inheritance?  
Multiple inheritance is a feature that allows a class to inherit from more than one parent class. This means the child class can acquire properties and behaviors from multiple sources.  

#### ⚠️ Why Doesn't Java Support Multiple Inheritance with Classes?  
Java **does not support multiple inheritance with classes** to avoid:  
1. **Diamond Problem 🔷** – Ambiguity when two parent classes have the same method.  
2. **Complexity ❗** – Managing dependencies from multiple parents can be confusing.  
3. **Maintainability 🛠️** – Hard to track method calls and resolve conflicts.  

#### ✅ How Java Achieves Multiple Inheritance  
Java allows multiple inheritance using:  
- **Interfaces 🎭** – A class can implement multiple interfaces.  
- **Default Methods in Interfaces 🏗️** – Since Java 8, interfaces can have default method implementations.  

#### 📌 Example of Multiple Inheritance using Interfaces  
```java
interface A {
    void methodA();
}

interface B {
    void methodB();
}

// Class implementing multiple interfaces
class C implements A, B {
    public void methodA() {
        System.out.println("Method A from Interface A");
    }

    public void methodB() {
        System.out.println("Method B from Interface B");
    }
}

public class MultipleInheritanceExample {
    public static void main(String[] args) {
        C obj = new C();
        obj.methodA();
        obj.methodB();
    }
}
```
**Output:**  
```
Method A from Interface A  
Method B from Interface B  
```

#### 🔷 What About Default Methods in Interfaces?  
If two interfaces have the same default method, the implementing class must override it:  

```java
interface X {
    default void show() {
        System.out.println("Show from X");
    }
}

interface Y {
    default void show() {
        System.out.println("Show from Y");
    }
}

class Z implements X, Y {
    @Override
    public void show() {
        System.out.println("Overridden show method in Z");
    }
}

public class DefaultMethodExample {
    public static void main(String[] args) {
        Z obj = new Z();
        obj.show(); // Resolves ambiguity
    }
}
```
**Output:**  
```
Overridden show method in Z
```

#### 🎯 Key Takeaways  
✅ Java prevents **multiple inheritance of classes** to avoid complexity.  
✅ Java allows **multiple inheritance using interfaces** to keep flexibility.  
✅ If conflicts arise from **default methods**, the class must override them.  

🔥 **Final Thought:** Java smartly avoids multiple inheritance issues while still providing flexibility through interfaces! 🚀

## ✅ **Overriding Default Methods in Java** 🚀  

Yes, **default methods in interfaces can be overridden** in Java. This is useful when a class implements multiple interfaces that have default methods with the same name, leading to ambiguity.  

---

### 📌 **How to Override a Default Method?**  
A class that implements an interface can **override** its default method by providing its own implementation.  

#### 📝 **Example: Overriding a Default Method**
```java
interface A {
    default void show() {
        System.out.println("Default show() from A");
    }
}

class B implements A {
    @Override
    public void show() {
        System.out.println("Overridden show() in class B");
    }
}

public class OverrideDefaultMethod {
    public static void main(String[] args) {
        B obj = new B();
        obj.show();  // Calls the overridden method
    }
}
```
**Output:**  
```
Overridden show() in class B
```
👉 Here, class `B` overrides the `show()` method of interface `A`.

---

### 🔥 **Handling Ambiguity in Multiple Interfaces**  
If two interfaces have the **same default method**, the implementing class **must override it** to resolve ambiguity.  

#### ⚠️ **Example: Resolving Conflict in Multiple Interfaces**  
```java
interface X {
    default void display() {
        System.out.println("Display from X");
    }
}

interface Y {
    default void display() {
        System.out.println("Display from Y");
    }
}

// Class implementing both interfaces
class Z implements X, Y {
    @Override
    public void display() {
        System.out.println("Overridden display() in class Z");
    }
}

public class ConflictResolution {
    public static void main(String[] args) {
        Z obj = new Z();
        obj.display();  // Resolves conflict
    }
}
```
**Output:**  
```
Overridden display() in class Z
```
🔹 **Why is this necessary?**  
If `Z` doesn't override `display()`, Java **won't compile** because it can't decide which method to call.

---

### 🎯 **Key Takeaways**
✅ **Default methods in interfaces can be overridden** in implementing classes.  
✅ If multiple interfaces have the **same default method**, the implementing class **must override** it.  
✅ Overriding default methods **resolves ambiguity** in multiple inheritance scenarios.  

**🔹 Conclusion:** Default methods provide flexibility in Java, but overriding them helps maintain clarity and control! 🚀

## ✅ **Can an Interface Override Default Methods in Java?** 🎭  

Yes! **An interface can override a default method** from a parent interface by:  
1. **Providing a new default implementation**  
2. **Making it abstract (forcing implementation in subclasses)**  

---

### 📌 **Example 1: Overriding a Default Method in Another Interface**  

```java
interface A {
    default void show() {
        System.out.println("Default show() from A");
    }
}

interface B extends A {  // B extends A and overrides show()
    @Override
    default void show() {
        System.out.println("Overridden default show() in B");
    }
}

class C implements B { }

public class InterfaceOverrideExample {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();  // Calls the overridden default method from B
    }
}
```
**Output:**  
```
Overridden default show() in B
```
🔹 **Why does this work?**  
- Interface `B` extends `A` and **overrides the default method** with a new implementation.  
- Class `C` implements `B`, so it inherits the overridden method from `B`.

---

### 📌 **Example 2: Making the Method Abstract Instead of Default**  

If an interface wants to **remove the default method implementation**, it can **declare the method as abstract** to force subclasses to implement it.

```java
interface A {
    default void show() {
        System.out.println("Default show() from A");
    }
}

interface B extends A {
    void show(); // Making it abstract (no default implementation)
}

class C implements B {
    @Override
    public void show() {
        System.out.println("Overridden show() in class C");
    }
}

public class AbstractMethodExample {
    public static void main(String[] args) {
        C obj = new C();
        obj.show();
    }
}
```
**Output:**  
```
Overridden show() in class C
```
🔹 **Why does this work?**  
- Interface `B` **removes** the default method by declaring `show()` as **abstract**.  
- Class `C` **must** implement `show()`, otherwise, it won’t compile.

---

### 🎯 **Key Takeaways**  
✅ **An interface can override a default method from a parent interface.**  
✅ It can **provide a new default implementation** or **make it abstract** to enforce implementation.  
✅ If an interface extends multiple interfaces with the same default method, it **must** override it to resolve conflicts.  

**🔹 Conclusion:** Interfaces in Java are powerful, allowing method inheritance while still providing control over default method behavior! 🚀

## ✅ Must the Parameterized Type of a Generic Class in Java Be a Reference Type?

Yes, in Java, the parameterized type of a generic class **must be a reference type**. Java generics **do not support primitive types** like `int`, `double`, `char`, etc. Instead, you must use their wrapper classes (`Integer`, `Double`, `Character`, etc.), which are reference types.

### Example:
✅ **Allowed (Reference Type)**
```java
class Box<T> { 
    private T value;
    
    public void setValue(T value) { this.value = value; }
    public T getValue() { return value; }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>(); // Integer (Wrapper class, reference type)
        intBox.setValue(100);
        System.out.println(intBox.getValue());
    }
}
```

❌ **Not Allowed (Primitive Type)**
```java
Box<int> intBox = new Box<>(); // Compilation Error ❌
```

### Why?  
Java generics rely on **type erasure**, which means that generic type parameters are erased at runtime. Since primitive types do not have a common superclass (like `Object` for reference types), they cannot be used as type parameters.

### Workaround:
If you need to use primitive types, use **wrapper classes** (`Integer`, `Double`, etc.) or **generic specialization** via `List<int[]>` (for arrays) or third-party libraries like **Eclipse Collections**.

https://stackoverflow.com/questions/2721546/why-dont-java-generics-support-primitive-types

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> `super` keyword in Java

The `super` keyword in Java is used **only for directly implemented interfaces and superclasses**. Here’s how it works in different contexts:

---

## **1️⃣ `super` in Classes (Inheritance)**
When used in a class, `super` refers to the **immediate superclass**.

### **Example:**
```java
class Parent {
    void show() {
        System.out.println("Parent show()");
    }
}

class Child extends Parent {
    @Override
    void show() {
        super.show();  // Calls Parent's show() method
        System.out.println("Child show()");
    }
}

public class Main {
    public static void main(String[] args) {
        Child obj = new Child();
        obj.show();
    }
}
```
### **Output:**
```
Parent show()
Child show()
```
- `super.show();` calls the `show()` method from the immediate superclass (`Parent`).
- It **cannot** access methods from a grandparent class directly if the parent overrides them.

---

## **2️⃣ `super` in Interfaces (Default Methods)**
When used in interfaces, `super` can be used to call **a default method of a directly implemented interface**.

### **Example:**
```java
interface I1 {
    default void show() {
        System.out.println("I1 show()");
    }
}

class C1 implements I1 {
    @Override
    public void show() {
        I1.super.show();  // Calls I1's default method
    }
}
```
### **Key Rule:**
You **must** explicitly name the interface (`I1.super.show();`) because Java does not have direct super-interface chaining like classes.

---

## **3️⃣ What Happens with Multiple Interfaces?**
If a class implements multiple interfaces that have conflicting default methods, Java **forces** you to override the method explicitly.

### **Example of Conflict:**
```java
interface I1 {
    default void show() {
        System.out.println("I1 show()");
    }
}

interface I2 {
    default void show() {
        System.out.println("I2 show()");
    }
}

class C1 implements I1, I2 {
    @Override
    public void show() {
        I1.super.show();  // Resolving conflict
    }
}
```
### **Why is this necessary?**
- Both `I1` and `I2` have `show()`, and Java **does not know which one to choose**.
- The class **must** explicitly override `show()` and call the correct interface using `I1.super.show();` or `I2.super.show();`.

---

## **🚀 Conclusion**
✅ `super` is only for:
1. **The immediate superclass** (in class inheritance).
2. **Directly implemented interfaces** (for calling default methods).

❌ **It cannot be used for:**
- Grandparent classes directly.
- Indirectly inherited interfaces.


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Why Generic Type Instantiation Fails in Java: Solutions and Best Practices

Why the following code is not working:

```java
class Box<T> {
    private T item;
    
    Box() {
        this.T = new T();
    }
    
}



public class Main {
    public static void main(String[] args) {
        Box<String> boxStr = new Box<String>();
    }
}
```


The code isn't compiling because you are trying to instantiate a generic type `T` using `new T()`. Java does not allow instantiating generic types like this due to type erasure at runtime. 

Here are the problems:
1. **Instantiation of Generic Types**: You cannot directly create an instance of a generic type `T` using `new T()` in Java. Generics in Java are implemented via type erasure, meaning that the actual type information for `T` is not available at runtime.

2. **Incorrect Constructor Syntax**: In the constructor `Box()`, `this.T = new T();` is invalid. `T` is a type parameter, not a field or variable, so `this.T` does not make sense.

To fix this issue, you can pass the instance of `T` from the outside (constructor or setter method), or use reflection if necessary. Here's an example using a constructor to pass the item:

### Solution 1: Pass the item through the constructor
```java
class Box<T> {
    private T item;
    
    Box(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<String> boxStr = new Box<>("Hello World");  // Pass the string
        System.out.println(boxStr.getItem());
    }
}
```

### Solution 2: Use Reflection (not recommended unless necessary)
You can use reflection to instantiate the generic type, but this approach can be more complex and error-prone.

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Handling Non-Default Constructible Types in C++ Templates: Best Practices and Solutions

In C++, if the type used in a template class does not have a default constructor, and you attempt to instantiate it using `T()`, the code will fail to compile. C++ templates are type-agnostic until they are instantiated with a specific type, so if the type doesn't meet the requirements (like having a default constructor), the error occurs at the point of instantiation.

Let me show you an example to explain this scenario:

### Example:

```cpp
#include <iostream>
using namespace std;

template <typename T>
class Box {
public:
    T item;

    Box() {
        item = T();  // Calls default constructor of T
    }

    T getItem() {
        return item;
    }
};

class NoDefaultConstructor {
public:
    NoDefaultConstructor(int x) {
        cout << "Constructor called with " << x << endl;
    }
};

int main() {
    Box<int> boxInt;  // This will work, int has a default constructor
    cout << boxInt.getItem() << endl;

    // Box<NoDefaultConstructor> boxNDC;  // This will cause a compile-time error
    return 0;
}
```

### Explanation:
- **Box<int>**: The type `int` has a default constructor (it is initialized to `0`), so `Box<int>` will compile and run correctly.
- **Box<NoDefaultConstructor>**: The class `NoDefaultConstructor` does not have a default constructor (only a constructor that takes an `int` argument), so if you try to instantiate `Box<NoDefaultConstructor>`, the instantiation will fail at compile-time when the line `item = T();` is encountered. The error occurs because `NoDefaultConstructor` lacks a default constructor.

### Compile-Time Error:
If you uncomment the line `Box<NoDefaultConstructor> boxNDC;`, the compiler will produce an error similar to this:

```
error: no matching function for call to 'NoDefaultConstructor::NoDefaultConstructor()'
```
--- 

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Understanding Compile-Time Type Information in C++ vs. Java's Type Erasure: Key Differences and Practical Examples

### Compile-Time Type Information in C++

In C++, **compile-time type information** means that types used in templates (similar to generics in Java) are fully known and resolved during compilation. When you create a template class or function, the compiler generates specific versions of that template for each type you use, which is called **template instantiation**. This is all done at compile-time, meaning before the program is executed.

Here's how it works in C++:
1. **Template Definition**: A template is a blueprint for a class or function that can work with any data type.
2. **Template Instantiation**: When you use a template with a specific type (e.g., `Box<int>`), the compiler creates a specific version of that template for the given type during compilation.
3. **Code Generation**: The compiler generates the necessary machine code for each type-specific version of the template.

This is why C++ allows operations like `new T()` in templates. Since the type is known at compile time, the compiler knows the exact constructor to call when instantiating `T()`.

### Example: Compile-Time Type Information in C++

```cpp
template <typename T>
class Box {
public:
    T item;
    
    Box() {
        item = T();  // Calls the default constructor for the specific type
    }
};

int main() {
    Box<int> boxInt;        // Compiler generates Box<int>
    Box<std::string> boxStr; // Compiler generates Box<std::string>

    return 0;
}
```
- When you instantiate `Box<int>`, the compiler generates a version of `Box` where `T` is `int`.
- When you instantiate `Box<std::string>`, the compiler generates another version of `Box` where `T` is `std::string`.
- This means that type-specific code for `Box<int>` and `Box<std::string>` is generated, and the default constructors for `int` and `std::string` will be called, respectively.

### Difference from Java's Generics (Type Erasure)

In **Java**, generics are implemented using **type erasure**, meaning that type information is removed at runtime. The compiler uses the generic type information for compile-time checks (ensuring type safety), but once the code is compiled, the type parameters are erased and replaced with a more general type, typically `Object`. As a result, you can't instantiate a generic type like `T` using `new T()` because the specific type `T` is not known at runtime.

### How Java's Type Erasure Works:
1. **Generic Type Check at Compile-Time**: Java ensures type safety at compile-time (e.g., you can't add a `String` to a `List<Integer>`).
2. **Type Erasure at Runtime**: Once compiled, Java replaces the generic types with more general ones (e.g., `Object` or `bounded types`). This is why you can't create new instances of a generic type using `new T()`, since `T` no longer exists at runtime.
3. **Single Class for All Types**: Unlike C++, where the compiler generates different versions of a template class for different types, Java only creates one version of a generic class, and it works for all types.

### Example: Type Erasure in Java

```java
class Box<T> {
    private T item;
    
    Box(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }
    
    // Can't do this
    // Box() {
    //     item = new T();  // ERROR: Can't create new instances of T
    // }
}

public class Main {
    public static void main(String[] args) {
        Box<Integer> boxInt = new Box<>(123);  // T is erased at runtime
        Box<String> boxStr = new Box<>("Hello"); // T is erased at runtime
    }
}
```

In the above example:
- At compile-time, Java knows that `boxInt` is of type `Box<Integer>` and `boxStr` is of type `Box<String>`.
- At runtime, however, both `Box<Integer>` and `Box<String>` are essentially treated as `Box<Object>`, due to type erasure. This is why the type `T` is not available at runtime, preventing you from calling `new T()`.

### Key Differences Between C++ and Java:

| **Aspect**                   | **C++ (Templates)**                      | **Java (Generics)**                      |
|------------------------------|------------------------------------------|------------------------------------------|
| **Type Information**          | Fully resolved at compile-time           | Type erased at runtime (type erasure)    |
| **Instantiation of Generic Types** | Can instantiate with `new T()`          | Cannot instantiate with `new T()`        |
| **Code Generation**           | Separate code is generated for each type | Single generic class is generated        |
| **Performance**               | No overhead from type erasure (optimized code per type) | Some overhead due to type erasure        |
| **Flexibility**               | More flexible (e.g., can call `new T()`, static methods, etc.) | More restricted (cannot use `new T()`, no static members) |
| **Compatibility**             | Templates generate type-specific code, no compatibility issue | Java ensures backward compatibility with raw types |

### Conclusion:
- **C++ templates** use compile-time type information, meaning type-specific code is generated at compile time, and all types are fully known. This allows operations like `new T()` to work.
- **Java generics** use type erasure, meaning type information is only available at compile time for type checking but is erased at runtime. Hence, you cannot instantiate `T` or use certain operations like `new T()`.

In summary, C++ templates offer more flexibility at the cost of potentially larger binaries due to template instantiation, while Java generics prioritize type safety and backward compatibility at runtime by using type erasure.

---


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Why super.super.method() is Not Allowed in Java ?

https://www.baeldung.com/java-super-super-not-allowed


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Type Erasure in Java

** To implement generics, the Java compiler applies type erasure to: Replace all type parameters in generic types with their bounds or Object if the type parameters 
are unbounded.**

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Why super keyword in generics is not allowed at class level ?

https://stackoverflow.com/questions/37411256/why-super-keyword-in-generics-is-not-allowed-at-class-level

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Erasure of Generic Types

https://docs.oracle.com/javase/tutorial/java/generics/genTypes.html

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Erasure of Generic Methods

https://docs.oracle.com/javase/tutorial/java/generics/genMethods.html


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Java Generics Questions and Answers

https://docs.oracle.com/javase/tutorial/java/generics/QandE/generics-questions.html

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> What are the differences between "generic" types in C++ and Java?

https://stackoverflow.com/questions/996135/how-are-java-generics-different-from-c-templates-why-cant-i-use-int-as-a-par

https://stackoverflow.com/questions/36347/what-are-the-differences-between-generic-types-in-c-and-java


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> What is reified Java Generics ?

https://maciejwalkowiak.com/blog/java-reified-generics/

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Template Instantiation in C++

https://docs.oracle.com/cd/E19205-01/819-5267/bkafi/index.html

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> What are the differences between Generics in C# and Java... and Templates in C++?

https://stackoverflow.com/questions/31693/what-are-the-differences-between-generics-in-c-sharp-and-java-and-templates-i?noredirect=1&lq=1

My example code for understanding the difference between C++ Templates and Java Generics:

```cpp
#include <iostream>
using namespace std;

class A {
    public:
    void foo() {
        cout << "A::foo()" << endl;
    }
};

class B {
    public:
};

template <typename T>
void print(T p1) {
    p1.foo();
}

int main() {
    A a1;
    print(a1);
    
    // B b1;
    // print(b1); // Compile-Time Error

    return 0;
}
```

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> What Happens If You Don’t Call `super()` First in Java?

Java enforces constructor chaining from the subclass to the superclass to ensure the **base class is fully initialized before any initialization in the derived class occurs**.

#### 🔥 Rule:

> In Java, **the call to the superclass constructor (`super(...)`) must be the first statement** in a subclass constructor. Otherwise, **the compiler will throw an error**.

---

### ✅ Correct Example: Super Constructor as First Line

```java
class Animal {
    Animal(String name) {
        System.out.println("Animal constructor called: " + name);
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name); // ✅ This must be the first statement
        System.out.println("Dog constructor called: " + name);
    }
}
```

#### ✅ Output:

```
Animal constructor called: Buddy
Dog constructor called: Buddy
```

🧠 **Explanation**: The superclass `Animal` is initialized first with `super(name)`, followed by the subclass `Dog`.

---

### ❌ Incorrect Example: Super Constructor Not First

```java
class Animal {
    Animal(String name) {
        System.out.println("Animal constructor called: " + name);
    }
}

class Dog extends Animal {
    Dog(String name) {
        System.out.println("Dog constructor called: " + name); // ❌ This comes before super()
        super(name); // ❌ Compile-time error
    }
}
```

#### ❌ Compile-Time Error:

```
Constructor call must be the first statement in a constructor
```

---

### ⚙️ Why This Rule Exists?

* Ensures **object integrity** by fully constructing the base class before the derived class.
* Prevents subclass code from operating on **partially constructed** objects.
* Keeps object construction **predictable and safe**.

---

### 🧭 Key Takeaways

| ✅ Do                                           | ❌ Don’t                                   |
| ---------------------------------------------- | ----------------------------------------- |
| Always call `super(...)` as the **first line** | Call `super(...)` after any statement     |
| Use `super(...)` to initialize base class      | Forget to initialize superclass correctly |
| Understand constructor chaining rules          | Assume Java reorders constructor logic    |

---

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Can a Static Method Be Inherited in a Derived Class in Java?

Yes

```java
class A1 {
    public static void func1() {
        System.out.println("A1.func");
    }
}

class A2 extends A1 {
    public static void func2() {
        System.out.println("A2.func");
    }
}

public class Main {
    public static void main(String[] args) {
        A1.func1();   // ✅ A1.func
        A2.func1();   // ✅ A1.func (inherited, no redeclaration)
        A2.func2();   // ✅ A2.func
    }
}
```
---

Sure! Here's a well-structured article explaining the compilation errors in your Java code with detailed technical reasoning.

---

## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> Understanding Java Method Overriding vs Hiding: Why Your Code Fails to Compile

Java developers often encounter confusion when dealing with **static** and **non-static methods**, especially when inheritance and overriding come into play. Let's break down the provided code and pinpoint exactly **why it fails to compile**, with insights into how Java handles method binding.

---

### 🧾 The Code in Question

```java
// Parent class
class Base {

  // non-static method
  public int add(int a, int b) {
    System.out.println("In the base class.");
    return a + b;
  }

  // static method
  public static void print() {
    System.out.println("In the Base class.");
  }
}

// Child class
class Derived extends Base {

  // ❌ Compilation error if added 'static'
  public static int add(int a, int b) {
    System.out.println("In the child class.");
    return a + b;
  }

  // ❌ Compilation error if removed 'static'
  public void print() {
    System.out.println("In the child class.");
  }
}
```

---

### 🚨 Compilation Errors Explained

#### ❌ Error 1: Attempting to "Override" a Non-Static Method with a Static One

```java
public static int add(int a, int b)
```

* The method `add()` in `Base` is **non-static**.
* In `Derived`, you’re trying to **declare a static method with the same signature**.
* In Java, **static methods belong to the class**, not to the instance.
* Therefore, a static method **cannot override** a non-static method. This causes a **compilation error**:

  ```
  error: add(int,int) in Derived cannot override add(int,int) in Base
    overridden method is not static
  ```

> ✅ **Fix**: Either remove the `static` keyword or rename the method if static behavior is needed separately.

---

### ❌ Error 2: Attempting to "Override" a Static Method with a Non-Static One

```java
public void print()
```

* The `print()` method in `Base` is **static**.
* In `Derived`, you're trying to **declare a non-static method with the same name**.
* Similar to above, **non-static methods cannot override static ones**.
* This also results in a **compilation error**:

  ```
  error: print() in Derived cannot override print() in Base
    overridden method is static
  ```

> ✅ **Fix**: Keep the method static if you intend to **hide** the static method, or rename it if you need a separate instance method.

---

### 🤔 Static vs Non-Static: What’s the Real Difference?

| Feature      | Static Methods                        | Instance Methods |
| ------------ | ------------------------------------- | ---------------- |
| Bound At     | Compile-time                          | Runtime          |
| Belongs To   | Class                                 | Object           |
| Overridable? | ❌ No (Can be hidden)                  | ✅ Yes            |
| Accessed Via | ClassName.method() or object.method() | object.method()  |

---

### ✅ Corrected Code

Here’s how the code can be **refactored to compile correctly**:

```java
class Base {
  public int add(int a, int b) {
    System.out.println("In the base class.");
    return a + b;
  }

  public static void print() {
    System.out.println("In the Base class.");
  }
}

class Derived extends Base {
  @Override
  public int add(int a, int b) {
    System.out.println("In the child class.");
    return a + b;
  }

  public static void print() {
    System.out.println("In the child class.");
  }
}

public class Main {
  public static void main(String args[]) {
    Base obj = new Derived();

    // Calls Derived's add() method - Runtime polymorphism
    System.out.println(obj.add(4, 5));

    // Calls Base's print() method - Static binding
    obj.print();
  }
}
```

---

### 🔍 Output of the Corrected Program

```
In the child class.
9
In the Base class.
```

> Notice how `print()` is still resolved to the `Base` version. That’s because static methods use **compile-time binding** based on the reference type (`Base obj`).

---

### 🧩 Conclusion

* ✔️ **Instance methods can be overridden**.
* ❌ **Static methods cannot be overridden** — they can only be **hidden**.
* ❌ **Mixing static/non-static between base and derived methods of the same name** leads to compilation errors.

Understanding these distinctions is **crucial for mastering polymorphism and inheritance** in Java. Always pay attention to the method types when designing class hierarchies!

---


## <img src="https://user-images.githubusercontent.com/74038190/212257467-871d32b7-e401-42e8-a166-fcfd7baa4c6b.gif" width ="25" style="margin-bottom: -5px;"> What is `String` and `StringBuilder` in Java ?

In Java, the primary difference between String and StringBuilder is mutability. 
String objects are immutable, meaning any modification creates a new String object, leading to increased memory usage. 
In contrast, StringBuilder is mutable, allowing in-place modifications, which makes it more memory-efficient for operations such as concatenation or manipulation within loops. 
Additionally, StringBuilder is generally faster than StringBuffer, as it is not synchronized and thus not thread-safe

For more Info: https://www.unlogged.io/post/java-and-the-string-odyssey-navigating-changes-from-jdk-1-to-jdk-21

- https://stackoverflow.com/questions/1882584/what-is-a-covariant-return-type
