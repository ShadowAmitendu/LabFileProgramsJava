import java.util.Scanner;

class ShoppingApp {
    StoreManager sm = new StoreManager(10);

    public static void main(String[] args) {
        ShoppingApp shoppingCartApp = new ShoppingApp();
        shoppingCartApp.mainMenu();
    }

    void mainMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Shopping Application =====");
            System.out.println("1. Store Manager Menu");
            System.out.println("2. Customer Menu");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    menuManager();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 0:
                    System.out.println("Exiting Application...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
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
                    cart.addProdToCart(sm);
                    break;
                case 2:
                    cart.removeProdFromCart();
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    sm.listProducts();
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

    void menuManager() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Store Manager Menu =====");
            System.out.println("1. Add A Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Remove A Product");
            System.out.println("4. Change A Product Price");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sm.addProduct();
                    break;
                case 2:
                    sm.listProducts();
                    break;
                case 3:
                    sm.removeProduct();
                    break;
                case 4:
                    sm.changeProductPrice();
                    break;
                case 0:
                    System.out.println("Exiting manager menu...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);
    }
}

// Product Class
class Product {
    private final int productId;
    private final String productName;
    private double productPrice;

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

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}

// Store Manager Class
class StoreManager {
    private final Product[] products;
    private int count;

    StoreManager(int size) {
        products = new Product[size];
        count = 0;
    }

    void addProduct() {
        if (count >= products.length) {
            System.out.println("Store is full! Cannot add more products.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        int productId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter product name: ");
        String productName = sc.nextLine();
        System.out.print("Enter product price: ");
        double productPrice = sc.nextDouble();

        products[count] = new Product(productId, productName, productPrice);
        count++;
        System.out.println("Product added successfully!");
    }

    void removeProduct() {
        if (count == 0) {
            System.out.println("No products to remove!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        listProducts();
        System.out.print("Enter product ID to remove: ");
        int productId = sc.nextInt();

        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getProductId() == productId) {
                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                }
                products[count - 1] = null;
                count--;
                System.out.println("Product removed successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    void listProducts() {
        if (count == 0) {
            System.out.println("No Products Found! Add products first.");
            return;
        }
        System.out.println("\n--- Product List ---");
        for (int i = 0; i < count; i++) {
            if (products[i] != null) {
                System.out.println("ID: " + products[i].getProductId() +
                        " | Name: " + products[i].getProductName() +
                        " | Price: " + products[i].getProductPrice());
            }
        }
    }

    void changeProductPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product ID: ");
        int productId = sc.nextInt();
        System.out.print("Enter new product price: ");
        double productPrice = sc.nextDouble();

        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getProductId() == productId) {
                products[i].setProductPrice(productPrice);
                System.out.println("Price updated successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    Product findProductById(int productId) {
        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getProductId() == productId) {
                return products[i];
            }
        }
        return null;
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

    void addProdToCart(StoreManager sm) {
        if (count >= products.length) {
            System.out.println("Cart is full! Cannot add more products.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        sm.listProducts();
        System.out.print("Enter product ID to add to cart: ");
        int productId = sc.nextInt();

        Product storeProduct = sm.findProductById(productId);
        if (storeProduct != null) {
            products[count] = storeProduct;
            count++;
            System.out.println("Product added to cart!");
        } else {
            System.out.println("Product not found in store.");
        }
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
