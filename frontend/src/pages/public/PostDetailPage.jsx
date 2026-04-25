import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { commentApi, postApi } from '../../api/services'
import { useAuthStore } from '../../store/authStore'

export default function PostDetailPage() {
  const { slug } = useParams()
  const [post, setPost] = useState(null)
  const [comments, setComments] = useState([])
  const [content, setContent] = useState('')
  const { token } = useAuthStore()

  useEffect(() => {
    load()
  }, [slug])

  const load = async () => {
    const p = await postApi.detail(slug)
    setPost(p.data.data)
    const c = await commentApi.listByPost(p.data.data.id)
    setComments(c.data.data.content)
  }

  const submitComment = async (e) => {
    e.preventDefault()
    await commentApi.add(post.id, { content })
    setContent('')
    load()
  }

  if (!post) return <p>Loading...</p>

  return (
    <div className="space-y-6">
      <article className="rounded bg-white p-4 shadow">
        <h1 className="text-3xl font-bold">{post.title}</h1>
        <p className="mt-3 whitespace-pre-line">{post.content}</p>
      </article>
      <section className="rounded bg-white p-4 shadow">
        <h2 className="text-xl font-semibold">Comments</h2>
        {token && <form onSubmit={submitComment} className="my-3 flex gap-2"><input value={content} onChange={(e) => setContent(e.target.value)} className="flex-1 rounded border p-2" /><button className="rounded bg-black px-4 text-white">Send</button></form>}
        <div className="space-y-2">{comments.map((c) => <div key={c.id} className="rounded border p-2"><b>{c.author}:</b> {c.content}</div>)}</div>
      </section>
    </div>
  )
}
