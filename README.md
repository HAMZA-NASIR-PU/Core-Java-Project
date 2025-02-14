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