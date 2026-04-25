import { Outlet } from 'react-router-dom'
import Navbar from '../components/common/Navbar'
import Footer from '../components/common/Footer'

export default function PublicLayout() {
  return <div><Navbar /><main className='container mx-auto p-4'><Outlet /></main><Footer /></div>
}
