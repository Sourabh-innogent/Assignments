
from fastapi import APIRouter, status
from app.schemas.product_schema import ProductCreate, ProductResponse
from app.crud import product_crud as p

router = APIRouter(prefix="/products", tags=["Products"])

#  Create Product
@router.post("/", response_model=ProductResponse, status_code=status.HTTP_201_CREATED)
async def create_product_route(product: ProductCreate):
    return await p.create_product(product)


#  Get All Products
@router.get("/", response_model=list[ProductResponse])
async def get_all_products_route():
    return await p.get_all_products()


# Get by ID
@router.get("/{product_id}", response_model=ProductResponse)
async def get_product_by_id_route(product_id: int):
    return await p.get_product_by_id(product_id)


#  Search by Name
@router.get("/search/name/{name}", response_model=list[ProductResponse])
async def search_by_name(name: str):
    return await p.get_product_by_name(name)


#  Search by Price
@router.get("/search/price/{price}", response_model=list[ProductResponse])
async def search_by_price(price: float):
    return await p.get_product_by_price(price)


#  Search by Company
@router.get("/search/company/{company}", response_model=list[ProductResponse])
async def search_by_company(company: str):
    return await p.get_products_by_company(company)


#  Search by Category
@router.get("/search/category/{category}", response_model=list[ProductResponse])
async def search_by_category(category: str):
    return await p.get_products_by_category(category)


#  Update Product
@router.put("/{product_id}", response_model=ProductResponse)
async def update_product_route(product_id: int, product: ProductCreate):
    return await p.update_product(product_id, product)


#  Delete Product
@router.delete("/{product_id}",status_code=status.HTTP_200_OK)
async def delete_product_route(product_id: int):
    return await p.delete_product(product_id)
