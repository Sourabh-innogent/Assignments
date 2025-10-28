# models/food_product.py
from models.product import Product

class FoodProduct(Product):
    def __init__(self, name, price, stock, shelf, tags, expiry_date):
        super().__init__(name, price, stock, shelf, tags)
        self.expiry_date = expiry_date

    def describe(self):
        desciption = super().describe()
        return f"{desciption}, Expiry Date: {self.expiry_date}"
