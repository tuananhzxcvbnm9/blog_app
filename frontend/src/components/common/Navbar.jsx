import { Link } from 'react-router-dom'
import { useAuthStore } from '../../store/authStore'

export default function Navbar() {
  const { user, logout } = useAuthStore()
  return (
    <header className='bg-white border-b'>
      <div className='container mx-auto p-4 flex justify-between'>
        <Link to='/' className='font-bold'>Blog App</Link>
        <nav className='flex gap-4 items-center'>
          <Link to='/search'>Search</Link>
          {user?.role === 'ROLE_ADMIN' && <Link to='/admin'>Admin</Link>}
          {user ? <button onClick={logout}>Logout</button> : <><Link to='/login'>Login</Link><Link to='/register'>Register</Link></>}
        </nav>
      </div>
    </header>
  )
}
