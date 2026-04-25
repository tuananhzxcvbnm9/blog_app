import { Link, Outlet } from 'react-router-dom'

export default function AdminLayout() {
  return (
    <div className="grid min-h-screen grid-cols-12">
      <aside className="col-span-3 border-r bg-gray-100 p-4">
        <h2 className="mb-4 text-xl font-bold">Admin</h2>
        <div className="flex flex-col gap-2">
          <Link to="/admin">Dashboard</Link>
          <Link to="/admin/posts">Manage Posts</Link>
          <Link to="/admin/categories">Manage Categories</Link>
          <Link to="/admin/tags">Manage Tags</Link>
          <Link to="/admin/comments">Manage Comments</Link>
          <Link to="/admin/users">Manage Users</Link>
        </div>
      </aside>
      <main className="col-span-9 p-6"><Outlet /></main>
    </div>
  )
}
