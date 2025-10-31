inventory = [
    {
        "LadyFinger": {
            "price": 50,
            "stock": 10,
            "shelf": "shelf-1",
            "tags": {"vegetable", "fresh"}
        }
    },
    {
        "Coconut Oil": {
            "price": 100,
            "stock": 3,
            "shelf": "shelf-2",
            "tags": {"grocery", "clearance"}
        }
    }
]

def allProducts():
    for product in inventory:
        for name, details in product.items():
            print(f"{name}: Price ₹{details['price']}, Stock {details['stock']}, Shelf {details['shelf']}, Tags {details['tags']}")

def lowStock():
    found = False
    for product in inventory:
        for name, details in product.items():
            if details['stock'] < 5:
                print(f" {name} has low stock — only {details['stock']} left!")
                found = True
    if not found:
        print("All products have sufficient stock.")

def addProduct(product_name, price, stock, shelf, tags):
    for product in inventory:
        if product_name in product:
            print(f" Product '{product_name}' already exists!")
            return
    inventory.append({
        product_name: {
            "price": price,
            "stock": stock,
            "shelf": shelf,
            "tags": set(tags)
        }
    })
    print(f" Product '{product_name}' added successfully!")

def updateProduct(product_name, new_price=None, new_stock=None, new_shelf=None, new_tags=None):
    for product in inventory:
        if product_name in product:
            if new_price is not None:
                product[product_name]["price"] = new_price
            if new_stock is not None:
                product[product_name]["stock"] = new_stock
            if new_shelf is not None:
                product[product_name]["shelf"] = new_shelf
            if new_tags is not None:
                product[product_name]["tags"] = set(new_tags)
            print(f" Updated {product_name}: {product[product_name]}")
            return
    print(f" Product '{product_name}' not found!")

def deleteProduct(product_name):
    for product in inventory:
        if product_name in product:
            inventory.remove(product)
            print(f" {product_name} has been deleted from inventory.")
            return
    print(f" {product_name} not found in inventory.")

def totalInventoryValue():
    total_value = 0
    for product in inventory:
        for name, details in product.items():
            total_value += details["price"] * details["stock"]
    print(f"\n Total inventory value: ₹{total_value}")

def discountedProduct():
   
    found = False
    for product in inventory:
        for name, details in product.items():
            if "clearance" in details.get("tags", []):
                old_price = details["price"]
                new_price = old_price * 0.5
                details["price"] = new_price
                print(f" {name}: Old ₹{old_price} → New ₹{new_price} (Shelf: {details['shelf']})")
                found = True
    if not found:
        print("No Discounted products found.")

def menu():
    while True:
        print("""
======== INVENTORY MENU ========
1  List all products
2  Show low stock warnings
3  Add new product
4  Update existing product
5  Delete a product
6  Print total inventory value
7  Apply 50% discount on clearance items
8  Exit
================================
        """)

        choice = input("Enter your choice (1-8): ")

        if choice == "1":
            allProducts()

        elif choice == "2":
            lowStock()

        elif choice == "3":
            name = input("Enter product name: ")
            price = float(input("Enter price: "))
            stock = int(input("Enter stock: "))
            shelf = input("Enter shelf (e.g., shelf-1): ")
            tags = input("Enter tags (comma-separated): ").split(",")
            addProduct(name, price, stock, shelf, tags)

        elif choice == "4":
            name = input("Enter product name to update: ")
            price = input("Enter new price (or press Enter to skip): ")
            stock = input("Enter new stock (or press Enter to skip): ")
            shelf = input("Enter new shelf (or press Enter to skip): ")
            tags = input("Enter new tags (comma-separated or press Enter to skip): ")
            updateProduct(
                name,
                float(price) if price else None,
                int(stock) if stock else None,
                shelf if shelf else None,
                tags.split(",") if tags else None
            )

        elif choice == "5":
            name = input("Enter product name to delete: ")
            deleteProduct(name)

        elif choice == "6":
            totalInventoryValue()

        elif choice == "7":
            discountedProduct()

        elif choice == "8":
            print(" Exiting Inventory System. Goodbye!")
            break

        else:
            print(" Invalid choice. Please enter a number between 1 and 8.")

menu()
