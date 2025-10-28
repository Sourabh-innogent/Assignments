# services/inventory_service.py
from models.product import Product
from models.food_product import FoodProduct

class InventoryService:
    def __init__(self):
        self.products = []

    def add_product(self, product):
        for p in self.products:
            if p.name == product.name:
                print(f" Product '{product.name}' already exists!")
                return
        self.products.append(product)
        print(f" Added product: {product.name}")

    def show_all(self):
        for p in self.products:
            print(p.describe())

    def low_stock(self):
        found = False
        for p in self.products:
            if p.stock < 5:
                print(f"{p.name} — only {p.stock} left!")
                found = True
        if not found:
            print("All products have sufficient stock.")

    def delete_product(self, name):
        for p in self.products:
            if p.name == name:
                self.products.remove(p)
                print(f" {name} removed.")
                return
        print(f" Product '{name}' not found.")

    def total_value(self):
        total = sum(p.value() for p in self.products)
        print(f"\n Total Inventory Value: ₹{total}")

    def apply_clearance_discount(self, tags):
        
        for p in self.products:
            if ("clearance" in p.tags) and (p.tags == tags):
                p.apply_discount(50)
                return
        print("No Discount")

        
            
