class ProductNotFoundError(Exception):
    def __init__(self, message: str = "Product not found"):
        self.message = message
        super().__init__(self.message)

class DuplicateProduct(Exception):
    def __init__(self, message: str = "Product with this name already exists."):
        self.message = message
        super().__init__(self.message)

class CategoryNotFoundError(Exception):
    def __init__(self, message: str = "Category not found"):
        self.message = message
        super().__init__(self.message)

class CompanyNotFoundError(Exception):
    def __init__(self, message: str = "Company not found"):
        self.message = message
        super().__init__(self.message)