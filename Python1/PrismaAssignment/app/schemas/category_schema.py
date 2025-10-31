from typing import List, Optional
from pydantic import BaseModel
from app.schemas.product_schema import  ProductResponse

class CategoryBase(BaseModel):
    name: str

class CategoryCreate(CategoryBase):
    pass

class CategoryResponse(CategoryBase):
    id: int
    products: Optional[List[ProductResponse]] = []  # use ProductResponse instead of SQLAlchemy Product

    class Config:
        orm_mode = True
