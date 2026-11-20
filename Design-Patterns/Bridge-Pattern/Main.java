
interface MessageSender {
    void send(String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}

abstract class Notification {
    
    protected final MessageSender sender;

    protected Notification(MessageSender sender) {
        this.sender = sender;
    }

    abstract void notifyUser(String content);
}

class AlertNotification extends Notification {

    public AlertNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser(String content) {
        this.sender.send("[ALERT] " + content);
    }

}

class ReminderNotification extends Notification {

    public ReminderNotification(MessageSender sender) {
        super(sender);
    }

    @Override
    public void notifyUser(String content) {
        this.sender.send("[REMINDER] " + content);
    }
    
}

public class Main {
    public static void main(String[] args) {

        Notification alertByEmail = new AlertNotification(new EmailSender());
        Notification reminderBySms = new ReminderNotification(new SmsSender());

        alertByEmail.notifyUser("Server is down");
        reminderBySms.notifyUser("Meeting at 3 PM.");

    }
}


