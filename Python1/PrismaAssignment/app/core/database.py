from prisma import Prisma

db = Prisma()

if db.is_connected():
    print("Database OK")
else:
    print("Database Error")