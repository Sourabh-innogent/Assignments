from sqlalchemy.orm import Session
from fastapi import HTTPException
from starlette import status

import exception
from models.product import Product
from schemas.product_schema import  ProductCreate


def create_product(db: Session, product: ProductCreate):
    existing_product = db.query(Product).filter(Product.name == product.name).first()
    if existing_product:
        raise HTTPException(status_code=status.HTTP_409_CONFLICT, detail="Product already exists")

    new_product = Product(
        name=product.name,
        price=product.price,
        quantity=product.quantity,
        company_id=product.company_id,
        category_id=product.category_id
    )
    db.add(new_product)
    db.commit()
    db.refresh(new_product)
    return new_product


def get_all_products(db: Session):
    return db.query(Product).all()


def get_product_by_id(db: Session, product_id: int):
    product = db.query(Product).filter(Product.id == product_id).first()
    if not product:
        raise exception.ProductNotFoundError()
    return product

def get_product_by_name(db: Session, name: str):
    products = db.query(Product).filter(Product.name.ilike(f"%{name}%")).all()
    if not products:
        raise exception.ProductNotFoundError()
    return products


def get_product_by_price(db: Session, price: float):
    products = db.query(Product).filter(Product.price == price).all()
    if not products:
        raise exception.ProductNotFoundError()
    return products

def get_products_by_company(db: Session, company_name: str):
    from models.company import Company

    company = db.query(Company).filter(Company.name == company_name).first()
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")

    products = db.query(Product).filter(Product.category_id == company.id).all()
    if not products:
        raise exception.ProductNotFoundError()
    return products


def get_products_by_category(db: Session, category_name: str):
    from models.category import Category

    category = db.query(Category).filter(Category.name == category_name).first()
    if not category:
        raise HTTPException(status_code=404, detail="Category not found")

    products = db.query(Product).filter(Product.category_id == category.id).all()
    if not products:
        raise exception.ProductNotFoundError()
    return products


def update_product(db: Session, product_id: int, product_data: ProductCreate):
    product = db.query(Product).filter(Product.id == product_id).first()
    if not product:
        raise exception.ProductNotFoundError()

    db_product = Product(**product_data.model_dump())

    db.commit()
    db.refresh(db_product)
    return db_product

def delete_product(db: Session, product_id: int):
    product = db.query(Product).filter(Product.id == product_id).first()
    if not product:
        raise exception.ProductNotFoundError()

    db.delete(product)
    db.commit()
    return {"message": "Product deleted successfully"}