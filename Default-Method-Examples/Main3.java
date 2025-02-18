// Diamond Problem in case of default method.

interface I1 {
    default String getStr() {
        return "I1::getStr()";
    }
}

interface I2 extends I1 {
    
}

interface I3 extends I1 {
    
}

class C1 implements I2, I3 {
    
}



class Main3 {
    public static void main(String[] args) {
        I1 obj = new C1();
        System.out.println(obj.getStr()); // I1::getStr()
    }
}