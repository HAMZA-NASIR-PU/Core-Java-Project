interface I1 {
    default String getStr() {
        return "I1::getStr()";
    }
}

class C1 implements I1 {
    
}



class Main {
    public static void main(String[] args) {
        C1 obj = new C1();
        System.out.println(obj.getStr()); // I1::getStr()
    }
}