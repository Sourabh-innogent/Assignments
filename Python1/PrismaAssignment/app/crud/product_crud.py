from starlette import status

from app.schemas.product_schema import *
from app.core.database import db
from fastapi import HTTPException

async def create_product(product: ProductCreate):
    existing_product = await db.product.find_first(
        where={"name": product.name}
    )
    if existing_product:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail="Product already exists"
        )

    new_product = await db.product.create(
        data={
            "name": product.name,
            "price": product.price,
            "quantity": product.quantity,
            "companyId": product.company_id,
            "categoryId": product.category_id
        }
    )
    return new_product


#  Get All Products
async def get_all_products():
    products = await db.product.find_many(
        include={"company": True, "category": True}
    )
    if not products:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="No products found"
        )
    return products


#  Get Product by ID
async def get_product_by_id(product_id: int):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Product not found"
        )
    return product


# Get Product by Name
async def get_product_by_name(name: str):
    products = await db.product.find_many(
        where={"name": {"contains": name}},
        include={"company": True, "category": True}
    )
    if not products:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"No products found with name like '{name}'"
        )
    return products


#  Get Product by Price
async def get_product_by_price(price: float):
    products = await db.product.find_many(where={"price": price})
    if not products:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"No products found with price {price}"
        )
    return products


# Get Products by Company Name
async def get_products_by_company(company_name: str):
    company = await db.company.find_first(where={"name": company_name})
    if not company:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Company '{company_name}' not found"
        )

    products = await db.product.find_many(where={"companyId": company.id})
    if not products:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"No products found for company '{company_name}'"
        )
    return products


# Get Products by Category Name
async def get_products_by_category(category_name: str):
    category = await db.category.find_first(where={"name": category_name})
    if not category:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"Category '{category_name}' not found"
        )

    products = await db.product.find_many(where={"categoryId": category.id})
    if not products:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail=f"No products found in category '{category_name}'"
        )
    return products


#  Update Product
async def update_product(product_id: int, product_data: ProductCreate):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Product not found"
        )

    updated_product = await db.product.update(
        where={"id": product_id},
        data={
            "name": product_data.name,
            "price": product_data.price,
            "quantity": product_data.quantity,
            "companyId": product_data.company_id,
            "categoryId": product_data.category_id
        }
    )
    return updated_product


# Delete Product
async def delete_product(product_id: int):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Product not found"
        )

    await db.product.delete(where={"id": product_id})
    return {"message": "Product deleted successfully"}