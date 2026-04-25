import { useParams } from 'react-router-dom'

export default function CategoryPage() {
  const { slug } = useParams()
  return <div>Category page: {slug} (dùng query category={slug} để lọc)</div>
}
