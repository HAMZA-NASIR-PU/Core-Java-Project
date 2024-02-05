@FunctionalInterface
interface LearningLambdaExp {
    public void learn(); // abstract method
}

@FunctionalInterface
interface LambdaExample2 {
    public int addInt(int a, int b); // abstract method
}

@FunctionalInterface
interface LambdaExample3 {
    public int getlength(String str); // abstract method
}

@FunctionalInterface
interface LambdaExample4 {
    public void print(String str1, String str2); // abstract method
}

class CompleteLambda implements LearningLambdaExp {
    public static void main(String[] args) {
        //Functional Programming
        //Functional Interface => Having only single abstract method
        CompleteLambda obj = new CompleteLambda();
        obj.learn(); //This is a normal way of implementing and calling abstract methods of an interface.
        
        //Functional interface provides reference to the lambda expression.
        //Zero parameter lambda expression.
        LearningLambdaExp i1 = () -> System.out.println("Lambda implementation of Learning Lambda exp");
        i1.learn();
        
        //Multiparameter lambda expression  
        //No need to specify parameter types
        LambdaExample2 i2 = (a, b)-> a+b;
        System.out.println(i2.addInt(3, 4));
        
        LambdaExample3 i3 = (str)->str.length();
        System.out.println(i3.getlength("HELLO WORLD"));
        
        LambdaExample4 i4 = (str1, str2)->{
            System.out.println("String1 = " + str1);
            System.out.println("String2 = " + str2);
        };
        i4.print("HELLO", "WORLD");
    }
    
    @Override
    public void learn(){
        System.out.println("Learning Lambda exp\n");
    }
}