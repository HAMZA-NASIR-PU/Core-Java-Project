interface I1 {
    default void show() {
        System.out.println("Default show() from A");
    }
}

interface I2 extends I1 {
    void show(); // Making it abstract (no default implementation)
}

class C1 implements I2 {
    @Override
    public void show() {
        System.out.println("Overridden show() in class C");
    }
}

public class Main {
    public static void main(String[] args) {
        I1 obj = new C1();
        obj.show();
    }
}