import java.util.Optional;

class BaseClass {
    private int value;
    public BaseClass(int value) {
        this.value = value;
    }
    public static void main(String[] args) throws ClassNotFoundException{
        BaseClass b1 = new BaseClass(1);
        Optional<BaseClass> o1 = Optional.of(b1);
        System.out.println("b1 object value = " + o1.get().value);
        
        // BaseClass b2 = null;
        // Optional<BaseClass> o2 = Optional.of(b2);   //Immediately throws a NullPointerException because b2 is null
        // System.out.println("Optional o2 = " + o2);
        
        BaseClass b3 = null;
        Optional<BaseClass> o3 = Optional.ofNullable(b3); //Not throws a NullPointerException.
        System.out.println("Optional o3 value = " + o3);  // Optional.empty
        
        
        Optional<BaseClass> o4 = Optional.empty();
        System.out.println("An empty Optional object o4 = " + o4);
        
        
        BaseClass b4 = null;
        Optional<BaseClass> o5 = Optional.ofNullable(b4);
        System.out.println("BaseClass b4 = " + b4);
        b4 = o5.orElse(new BaseClass(100));
        System.out.println("BaseClass b4 = " + b4.value);
        
        
        BaseClass b5 = null;
        Optional<BaseClass> o6 = Optional.ofNullable(b5);
        b5 = o6.orElseThrow(()-> new ClassNotFoundException("orElseThrow example"));
        // o6.orElseThrow(()->new ClassNotFoundException("HELLO WORLD"));
        
        
    }
}