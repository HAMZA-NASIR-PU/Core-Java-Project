import java.util.List;
import java.util.function.Predicate;
import java.util.Arrays;

class Product {
    private String name;
    private double price;
    private String type;
    private String company;

    public Product(String name, double price, String type, String company) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.company = company;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public String getCompany() {
        return this.company;
    }

    @Override
    public String toString() {
        return "Product { " + "name=" + this.name + ", price=" + this.price + ", company=" + this.company + "}";
    }
}

public class EcommerceProduct {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200.0, "Electronics", "Dell"),
                new Product("Smartphone 1", 800.0, "Electronics", "Samsung"),
                new Product("Smartphone 2", 600.0, "Electronics", "Samsung"),
                new Product("Shoes", 100.0, "Fashion", "Nike"),
                new Product("T-shirt", 30.0, "Fashion", "Adidas"));

        Predicate<Product> filter = getCombinedFilter(1000, "Electronics", "Samsung");

        List<Product> filteredList = products.stream().filter(filter).toList();

        filteredList.stream().forEach(product -> System.out.println(product));

    }

    public static Predicate<Product> getCombinedFilter(double price, String type, String company) {
        // Define filters
        Predicate<Product> priceFilter = product -> product.getPrice() <= price;
        Predicate<Product> typeFilter = product -> product.getType().equals(type);
        Predicate<Product> companyFilter = product -> product.getCompany().equals(company);

        Predicate<Product> combinedFilter = priceFilter.and(typeFilter).and(companyFilter);

        return combinedFilter;
    }
}