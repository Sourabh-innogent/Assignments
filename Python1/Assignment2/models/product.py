# models/product.py

class Product:
    def __init__(self, name, price, stock, shelf, tags):
        self.name = name
        self.price = price
        self.stock = stock
        self.shelf = shelf
        self.tags = set(tags)

    def value(self):
        return self.price * self.stock

    def describe(self):
        return (f"{self.name} — Price ₹{self.price}, Stock: {self.stock}, "
                f"Shelf: {self.shelf}, Tags: {self.tags}")

    def apply_discount(self, percent):
        old_price = self.price
        self.price = self.price - (self.price * percent / 100)
        print(f" Discount applied on {self.name}: {old_price} → {self.price}")
