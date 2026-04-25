import { useParams } from 'react-router-dom'

export default function TagPage() {
  const { slug } = useParams()
  return <div>Tag page: {slug} (dùng query tag={slug} để lọc)</div>
}
