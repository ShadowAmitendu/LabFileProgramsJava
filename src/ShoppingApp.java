import java.util.Scanner;

class ShoppingApp {
    Product[] storeProducts = {
            new Product(101, "IBM ThinkPad", 55000),
            new Product(102, "ONEPLUS 13", 75000),
            new Product(103, "SONY WH-18000", 80000),
            new Product(104, "Logitech G512", 4000),
            new Product(105, "Logitech G502 - Hero", 2500)
    };

    public static void main(String[] args) {
        ShoppingApp shoppingCartApp = new ShoppingApp();
        shoppingCartApp.customerMenu();
    }

    void customerMenu() {
        Cart cart = new Cart(10);
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Customer Menu =====");
            System.out.println("1. Add Product To Cart");
            System.out.println("2. Remove Product From Cart");
            System.out.println("3. Display Shopping Cart");
            System.out.println("4. Display All Store Products");
            System.out.println("5. Checkout Cart");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    cart.addProdToCart(storeProducts);
                    break;
                case 2:
                    cart.removeProdFromCart();
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    listProducts();
                    break;
                case 5:
                    cart.checkout();
                    break;
                case 0:
                    System.out.println("Exiting customer menu...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }

    void listProducts() {
        System.out.println("\n--- Product List ---");
        for (Product p : storeProducts) {
            if (p != null) {
                System.out.println("ID: " + p.getProductId() +
                        " | Name: " + p.getProductName() +
                        " | Price: " + p.getProductPrice());
            }
        }
    }
}

// Product Class
class Product {
    private final int productId;
    private final String productName;
    private final double productPrice;

    Product(int productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    int getProductId() {
        return productId;
    }

    String getProductName() {
        return productName;
    }

    double getProductPrice() {
        return productPrice;
    }
}

// Cart Class
class Cart {
    private final Product[] products;
    private int count;

    Cart(int size) {
        products = new Product[size];
        count = 0;
    }

    void addProdToCart(Product[] storeProducts) {
        if (count >= products.length) {
            System.out.println("Cart is full! Cannot add more products.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product ID to add to cart: ");
        int productId = sc.nextInt();

        for (Product storeProduct : storeProducts) {
            if (storeProduct != null && storeProduct.getProductId() == productId) {
                products[count] = storeProduct;
                count++;
                System.out.println("Product added to cart!");
                return;
            }
        }
        System.out.println("Product not found in store.");
    }

    void removeProdFromCart() {
        if (count == 0) {
            System.out.println("Cart is empty.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        displayCart();
        System.out.print("Enter product ID to remove from cart: ");
        int productId = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getProductId() == productId) {
                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                }
                products[count - 1] = null;
                count--;
                System.out.println("Product removed from cart.");
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    void displayCart() {
        if (count == 0) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\n--- Shopping Cart ---");
        double total = 0;
        for (int i = 0; i < count; i++) {
            System.out.println("ID: " + products[i].getProductId() +
                    " | Name: " + products[i].getProductName() +
                    " | Price: " + products[i].getProductPrice());
            total += products[i].getProductPrice();
        }
        System.out.println("Total Price: " + total);
        System.out.println("\n---------------------");
    }

    void checkout() {
        if (count == 0) {
            System.out.println("Cart is empty. Cannot checkout.");
        } else {
            displayCart();
            System.out.println("Checkout complete. Thank you for shopping!");
            count = 0;
        }
    }
}
