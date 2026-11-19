import java.util.*;

interface Mediator {
    void addUser(User user);
    void sendMessage(String message, User sender);
}

class SimpleChatMediator implements Mediator {
    
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        System.out.println("\n[Simple Mediator] Broadcasting message...");

        for (User user : this.users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }

        System.out.println("----------------------------------------------");
    }

}

class ModeratedChatMediator implements Mediator {
    
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        System.out.println("\n[Moderated Mediator] Checking message...");
        if (message.toLowerCase().contains("spam")) {
            System.out.println("Message rejected! Spam is not allowed.");
            System.out.println("----------------------------------------------");
            return;
        }

        System.out.println("Message approved. Broadcasting...");

        for (User user : this.users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
        
        System.out.println("----------------------------------------------");
        
    }
}

class User {
    
    private String name;
    private Mediator mediator;

    public User(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public void sendMessage(String message) {
        System.out.println(this.name + " sends: " + message);
        this.mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(this.name + " received: " + message);
    }

}

public class Main {
    public static void main(String[] args) {

        System.out.println("===== IMPLEMENTATION 1 =====");

        Mediator simpleMediator = new SimpleChatMediator();

        runChat(simpleMediator);

        System.out.println("===== IMPLEMENTATION 2 =====");
        
        Mediator moderatedMediator = new ModeratedChatMediator();
        
        runChat(moderatedMediator);

    }

    public static void runChat(Mediator mediator) {

        User alice = new User("Alice", mediator);
        User bob = new User("Bob", mediator);
        User charlie = new User("Charlie", mediator);

        mediator.addUser(alice);
        mediator.addUser(bob);
        mediator.addUser(charlie);

        alice.sendMessage("Hello everyone!");
        
        bob.sendMessage("This is spam content!");

        charlie.sendMessage("How is everyone doing?");

    }

}


