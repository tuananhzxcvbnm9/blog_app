# Fullstack Blog App (Spring Boot 3 + React + PostgreSQL)

## Kiến trúc
- Backend: Spring Boot 3, JPA, Security JWT, Flyway
- Frontend: React + Vite + Tailwind + Zustand
- DB: PostgreSQL

## Chạy local
1. `docker compose up -d postgres`
2. Backend
   - `cd backend`
   - `mvn spring-boot:run`
3. Frontend
   - `cd frontend`
   - `npm install`
   - `npm run dev`

## Tài khoản seed
- admin@blog.local / Admin@123

## Swagger
- http://localhost:8080/swagger-ui/index.html
