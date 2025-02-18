// What happens if you remove the getStr() method from C1? Which interface’s method will be called, and why? 🚀

/** 
 * If you remove the getStr() method from C1, the code will fail to compile with the error:

"class C1 inherits unrelated defaults for getStr() from types I2 and I3"
*/
interface I1 {
    default String getStr() {
        return "I1::getStr()";
    }
}

interface I2 extends I1 {
    default String getStr() {
        return "I2::getStr()";
    }
}

interface I3 extends I1 {
    default String getStr() {
        return "I3::getStr()";
    }
}

class C1 implements I2, I3 {
    @Override
    public String getStr() {
        return I2.super.getStr();
    }
}


class Main {
    public static void main(String[] args) {
        I1 obj = new C1();
        System.out.println(obj.getStr()); // I2::getStr()
    }
}
        