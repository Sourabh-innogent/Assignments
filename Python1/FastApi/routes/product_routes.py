from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from db.database import get_db

from schemas.product_schema import ProductCreate, ProductResponse
from crud.product_crud import (
    create_product,
    get_all_products,
    # get_product_by_id,
    # get_product_by_name,
    # get_product_by_price,
    update_product,
    delete_product,
    filter_products
    # get_products_by_company,
    # get_products_by_category, filter_products
)

router = APIRouter(
    prefix="/products",
    tags=["Products"]
)

# Create a new product
@router.post("/", response_model=ProductResponse)
def create_product_route(product: ProductCreate, db: Session = Depends(get_db)):
    try:
        return create_product(db, product)
    except HTTPException as e:
        raise HTTPException(status_code=400, detail=f"Failed to create product: {str(e)}")


#  Get all products
@router.get("/", response_model=list[ProductResponse])
def get_all_products_route(db: Session = Depends(get_db)):
    try:
        products = get_all_products(db)
        if not products:
            raise HTTPException(status_code=404, detail="No products found")
        return products
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error fetching products: {str(e)}")


@router.get("/search", response_model=list[ProductResponse])
def search_products(
    name: str | None = None,
    price: float | None = None,
    category: str | None = None,
    db: Session = Depends(get_db),
):
    return filter_products(db, name, price, category)


#  Get product by ID
# @router.get("/{product_id}", response_model=ProductResponse)
# def get_product_by_id_route(product_id: int, db: Session = Depends(get_db)):
#     try:
#         return get_product_by_id(db, product_id)
#     except HTTPException as e:
#         raise e
#     except Exception as e:
#         raise HTTPException(status_code=500, detail=f"Error fetching product by ID: {str(e)}")
#
#
# #  Search by name
# @router.get("/search/name/{name}", response_model=list[ProductResponse])
# def search_product_by_name_route(name: str, db: Session = Depends(get_db)):
#     try:
#         products = get_product_by_name(db, name)
#         if not products:
#             raise HTTPException(status_code=404, detail="No products found with that name")
#         return products
#     except HTTPException as e:
#         raise e
#     except Exception as e:
#         raise HTTPException(status_code=500, detail=f"Error searching product by name: {str(e)}")
#
#
# #  Search by price
# @router.get("/search/price/{price}", response_model=list[ProductResponse])
# def search_product_by_price_route(price: float, db: Session = Depends(get_db)):
#     try:
#         products = get_product_by_price(db, price)
#         if not products:
#             raise HTTPException(status_code=404, detail="No products found at this price")
#         return products
#     except HTTPException as e:
#         raise e
#     except Exception as e:
#         raise HTTPException(status_code=500, detail=f"Error searching product by price: {str(e)}")
#
# @router.get("/search/category/{category}", response_model=list[ProductResponse])
# def search_product_by_category(category: str, db: Session = Depends(get_db)):
#     try:
#         products = get_products_by_category(db, category)
#         if not products:
#             raise HTTPException(status_code=404, detail="No products found at this price")
#         return products
#     except HTTPException as e:
#         raise e
#     except Exception as e:
#         raise HTTPException(status_code=500, detail=f"Error searching product by price: {str(e)}")
#
#
# @router.get("/search/company/{compnay}", response_model=list[ProductResponse])
# def search_product_by_company(company: str, db: Session = Depends(get_db)):
#     try:
#         products = get_products_by_company(db, company)
#         if not products:
#             raise HTTPException(status_code=404, detail="No products found at this price")
#         return products
#     except HTTPException as e:
#         raise e
#     except Exception as e:
#         raise HTTPException(status_code=500, detail=f"Error searching product by price: {str(e)}")


# Update product
@router.put("/{product_id}", response_model=ProductResponse)
def update_product_route(product_id: int, product: ProductCreate, db: Session = Depends(get_db)):
    try:
        return update_product(db, product_id, product)
    except HTTPException as e:
        raise e
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Failed to update product: {str(e)}")


#  Delete product
@router.delete("/{product_id}")
def delete_product_route(product_id: int, db: Session = Depends(get_db)):
    try:
        delete_product(db, product_id)
        return {"message": "Product deleted successfully"}
    except HTTPException as e:
        raise e
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Failed to delete product: {str(e)}")
