from sqlalchemy import Column, Integer, String, Float
from sqlalchemy.orm import relationship
from db.database import Base

class Company(Base):
    __tablename__ = "company"
    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(100), nullable=False)
    location = Column(String(100))

    products = relationship("Product", back_populates="company")