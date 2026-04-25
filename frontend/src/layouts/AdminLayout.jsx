import { Link, Outlet } from 'react-router-dom'

export default function AdminLayout() {
  return (
    <div className='min-h-screen grid grid-cols-[240px_1fr]'>
      <aside className='bg-slate-900 text-white p-4 space-y-2'>
        <Link className='block' to='/admin'>Dashboard</Link>
        <Link className='block' to='/admin/posts'>Manage Posts</Link>
        <Link className='block' to='/admin/categories'>Manage Categories</Link>
        <Link className='block' to='/admin/tags'>Manage Tags</Link>
        <Link className='block' to='/admin/comments'>Manage Comments</Link>
        <Link className='block' to='/admin/users'>Manage Users</Link>
      </aside>
      <main className='p-6'><Outlet /></main>
    </div>
  )
}
