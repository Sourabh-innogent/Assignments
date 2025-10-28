# main.py
from models.product import Product
from models.food_product import FoodProduct
from services.inventory_service import InventoryService
from stats.report import show_stats
def menu():
    inventory = InventoryService()

    # Preload some items
    inventory.add_product(Product("Coconut Oil", 100, 3, "shelf-2", ["grocery", "clearance"]))
    inventory.add_product(FoodProduct("LadyFinger", 50, 10, "shelf-1", ["vegetable", "fresh"], "2025-12-01"))

    while True:
        print("""
======== INVENTORY MENU ========
1  List all products
2  Show low stock warnings
3  Add new product
4  Delete a product
5  Print total inventory value
6  Apply 50% discount on clearance items
7  Check Stats
8  Exit
================================
        """)
        choice = input("Enter your choice: ")

        if choice == "1":
            inventory.show_all()

        elif choice == "2":
            inventory.low_stock()

        elif choice == "3":
            name = input("Enter product name: ")
            price = float(input("Enter price: "))
            stock = int(input("Enter stock: "))
            shelf = input("Enter shelf: ")
            tags = input("Enter tags (comma-separated): ").split(",")
            expiry = input("Enter expiry date (or press Enter if not food): ")
            
            if expiry:
                product = FoodProduct(name, price, stock, shelf, tags, expiry)
            else:
                product = Product(name, price, stock, shelf, tags)
            
            inventory.add_product(product)

        elif choice == "4":
            name = input("Enter product name to delete: ")
            inventory.delete_product(name)

        elif choice == "5":
            inventory.total_value()

        elif choice == "6":
            tags = input("Enter name of tags you want to apply: ")
            inventory.apply_clearance_discount(tags)

        elif choice == "7":
            show_stats(inventory.products)

        elif choice == "8":
            print("👋 Exiting Inventory System.")
            break

        else:
            print(" Invalid choice!")

if __name__ == "__main__":
    menu()
