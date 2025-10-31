from fastapi import HTTPException
from sqlalchemy.orm import Session
from starlette import status

from models.company import Company
from schemas.company_schema import CompanyCreate
import exception

def create_company(db: Session, company: CompanyCreate):
    existing_company = db.query(Company).filter(Company.name == company.name).first()
    if existing_company:
        raise HTTPException(status_code=status.HTTP_409_CONFLICT, detail="Company already exists")
    new_company = Company(name=company.name, location=company.location)
    db.add(new_company)
    db.commit()
    db.refresh(new_company)
    return new_company


def get_all_companies(db: Session):
    return db.query(Company).all()

def get_company_by_id(db: Session, company_id: int):
    company = db.query(Company).filter(Company.id == company_id).first()
    if not company:
        raise exception.CompanyNotFoundError()
    return company

def update_company(db: Session, company_id: int, updated_data: CompanyCreate):
    company = db.query(Company).filter(Company.id == company_id).first()
    if not company:
        raise exception.CompanyNotFoundError()
    for key, value in updated_data.model_dump().items():
        setattr(company, key, value)

    db.commit()
    db.refresh(company)
    return company

def delete_company(db: Session, company_id: int):
    company = db.query(Company).filter(Company.id == company_id).first()
    if not company:
        raise exception.CompanyNotFoundError()

    db.delete(company)
    db.commit()
    return {"message": "Company deleted successfully"}