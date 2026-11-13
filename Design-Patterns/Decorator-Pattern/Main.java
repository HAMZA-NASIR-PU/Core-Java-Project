import java.util.*;

interface Notifier {
    void sendMessage(String message);
}

class EmailNotifier implements Notifier {

    @Override 
    public void sendMessage(String message) {
        System.out.println("Sending Email: " + message);
    }

}

abstract class NotifierDecorator implements Notifier {
    public Notifier notifier;

    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

}

class WhatsAppDecorator extends NotifierDecorator {

    public WhatsAppDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override 
    public void sendMessage(String message) {
        this.notifier.sendMessage(message);
        System.out.println("Sending WhatsApp: " + message);
    }

}

class SMSDecorator extends NotifierDecorator {

    public SMSDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override 
    public void sendMessage(String message) {
        this.notifier.sendMessage(message);
        System.out.println("Sending SMS: " + message);
    }

}

public class Main {
    public static void main(String[] args) {
        Notifier message = new EmailNotifier();

        message = new SMSDecorator(message);

        message = new WhatsAppDecorator(message);

        message.sendMessage("Welcome to our Club.");
    }

}


