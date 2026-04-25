import { useEffect, useState } from 'react'
import { getPosts } from '../../api/postApi'
import PostCard from '../../components/PostCard'

export default function HomePage() {
  const [posts, setPosts] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    getPosts({ page: 0, size: 10 }).then((res) => setPosts(res.data.data.content || [])).finally(() => setLoading(false))
  }, [])

  if (loading) return <p>Loading posts...</p>
  if (!posts.length) return <p>No posts yet.</p>

  return <div className="grid md:grid-cols-2 gap-4">{posts.map((p) => <PostCard key={p.id} post={p} />)}</div>
}
