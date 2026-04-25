import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { getPost } from '../../api/postApi'

export default function PostDetailPage() {
  const { id } = useParams()
  const [post, setPost] = useState(null)

  useEffect(() => { getPost(id).then((res) => setPost(res.data.data)) }, [id])
  if (!post) return <p>Loading...</p>

  return (
    <article className="bg-white rounded-xl p-6 shadow-sm">
      <h1 className="text-2xl font-bold">{post.title}</h1>
      <p className="text-slate-500 mt-2">{post.category} • {post.author}</p>
      <div className="mt-6 whitespace-pre-wrap">{post.content}</div>
    </article>
  )
}
