import { useEffect, useState } from 'react'
import { getPosts } from '../../api/postApi'

export default function ManagePostsPage() {
  const [posts, setPosts] = useState([])
  useEffect(() => { getPosts({ page: 0, size: 20 }).then((res) => setPosts(res.data.data.content || [])) }, [])
  return (
    <div>
      <h1 className="text-xl font-semibold mb-4">Manage Posts</h1>
      <div className="bg-white rounded-xl shadow-sm overflow-hidden">
        <table className="w-full text-sm">
          <thead className="bg-slate-100"><tr><th className="text-left p-3">Title</th><th className="text-left p-3">Status</th><th className="text-left p-3">Views</th></tr></thead>
          <tbody>{posts.map((p) => <tr key={p.id} className="border-t"><td className="p-3">{p.title}</td><td className="p-3">{p.status}</td><td className="p-3">{p.viewCount}</td></tr>)}</tbody>
        </table>
      </div>
    </div>
  )
}
