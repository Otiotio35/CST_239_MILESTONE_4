package Menu;

import java.util.Scanner;

import inventory.InventoryManager;
import salable.Salable;
import shopping.ShoppingCart;
/**
 * Represents the main interface where users can interact with the store.
 */
public class StoreFront {
    private String name;
    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;
    private Scanner scanner;
    /**
     * Initializes a new StoreFront with the given name.
     *
     * @param name The name of the store.
     */
    public StoreFront(String name) {
        this.name = name;
        this.inventoryManager = new InventoryManager("InFile.txt"); // Provide the file path
        this.shoppingCart = new ShoppingCart();
        this.scanner = new Scanner(System.in);

        displayWelcomeMessage();
        run();
        scanner.close();
    }

    /**
     * Purchases a specified quantity of a given product.
     *
     * @param product  The product to purchase.
     * @param quantity The quantity of the product to purchase.
     */
    public void purchaseProduct(Salable product, int quantity) {
        shoppingCart.addItem(product, quantity);
        inventoryManager.removeProduct(product, quantity);
    }
    /**
     * Cancels the purchase of a given product and returns it back to the inventory.
     *
     * @param product The product whose purchase is to be cancelled.
     */
    public void cancelPurchase(Salable product) {
        shoppingCart.removeItem(product);
        inventoryManager.addProduct(product);
    }
    /**
     * Provides a view of the items currently in the shopping cart.
     */
    public void viewCart() {
        shoppingCart.viewCart();
    }
    /**
     * Handles the checkout process, displaying the current items in the cart
     * and the total price. Asks the user for confirmation before completing the checkout.
     */
    public void checkout() {
        System.out.println("===== Checkout =====");
        shoppingCart.viewCart();
        double totalPrice = shoppingCart.getTotalPrice();
        if (totalPrice > 0) {
            System.out.println("Total Price: $" + totalPrice);
            System.out.print("Confirm checkout (y/n): ");
            String confirm = scanner.next().toLowerCase();
            if (confirm.equals("y")) {
                System.out.println("Checkout completed!");
                shoppingCart.clearCart();
            } else {
                System.out.println("Checkout canceled.");
            }
        } else {
            System.out.println("Cart is empty. Nothing to checkout.");
        }
    }
    /**
     * Displays a welcoming message to users upon their entrance to the store interface.
     */
    public void displayWelcomeMessage() {
        System.out.println("WELCOME TO " + name + "!");
    }
    /**
     * Handles the checkout process for the user's cart. This method will display the cart,
     * compute the total price, and ask the user to confirm the purchase.
     */
    public void displayMenu() {
        System.out.println("===== Actions =====");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Item to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
    }

/**
 * Contains the main logic for running the store interface
 * and continuously interacting with the user through various menu options.
 */
    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    inventoryManager.displayInventory();
                    break;
                case 2:
                    scanner.nextLine();  // Clearing the buffer
                    System.out.print("Enter the item name to add to the cart: ");
                    String itemName = scanner.nextLine();
                    Salable item = inventoryManager.getProduct(itemName);
                    if (item != null) {
                        System.out.print("Enter the quantity of " + item.getName() + " you wish to buy: ");
                        int quantity = scanner.nextInt();
                        if (quantity > 0 && quantity <= item.getQuantity()) {
                            purchaseProduct(item, quantity);
                            System.out.println(quantity + "x " + item.getName() + " added to the cart.");
                        } else {
                            System.out.println("Invalid quantity. Please try again.");
                        }
                    } else {
                        System.out.println("Item not found in the inventory.");
                    }
                    break;
                case 3:
                    shoppingCart.viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    System.out.println("Thank you for using the StoreFront!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * The main entry point for the StoreFront application.
     * Initializes the store interface and starts user interaction.
     *
     * @param args Command-line arguments (not used in this application).
     */

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        StoreFront storeFront = new StoreFront("TAOFIK OTIOTIO INTERNATIONAL FOOD STORE");
    }
}
