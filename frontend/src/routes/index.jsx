import PublicLayout from '../layouts/PublicLayout'
import AdminLayout from '../layouts/AdminLayout'
import ProtectedAdminRoute from './ProtectedAdminRoute'
import HomePage from '../pages/public/HomePage'
import PostDetailPage from '../pages/public/PostDetailPage'
import LoginPage from '../pages/public/LoginPage'
import RegisterPage from '../pages/public/RegisterPage'
import CategoryPage from '../pages/public/CategoryPage'
import TagPage from '../pages/public/TagPage'
import SearchPage from '../pages/public/SearchPage'
import AdminDashboardPage from '../pages/admin/AdminDashboardPage'
import ManagePostsPage from '../pages/admin/ManagePostsPage'
import CreatePostPage from '../pages/admin/CreatePostPage'
import EditPostPage from '../pages/admin/EditPostPage'
import ManageCategoriesPage from '../pages/admin/ManageCategoriesPage'
import ManageTagsPage from '../pages/admin/ManageTagsPage'
import ManageCommentsPage from '../pages/admin/ManageCommentsPage'
import ManageUsersPage from '../pages/admin/ManageUsersPage'

export const appRoutes = [
  { path: '/', element: <PublicLayout />, children: [
    { index: true, element: <HomePage /> },
    { path: 'posts/:slug', element: <PostDetailPage /> },
    { path: 'login', element: <LoginPage /> },
    { path: 'register', element: <RegisterPage /> },
    { path: 'categories/:slug', element: <CategoryPage /> },
    { path: 'tags/:slug', element: <TagPage /> },
    { path: 'search', element: <SearchPage /> }
  ]},
  { element: <ProtectedAdminRoute />, children: [
    { path: '/admin', element: <AdminLayout />, children: [
      { index: true, element: <AdminDashboardPage /> },
      { path: 'posts', element: <ManagePostsPage /> },
      { path: 'posts/create', element: <CreatePostPage /> },
      { path: 'posts/:id/edit', element: <EditPostPage /> },
      { path: 'categories', element: <ManageCategoriesPage /> },
      { path: 'tags', element: <ManageTagsPage /> },
      { path: 'comments', element: <ManageCommentsPage /> },
      { path: 'users', element: <ManageUsersPage /> }
    ]}
  ]}
]
