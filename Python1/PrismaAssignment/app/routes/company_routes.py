from fastapi import APIRouter, HTTPException, status
from prisma import Prisma
from app.schemas.company_schema import CompanyCreate, CompanyResponse
from app.core.database import db

router = APIRouter(prefix="/companies", tags=["Companies"])


#  Create Company
@router.post("/", response_model=CompanyResponse, status_code=status.HTTP_201_CREATED)
async def create_company(company: CompanyCreate):
    existing = await db.company.find_unique(where={"name": company.name})
    if existing:
        raise HTTPException(status_code=status.HTTP_409_CONFLICT, detail="Company already exists")

    new_company = await db.company.create(data={
        "name": company.name,
        "location": company.location
    })
    return new_company

#  Get All Companies
@router.get("/", response_model=list[CompanyResponse])
async def get_companies():
    companies = await db.company.find_many()
    return companies

#  Get Company by ID
@router.get("/{company_id}", response_model=CompanyResponse)
async def get_company(company_id: int):
    company = await db.company.find_unique(where={"id": company_id})
    if not company:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Company not found")
    return company

#  Update Company
@router.put("/{company_id}", response_model=CompanyResponse)
async def update_company(company_id: int, data: CompanyCreate):
    existing = await db.company.find_unique(where={"id": company_id})
    if not existing:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Company not found")

    updated = await db.company.update(
        where={"id": company_id},
        data={"name": data.name, "location": data.location}
    )
    return updated

#  Delete Company
@router.delete("/{company_id}", status_code=status.HTTP_200_OK)
async def delete_company(company_id: int):
    existing = await db.company.find_unique(where={"id": company_id})
    if not existing:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Company not found")

    await db.company.delete(where={"id": company_id})
    return {"message": "Company deleted successfully"}
