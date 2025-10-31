from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from db.database import get_db
from schemas.category_schema import CategoryCreate, CategoryResponse
from crud.category_crud import (
    create_category,
    get_categories,
    get_category_by_id,
    update_category,
    delete_category
)

router = APIRouter(prefix="/categories", tags=["Categories"])


#  Create a category
@router.post("/", response_model=CategoryResponse)
def create_category_route(category: CategoryCreate, db: Session = Depends(get_db)):
    try:
        return create_category(db, category)
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Error creating category: {str(e)}")


# Get all categories
@router.get("/", response_model=list[CategoryResponse])
def get_all_categories_route(db: Session = Depends(get_db)):
    return get_categories(db)


#  Get category by ID
@router.get("/{category_id}", response_model=CategoryResponse)
def get_category_by_id_route(category_id: int, db: Session = Depends(get_db)):
    category = get_category_by_id(db, category_id)
    if not category:
        raise HTTPException(status_code=404, detail="Category not found")
    return category


#  Update category
@router.put("/{category_id}", response_model=CategoryResponse)
def update_category_route(category_id: int, category: CategoryCreate, db: Session = Depends(get_db)):
    updated = update_category(db, category_id, category)
    if not updated:
        raise HTTPException(status_code=404, detail="Category not found for update")
    return updated


#  Delete category
@router.delete("/{category_id}")
def delete_category_route(category_id: int, db: Session = Depends(get_db)):
    deleted = delete_category(db, category_id)
    if not deleted:
        raise HTTPException(status_code=404, detail="Category not found")
    return {"message": "Category deleted successfully"}
