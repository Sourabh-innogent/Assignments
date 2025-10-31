
from pydantic import BaseModel

class ProductBase(BaseModel):
    name: str
    price: float
    quantity: int


class ProductCreate(ProductBase):
    company_id: int
    category_id: int


class ProductResponse(ProductBase):
    id: int
    companyId: int
    categoryId: int

    class Config:
        orm_mode = True
