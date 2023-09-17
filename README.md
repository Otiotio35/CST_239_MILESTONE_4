Theory of Operation: Store Front System
Overview:
The Store Front System is designed to simulate a virtual marketplace where users can interact with the inventory, add items to their shopping cart, and checkout. This system is composed of several integrated components: StoreFront, InventoryManager, ShoppingCart, and various Salable items.

Components:
Salable:

This is the base class representing any item that can be sold in the store.
Attributes include type, name, description, price, and quantity.
Different products, like Weapon, Armor, and Health, inherit from Salable, showcasing the object-oriented principle of inheritance.
InventoryManager:

Manages the store's inventory, tracking available Salable items.
Allows for the addition and removal of products.
Typically, the inventory is loaded from a file, as suggested by the "InFile.txt" reference, although the actual file handling operations were not shown in the provided details.
ShoppingCart:

Represents a user's cart.
Allows users to add or remove items.
Provides a total price calculation based on items in the cart.
StoreFront:

Represents the main interface where users interact with the store.
Displays welcoming messages and provides a menu for actions like viewing inventory, adding/removing items from the cart, and checkout.
Internally, it interacts with InventoryManager to handle inventory operations and ShoppingCart for cart operations.
Operational Flow:
Initialization:

The StoreFront is initialized with a store name.
The InventoryManager is initiated with an inventory file ("InFile.txt").
A ShoppingCart instance is created for the user.
User Interaction:

The StoreFront displays a menu to the user with options like viewing the inventory, managing the cart, and proceeding to checkout.
Based on user choice, the appropriate actions are triggered, involving interactions with the InventoryManager and ShoppingCart.
Shopping Process:

Users can browse items through the InventoryManager.
They can add selected items to their ShoppingCart.
During this process, the InventoryManager updates the quantity of items based on purchases.
Checkout:

Users can review their cart.
On confirmation, the total price is calculated, and the checkout process completes.
Inheritance in the System:
The system utilizes object-oriented principles, chiefly inheritance, to manage different types of products. At the heart of this is the Salable class, a generalized representation of any item for sale. Specific product types like Weapon, Armor, and Health extend Salable, inheriting its attributes and methods, while adding their unique features (e.g., power for Weapon).
