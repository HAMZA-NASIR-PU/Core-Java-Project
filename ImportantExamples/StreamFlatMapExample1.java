import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private int orderId;
    private List<Item> items;

    public Order(int orderId, List<Item> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}

public class StreamFlatMapExample1 {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(new Item("Laptop", 1000), new Item("Phone", 500))),
                new Order(2, Arrays.asList(new Item("Tablet", 300), new Item("Watch", 200))),
                new Order(3, Arrays.asList(new Item("Headphones", 100), new Item("Speaker", 150)))
        );

        double totalPrice = orders.stream()
                                 .flatMap(order -> order.getItems().stream())
                                 .mapToDouble(Item::getPrice)
                                 .sum();

        System.out.println("Total price of all items: $" + totalPrice);
    }
}

