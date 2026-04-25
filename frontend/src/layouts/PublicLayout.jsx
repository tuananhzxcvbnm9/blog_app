import { Link, Outlet } from 'react-router-dom'

export default function PublicLayout() {
  return (
    <div>
      <header className="bg-white shadow-sm">
        <div className="container-app h-14 flex items-center justify-between">
          <Link to="/" className="font-bold">Blog App</Link>
          <div className="space-x-4"><Link to="/login">Login</Link><Link to="/register">Register</Link></div>
        </div>
      </header>
      <main className="container-app py-6"><Outlet /></main>
      <footer className="py-6 text-center text-sm text-slate-500">© Blog App</footer>
    </div>
  )
}
