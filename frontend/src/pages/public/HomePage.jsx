import { useEffect, useState } from 'react'
import { fetchPosts } from '../../api/postApi'
import PostCard from '../../components/common/PostCard'
import Pagination from '../../components/common/Pagination'

export default function HomePage() {
  const [page, setPage] = useState(0)
  const [data, setData] = useState({ content: [], totalPages: 1 })
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    setLoading(true)
    fetchPosts({ page, size: 6 }).then((res) => setData(res.data.data)).finally(() => setLoading(false))
  }, [page])

  if (loading) return <p>Loading...</p>
  if (data.content.length === 0) return <p>Không có bài viết.</p>

  return <div><h1 className='text-2xl font-bold mb-4'>Bài viết mới nhất</h1><div className='grid md:grid-cols-3 gap-4'>{data.content.map((p) => <PostCard key={p.id} post={p} />)}</div><Pagination page={page} totalPages={data.totalPages} onChange={setPage} /></div>
}
