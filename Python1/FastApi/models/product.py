from sqlalchemy import Column, Integer, String, Float, ForeignKey
from sqlalchemy.orm import relationship
from db.database import Base


class Product(Base):
    __tablename__ = "product"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(100) , nullable=False)
    price = Column(Float)
    quantity = Column(Integer)
    company_id = Column(Integer, ForeignKey("company.id"))
    category_id = Column(Integer, ForeignKey("category.id"))

    company = relationship("Company", back_populates="products")
    category = relationship("Category", back_populates="products")