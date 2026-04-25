import { Link } from 'react-router-dom'
import { useAuthStore } from '../store/authStore'

export default function Navbar() {
  const { user, logout } = useAuthStore()

  return (
    <header className="bg-white shadow-sm">
      <div className="mx-auto flex max-w-6xl items-center justify-between p-4">
        <Link to="/" className="text-xl font-bold">Blog App</Link>
        <nav className="flex items-center gap-4">
          <Link to="/search">Search</Link>
          {user ? (
            <>
              {user.roles?.includes('ROLE_ADMIN') && <Link to="/admin">Admin</Link>}
              <button onClick={logout} className="rounded bg-gray-800 px-3 py-1 text-white">Logout</button>
            </>
          ) : (
            <>
              <Link to="/login">Login</Link>
              <Link to="/register">Register</Link>
            </>
          )}
        </nav>
      </div>
    </header>
  )
}
