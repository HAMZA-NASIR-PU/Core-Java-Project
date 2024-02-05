//Why we need to consider local variables as constant always ? ==> Due to multi-threading environment.

@FunctionalInterface
interface InterfaceVar{
    public void varScope();
}

class LambdaVariableScope {
    int eId; //instance variable
    String eName; //instance variable
    static String company = "Company ABC";
    
    public static void main(String[] args) {
        int age = 25; //local variable
        String role = "Develoepr"; //local variable 
        
        InterfaceVar ref = () -> {
            System.out.println("Lambda Expression starts*********************");
            System.out.println("Emp age = " + age); //local
            System.out.println("Emp role = " + role); //local
            
            System.out.println("Emp company = " + company); //static
            
            // System.out.println("Emp id = " + eId); //error: non-static variable eId cannot be referenced from a static context
            
            // age = age + 25; //local variables referenced from a lambda expression must be final or effectively final
            //one solution is to make declaration of age variable final.
            //What is effectively final ? ==> Means consider a non-final variable as final.
            company = "AAAAAAA";
            System.out.println("Emp company = " + company + "/n"); //static
            
            LambdaVariableScope obj = new LambdaVariableScope();
            obj.eId = 11;
            obj.eName = "Michael";
            System.out.println("Emp ID = "+ obj.eId);  //Valid
            System.out.println("Emp Name = " + obj.eName);
            
            System.out.println("Lambda Expression ends*********************");
        };
        
        ref.varScope();
    }
    
}