package inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import salable.Salable;

/**
 * Manages the inventory of Salable products. Handles operations such as adding,
 * removing, purchasing, and displaying products. Also manages file operations
 * for the inventory.
 *
 * @version 09/17/2023 ID: 21024608
 * @author toafik otiotio
 */

public class InventoryManager {
	/** Map to store products with their names as keys. */
	private Map<String, Salable> products;
	/** Path to the file where the inventory is stored. */
	private String filePath;
	/** Service to handle file operations. */
	private FileService fileService;

	/**
	 * Constructor initializes an inventory manager with the specified file path.
	 *
	 * @param filePath Path to the file containing inventory data.
	 */
	public InventoryManager(String filePath) {
		this.products = new HashMap<>();
		this.filePath = filePath;
		this.fileService = new FileService();

		loadInventory();
	}

	/**
	 * Loads the inventory from the specified file.
	 */
	private void loadInventory() {
		try {
			String fileData = fileService.readFile(filePath);
			String[] lines = fileData.split("\\n");

			for (String line : lines) {
				processInventoryLine(line);
			}
		} catch (IOException e) {
			System.err.println("Error loading inventory: " + e.getMessage());
		}
	}

	/**
	 * Processes a single line from the inventory file to extract product
	 * information.
	 *
	 * @param line A line from the inventory file.
	 */
	private void processInventoryLine(String line) {
		String[] parts = line.split("\\|"); // Split using the pipe delimiter
		if (parts.length == 5) {
			String type = parts[0].trim().substring("Type: ".length()); // Extract type
			String name = parts[1].trim().substring("Name: ".length()); // Extract name
			String description = parts[2].trim().substring("Description: ".length()); // Extract description
			double price = Double.parseDouble(parts[3].trim().substring("Price: ".length())); // Extract price
			int quantity = Integer.parseInt(parts[4].trim().substring("Quantity: ".length())); // Extract quantity

			Salable item = new Salable(type, name, description, price, quantity);
			products.put(name.toLowerCase(), item);
		}
	}

	/**
	 * Returns a list of all products in the inventory.
	 *
	 * @return List of Salable products.
	 */
	public List<Salable> getInventory() {
		return new ArrayList<>(products.values());
	}

	/**
	 * Fetches a product from the inventory by its name.
	 *
	 * @param name Name of the product to be fetched.
	 * @return Salable product or null if not found.
	 */

	public Salable getProduct(String name) {
		return products.get(name.toLowerCase());
	}

	/**
	 * Adds a new product to the inventory.
	 *
	 * @param product Salable product to be added.
	 */
	public void addProduct(Salable product) {
		products.put(product.getName().toLowerCase(), product);
	}

	/**
	 * Removes a specified quantity of a product from the inventory. Note: This
	 * method currently removes the product entirely regardless of quantity.
	 *
	 * @param product  Salable product to be removed.
	 * @param quantity Number of products to remove.
	 */
	public void removeProduct(Salable product, int quantity) {
		products.remove(product.getName().toLowerCase());
	}

	/**
	 * Purchases a specified quantity of a product from the inventory.
	 *
	 * @param name     Name of the product to purchase.
	 * @param quantity Quantity of the product to purchase.
	 * @return True if the purchase is successful, false otherwise.
	 */
	public boolean purchaseProduct(String name, int quantity) {
		Salable product = getProduct(name);
		if (product != null && product.getQuantity() >= quantity) {
			product.setQuantity(product.getQuantity() - quantity);
			return true;
		}
		return false;
	}

	/**
	 * Cancels a purchase, effectively increasing the quantity of the product back
	 * in the inventory.
	 *
	 * @param name     Name of the product whose purchase is to be cancelled.
	 * @param quantity Quantity of the product for which the purchase is cancelled.
	 * @return True if the cancellation is successful, false otherwise.
	 */
	public boolean cancelPurchase(String name, int quantity) {
		Salable product = getProduct(name);
		if (product != null) {
			product.setQuantity(product.getQuantity() + quantity);
			return true;
		}
		return false;
	}

	/**
	 * Displays the current state of the inventory to the console.
	 */
	public void displayInventory() {
		System.out.println("Current Inventory:");
		System.out.printf("%-10s %-15s %-30s %-10s %-8s%n", "Type", "Name", "Description", "Price", "Quantity");
		for (Salable product : products.values()) {
			System.out.printf("%-10s %-15s %-30s $%-9.2f %-8d%n", product.getType(), product.getName(),
					product.getDescription(), product.getPrice(), product.getQuantity());
		}
	}

	/**
	 * Saves the current state of the inventory to the specified file.
	 */
	public void saveInventoryToFile() {
		StringBuilder fileData = new StringBuilder();
		for (Salable product : products.values()) {
			String line = String.format("Type: %s|Name: %s|Description: %s|Price: %.2f|Quantity: %d", product.getType(),
					product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
			fileData.append(line).append("\n");
		}

		try {
			fileService.writeFile(filePath, fileData.toString());
		} catch (IOException e) {
			System.err.println("Error saving inventory: " + e.getMessage());
		}
	}
}
