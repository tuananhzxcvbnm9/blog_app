# Fullstack Blog App (Spring Boot 3 + React + JWT)

## Kiến trúc
- Backend: Spring Boot 3, layered architecture, JWT RBAC
- Frontend: React + Vite + Tailwind + Zustand
- DB: PostgreSQL

## Chạy local nhanh
1. `docker compose up -d db`
2. Backend:
   - `cd backend && mvn spring-boot:run`
3. Frontend:
   - `cd frontend && npm install && npm run dev`

Tài khoản seed:
- admin/admin123

Swagger: http://localhost:8080/swagger-ui/index.html
