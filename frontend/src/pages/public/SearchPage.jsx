import { useSearchParams } from 'react-router-dom'
import HomePage from './HomePage'

export default function SearchPage() {
  const [params] = useSearchParams()
  return <div><h1 className='text-2xl font-bold mb-4'>Search: {params.get('keyword') || ''}</h1><HomePage /></div>
}
