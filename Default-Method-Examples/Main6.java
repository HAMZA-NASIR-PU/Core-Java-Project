interface A {
    default void show() {
        System.out.println("A::show()");
    }
}

interface B {
    default void show() {
        System.out.println("B::show()");
    }
}

class C implements A, B {
    @Override
    public void show() {
        A.super.show(); // Explicitly choosing A's method
    }
}

public class Main {
    public static void main(String[] args) {
        A obj = new C(); // A::show()
        obj.show();
    }
}
