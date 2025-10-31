from fastapi import HTTPException
from starlette import status

import exception
from sqlalchemy.orm import Session
from models.category import Category
from schemas.category_schema import CategoryCreate

def create_category(db: Session, category: CategoryCreate):
    existing_category = db.query(Category).filter(Category.name == category.name).first()
    if existing_category:
        raise HTTPException(status_code=status.HTTP_409_CONFLICT, detail="Category already exists")
    new_category = Category(name=category.name)
    db.add(new_category)
    db.commit()
    db.refresh(new_category)
    return new_category


def get_categories(db: Session):
    return db.query(Category).all()


def get_category_by_id(db: Session, category_id: int):
    category = db.query(Category).filter(Category.id == category_id).first()
    if not category:
        raise exception.CategoryNotFoundError()
    return category

def update_category(db: Session, category_id: int, updated_data: CategoryCreate):
    category = db.query(Category).filter(Category.id == category_id).first()
    if category:
        category.name = updated_data.name
        db.commit()
        db.refresh(category)
        return category
    raise exception.CategoryNotFoundError()


def delete_category(db: Session, category_id: int):
    category = db.query(Category).filter(Category.id == category_id).first()
    if category:
        db.delete(category)
        db.commit()
        return True
    return False