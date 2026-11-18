
interface PaymentProcessor {
    void pay(double amount);
}

class PayPalService {
    public void makePayment(double amount) {
        System.out.println("Payment of " + amount + " processed using PayPal.");
    }
}

class PayPalAdapter implements PaymentProcessor {

    private final PayPalService payPalService;

    public PayPalAdapter(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @Override
    public void pay(double amount) {
        this.payPalService.makePayment(amount);
    }

}

class ShoppingCart {
    public void checkout(PaymentProcessor processor) {
        processor.pay(1200);
    }
}

public class Main {
    public static void main(String[] args) {

        PayPalService service = new PayPalService();

        PaymentProcessor adapter = new PayPalAdapter(service);

        ShoppingCart cart = new ShoppingCart();

        cart.checkout(adapter);


    }
}

