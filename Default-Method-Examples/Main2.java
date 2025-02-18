// A default method can also be overridden in the class that implementing the interface.

interface I1 {
    default String getStr() {
        return "I1::getStr()";
    }
}

class C1 implements I1 {
    public String getStr() {
        return "C1::getStr()";
    }
}



class Main2 {
    public static void main(String[] args) {
        I1 obj = new C1();
        System.out.println(obj.getStr()); // C1::getStr()
    }
}