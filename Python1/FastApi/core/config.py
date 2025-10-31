import os
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()


class Settings:
    DB_USER: str = os.getenv("DB_USER")
    DB_PASSWORD: str = os.getenv("DB_PASSWORD")
    DB_HOST: str = os.getenv("DB_HOST")
    DB_PORT: str = os.getenv("DB_PORT")
    DB_NAME: str = os.getenv("DB_NAME")
    DATABASE_URL: str = os.getenv("DATABASE_URL")

setting = Settings()