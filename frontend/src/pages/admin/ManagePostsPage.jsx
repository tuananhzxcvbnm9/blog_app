import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { postApi } from '../../api/services'

export default function ManagePostsPage() {
  const [posts, setPosts] = useState([])

  useEffect(() => {
    postApi.list({ page: 0, size: 50 }).then((res) => setPosts(res.data.data.content || []))
  }, [])

  return (
    <div>
      <div className="mb-4 flex justify-between">
        <h1 className="text-2xl font-bold">Manage Posts</h1>
        <Link to="/admin/posts/new" className="rounded bg-black px-4 py-2 text-white">Create Post</Link>
      </div>
      <div className="space-y-2">
        {posts.map((post) => (
          <div key={post.id} className="flex items-center justify-between rounded border p-3">
            <span>{post.title}</span>
            <Link className="text-blue-600" to={`/admin/posts/${post.id}/edit`}>Edit</Link>
          </div>
        ))}
      </div>
    </div>
  )
}
