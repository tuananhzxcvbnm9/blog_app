import { Link, Outlet } from 'react-router-dom'

export default function AdminLayout() {
  return (
    <div className="min-h-screen grid grid-cols-[240px_1fr]">
      <aside className="bg-slate-900 text-white p-4 space-y-3">
        <h2 className="font-semibold">Admin Panel</h2>
        <nav className="space-y-2 text-sm">
          <Link className="block" to="/admin">Dashboard</Link>
          <Link className="block" to="/admin/posts">Manage Posts</Link>
          <Link className="block" to="/admin/posts/new">Create Post</Link>
        </nav>
      </aside>
      <main className="p-6"><Outlet /></main>
    </div>
  )
}
