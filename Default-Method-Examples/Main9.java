interface I1 {
    default void show() {
        System.out.println("Default show() from I1");
    }
}

interface I2 extends I1 {
}

class C1 implements I2 {
    @Override
    public void show() {
        // System.out.println("Overridden show() in class C");
        // I1.super.show();  // Gives Compile Time Error
        I2.super.show();
    }
}

public class Main {
    public static void main(String[] args) {
        I1 obj = new C1();
        obj.show();
    }
}