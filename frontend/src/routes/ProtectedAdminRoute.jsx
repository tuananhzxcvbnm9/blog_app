import { Navigate, Outlet } from 'react-router-dom'
import { useAuthStore } from '../store/authStore'

export default function ProtectedAdminRoute() {
  const { user } = useAuthStore()
  if (!user || user.role !== 'ROLE_ADMIN') return <Navigate to='/login' replace />
  return <Outlet />
}
