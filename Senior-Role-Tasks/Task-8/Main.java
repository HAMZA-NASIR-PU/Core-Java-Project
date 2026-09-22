//
// Map interface defaut method computeIfAbsent()
//
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

      List<User> users = List.of(
        new User("Ali", "ADMIN"),
        new User("Sara", "USER"),
        new User("Ahmed", "ADMIN"),
        new User("John", "USER"),
        new User("Maria", "MANAGER")
      );

      Map<String, List<String>> mp = users.stream()
            .collect(
              Collectors.groupingBy(
                User::getRole,
                Collectors.mapping(
                  User::getName,
                  Collectors.toList()
                )
              )
            );
      
      System.out.println(mp);

      Map<String, List<String>> mp2 = new HashMap<>();

      for (User user : users) {
        mp2.computeIfAbsent(user.getRole(), role -> new ArrayList<>()).add(user.getName());
      }
      
      System.out.println(mp2);

      Map<String, List<String>> mp3 = mp.entrySet().stream().sorted(Map.Entry.<String, List<String>>comparingByKey().reversed()).collect(
        Collectors.toMap(
          Map.Entry::getKey,
          Map.Entry::getValue,
          (a, b) -> a,
          LinkedHashMap::new
        )
      );

      System.out.println(mp3);


      // Sort map based on the length of keys.
    //   mp = mp.entrySet().stream().sorted(
    //       (Map.Entry<String, List<String>> e1, Map.Entry<String, List<String>> e2) -> 
    //                         Integer.compare(e1.getKey().length(), e2.getKey().length())
    //     ).collect(
    //     Collectors.toMap(
    //       Map.Entry::getKey,
    //       Map.Entry::getValue,
    //       (a, b) -> a,
    //       LinkedHashMap::new
    //     )
    //   );

    }
}

class User {

    private String name;
    private String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return name + " -> " + role;
    }
}
