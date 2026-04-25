import { createBrowserRouter, Navigate } from 'react-router-dom'
import PublicLayout from '../layouts/PublicLayout'
import AdminLayout from '../layouts/AdminLayout'
import HomePage from '../pages/public/HomePage'
import PostDetailPage from '../pages/public/PostDetailPage'
import LoginPage from '../pages/public/LoginPage'
import RegisterPage from '../pages/public/RegisterPage'
import SearchPage from '../pages/public/SearchPage'
import CategoryPage from '../pages/public/CategoryPage'
import TagPage from '../pages/public/TagPage'
import DashboardPage from '../pages/admin/DashboardPage'
import ManagePostsPage from '../pages/admin/ManagePostsPage'
import PostFormPage from '../pages/admin/PostFormPage'
import PlaceholderAdminPage from '../pages/admin/PlaceholderAdminPage'
import { useAuthStore } from '../store/authStore'

function AdminGuard({ children }) {
  const user = useAuthStore.getState().user
  return user?.roles?.includes('ROLE_ADMIN') ? children : <Navigate to="/login" replace />
}

export const router = createBrowserRouter([
  {
    path: '/',
    element: <PublicLayout />,
    children: [
      { index: true, element: <HomePage /> },
      { path: 'posts/:slug', element: <PostDetailPage /> },
      { path: 'login', element: <LoginPage /> },
      { path: 'register', element: <RegisterPage /> },
      { path: 'search', element: <SearchPage /> },
      { path: 'category/:slug', element: <CategoryPage /> },
      { path: 'tag/:slug', element: <TagPage /> },
    ],
  },
  {
    path: '/admin',
    element: <AdminGuard><AdminLayout /></AdminGuard>,
    children: [
      { index: true, element: <DashboardPage /> },
      { path: 'posts', element: <ManagePostsPage /> },
      { path: 'posts/new', element: <PostFormPage /> },
      { path: 'posts/:id/edit', element: <PostFormPage /> },
      { path: 'categories', element: <PlaceholderAdminPage title="Manage Categories" /> },
      { path: 'tags', element: <PlaceholderAdminPage title="Manage Tags" /> },
      { path: 'comments', element: <PlaceholderAdminPage title="Manage Comments" /> },
      { path: 'users', element: <PlaceholderAdminPage title="Manage Users" /> },
    ],
  },
])
