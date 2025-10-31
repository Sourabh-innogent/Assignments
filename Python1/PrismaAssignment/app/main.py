from fastapi import FastAPI
from unicodedata import category

from app.core.database import db
from app.routes import product_routes, company_routes, category_routes

app = FastAPI(
    title="Product Management System (Prisma + FastAPI)",
    description="API for managing products, categories, and companies using Prisma ORM.",
    version="1.0.0"
)

#  Include routers right after app creation
app.include_router(product_routes.router)
app.include_router(company_routes.router)
app.include_router(category_routes.router)



# ⚙️ Database lifecycle events
@app.on_event("startup")
async def startup():
    await db.connect()
    print(" Database connected successfully.")


@app.on_event("shutdown")
async def shutdown():
    await db.disconnect()
    print(" Database disconnected.")


# 🏠 Root endpoint
@app.get("/")
async def root():
    return {"message": "Hello "}


# 👋 Example dynamic route
@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}
