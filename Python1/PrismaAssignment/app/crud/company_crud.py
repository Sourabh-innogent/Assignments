from fastapi import HTTPException, status
from prisma import Prisma
from app.schemas.company_schema import CompanyCreate

db = Prisma()

#  Create a new company
async def create_company(company: CompanyCreate):
    existing_company = await db.company.find_unique(where={"name": company.name})
    if existing_company:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail="Company already exists"
        )

    new_company = await db.company.create(
        data={"name": company.name, "location": company.location}
    )
    return new_company


#  Get all companies
async def get_all_companies():
    companies = await db.company.find_many(include={"products": True})
    if not companies:
        raise HTTPException(status_code=404, detail="No companies found")
    return companies


#  Get company by ID
async def get_company_by_id(company_id: int):
    company = await db.company.find_unique(
        where={"id": company_id},
        include={"products": True}
    )
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")
    return company


#  Update company
async def update_company(company_id: int, updated_data: CompanyCreate):
    company = await db.company.find_unique(where={"id": company_id})
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")

    updated_company = await db.company.update(
        where={"id": company_id},
        data={
            "name": updated_data.name,
            "location": updated_data.location
        }
    )
    return updated_company


#  Delete company
async def delete_company(company_id: int):
    company = await db.company.find_unique(where={"id": company_id})
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")

    await db.company.delete(where={"id": company_id})
    return {"message": "Company deleted successfully"}
