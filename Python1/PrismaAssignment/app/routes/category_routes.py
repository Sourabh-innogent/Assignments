from fastapi import APIRouter, HTTPException, status
from prisma import Prisma
from app.schemas.category_schema import CategoryCreate, CategoryResponse
from app.core.database import db
router = APIRouter(prefix="/categories", tags=["Categories"])

#  Create Category
@router.post("/", response_model=CategoryResponse, status_code=status.HTTP_201_CREATED)
async def create_category(category: CategoryCreate):
    existing = await db.category.find_unique(where={"name": category.name})
    if existing:
        raise HTTPException(status_code=status.HTTP_409_CONFLICT, detail="Category already exists")

    new_category = await db.category.create(data={"name": category.name})
    return new_category

#  Get All Categories
@router.get("/", response_model=list[CategoryResponse])
async def get_categories():
    return await db.category.find_many(include={"products": True})

#  Get Category by ID
@router.get("/{category_id}", response_model=CategoryResponse)
async def get_category(category_id: int):
    category = await db.category.find_unique(
        where={"id": category_id},
        include={"products": True}
    )
    if not category:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Category not found")
    return category

#  Update Category
@router.put("/{category_id}", response_model=CategoryResponse)
async def update_category(category_id: int, data: CategoryCreate):
    existing = await db.category.find_unique(where={"id": category_id})
    if not existing:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Category not found")

    updated = await db.category.update(
        where={"id": category_id},
        data={"name": data.name}
    )
    return updated

#  Delete Category
@router.delete("/{category_id}", status_code=status.HTTP_200_OK)
async def delete_category(category_id: int):
    existing = await db.category.find_unique(where={"id": category_id})
    if not existing:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Category not found")

    await db.category.delete(where={"id": category_id})
    return {"message": "Category deleted successfully"}
