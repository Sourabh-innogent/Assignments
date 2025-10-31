from fastapi import FastAPI
from routes import company_routes, product_routes, category_routes
from db import database
import logging
from fastapi import Request

database.Base.metadata.create_all(bind=database.engine)

app = FastAPI(title="Product Management API")

# Include routes
app.include_router(company_routes.router)
app.include_router(product_routes.router)
app.include_router(category_routes.router)

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)

@app.middleware("http")
async def log_requests(request: Request, call_next):
    logging.info(f"Incoming request: {request.method} {request.url}")
    response = await call_next(request)
    logging.info(f"Response status: {response.status_code}")
    return response


@app.get("/")
def root():
    return {"message": "Welcome to Product Management API "}
