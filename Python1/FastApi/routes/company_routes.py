from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from db.database import get_db
from schemas.company_schema import CompanyCreate, CompanyResponse
from crud.company_crud import (
    create_company,
    get_all_companies,
    get_company_by_id,
    update_company,
    delete_company
)

router = APIRouter(prefix="/companies", tags=["Companies"])


# Create a company
@router.post("/", response_model=CompanyResponse)
def create_company_route(company: CompanyCreate, db: Session = Depends(get_db)):
    try:
        return create_company(db, company)
    except Exception as e:
        raise HTTPException(status_code=400, detail=f"Error creating company: {str(e)}")


# Get all companies
@router.get("/", response_model=list[CompanyResponse])
def get_all_companies_route(db: Session = Depends(get_db)):
    return get_all_companies(db)


# Get company by ID
@router.get("/{company_id}", response_model=CompanyResponse)
def get_company_by_id_route(company_id: int, db: Session = Depends(get_db)):
    company = get_company_by_id(db, company_id)
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")
    return company


# Update company
@router.put("/{company_id}", response_model=CompanyResponse)
def update_company_route(company_id: int, company: CompanyCreate, db: Session = Depends(get_db)):
    updated = update_company(db, company_id, company)
    if not updated:
        raise HTTPException(status_code=404, detail="Company not found for update")
    return updated


# Delete company
@router.delete("/{company_id}")
def delete_company_route(company_id: int, db: Session = Depends(get_db)):
    deleted = delete_company(db, company_id)
    if not deleted:
        raise HTTPException(status_code=404, detail="Company not found")
    return {"message": "Company deleted successfully"}
