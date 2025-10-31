from fastapi import HTTPException, status
from prisma import Prisma
from app.schemas.category_schema import CategoryCreate

db = Prisma()

# Create a new category
async def create_category(category: CategoryCreate):
    existing_category = await db.category.find_unique(where={"name": category.name})
    if existing_category:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail="Category already exists"
        )

    new_category = await db.category.create(
        data={"name": category.name}
    )
    return new_category


#  Get all categories
async def get_categories():
    categories = await db.category.find_many(include={"products": True})
    if not categories:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="No categories found"
        )
    return categories


#  Get category by ID
async def get_category_by_id(category_id: int):
    category = await db.category.find_unique(
        where={"id": category_id},
        include={"products": True}
    )
    if not category:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Category not found"
        )
    return category


#  Update category
async def update_category(category_id: int, updated_data: CategoryCreate):
    category = await db.category.find_unique(where={"id": category_id})
    if not category:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Category not found"
        )

    updated_category = await db.category.update(
        where={"id": category_id},
        data={"name": updated_data.name}
    )
    return updated_category


#  Delete category
async def delete_category(category_id: int):
    category = await db.category.find_unique(where={"id": category_id})
    if not category:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail="Category not found"
        )

    await db.category.delete(where={"id": category_id})
    return {"message": "Category deleted successfully"}
