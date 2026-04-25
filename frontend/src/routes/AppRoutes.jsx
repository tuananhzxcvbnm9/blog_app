import { Navigate, Route, Routes } from 'react-router-dom'
import PublicLayout from '../layouts/PublicLayout'
import AdminLayout from '../layouts/AdminLayout'
import HomePage from '../pages/public/HomePage'
import PostDetailPage from '../pages/public/PostDetailPage'
import LoginPage from '../pages/public/LoginPage'
import RegisterPage from '../pages/public/RegisterPage'
import AdminDashboard from '../pages/admin/AdminDashboard'
import ManagePostsPage from '../pages/admin/ManagePostsPage'
import CreatePostPage from '../pages/admin/CreatePostPage'
import { useAuthStore } from '../store/authStore'

function AdminRoute({ children }) {
  const user = useAuthStore((s) => s.user)
  return user?.roles?.includes('ROLE_ADMIN') ? children : <Navigate to="/login" replace />
}

export function AppRoutes() {
  return (
    <Routes>
      <Route element={<PublicLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/posts/:id" element={<PostDetailPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Route>
      <Route path="/admin" element={<AdminRoute><AdminLayout /></AdminRoute>}>
        <Route index element={<AdminDashboard />} />
        <Route path="posts" element={<ManagePostsPage />} />
        <Route path="posts/new" element={<CreatePostPage />} />
      </Route>
    </Routes>
  )
}
