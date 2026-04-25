import { Link } from 'react-router-dom'

export default function PostCard({ post }) {
  return (
    <article className="bg-white rounded-xl p-4 shadow-sm">
      <h3 className="font-semibold text-lg"><Link to={`/posts/${post.id}`}>{post.title}</Link></h3>
      <p className="text-slate-600 mt-2">{post.excerpt}</p>
      <p className="text-xs mt-3 text-slate-500">Views: {post.viewCount}</p>
    </article>
  )
}
