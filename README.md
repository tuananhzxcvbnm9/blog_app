# Blog App Fullstack (Spring Boot 3 + React + PostgreSQL)

## Kiến trúc
- Backend: Spring Boot 3, Spring Security + JWT, Flyway, PostgreSQL
- Frontend: React + Vite + Tailwind + Zustand

## Chạy local
1. Khởi động DB: `docker compose up -d`
2. Backend:
   - `cd backend`
   - `mvn spring-boot:run`
3. Frontend:
   - `cd frontend`
   - `npm install`
   - `npm run dev`

## Tài khoản seed
- Admin: `admin@blog.local / Admin@123`
- User: `john@blog.local / User@123`

## Tính năng chính
- Auth JWT + phân quyền USER/ADMIN
- CRUD post (admin), publish/draft, like, view count
- Category/tag API
- Comment và ẩn/hiện comment
- Profile user
- Swagger: `/swagger-ui.html`
