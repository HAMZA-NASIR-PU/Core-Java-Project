import java.util.Optional;

class CompleteOptional {
    public static void main(String[] args) throws ClassNotFoundException {
        // Create an Optional Object
        Optional<String> myStr1 = Optional.empty();
        System.out.println(myStr1);
        // System.out.println(myStr1.get()); //Error: java.util.NoSuchElementException

        // Optional.of() is a static method
        Optional<String> myStr2 = Optional.of("ABC");
        System.out.println(myStr2.get());

        // Optional.ofNullable() is a static method
        Optional<String> myStr3 = Optional.ofNullable("DEF");
        System.out.println(myStr3.get());

        // DIfference between Optiona.of() and Optional.ofNullable()
        // Optional<String> myStr4 = Optional.of(null); // Optional.of() will throw an
        // error if argument is null.
        Optional<String> myStr5 = Optional.ofNullable(null);

        if (myStr5.isPresent()) {
            System.out.println(myStr5.get());
        }

        Optional<String> myStr6 = Optional.ofNullable("GHI");
        myStr6.ifPresent(str -> System.out.println(str.toLowerCase()));

        Optional<String> myStr7 = Optional.empty();
        String str1 = myStr7.orElse("HELLO WORLD");
        System.out.println(str1);

        Optional<String> myStr8 = Optional.of("NEW VALUE");
        String str2 = myStr8.orElse("OLD VALUE");
        System.out.println(str2);

        Optional<String> myStr9 = Optional.empty();
        String str3 = myStr9.orElseGet(() -> {
            System.out.println("Optional.orElseGet() instance method is calling...");
            return "Optional.orElseGet() value";
        });
        System.out.println(str3);

        // Optional<String> myStr10 = Optional.empty();
        // String str4 = myStr10.orElseThrow(ClassNotFoundException::new);
        // System.out.println(str4);

        System.out.println("**************************************************/n");
        Optional<String> myStr11 = Optional.of("HELLO WORLD");
        System.out.println(myStr11.filter(str -> str.contains("HELLO")));
        // System.out.println(str5);

        Optional<String> myStr12 = Optional.of("HELLO");
        // if myStr12 conatins null, map() instance method will not execute.
        System.out.println(myStr12.map((str) -> {
            return str + " ABC";
        }));

        System.out.println("*********DIFFERENCE BETWEEN flatMap and map***************/n");
        Optional<String> myStr13 = Optional.ofNullable("String 1");
        System.out.println(myStr13.map(s -> Optional.of(s)));

        System.out.println(myStr13.flatMap(s -> Optional.of(s)));
        

    }
}