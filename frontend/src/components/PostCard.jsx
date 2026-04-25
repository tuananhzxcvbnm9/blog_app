import { Link } from 'react-router-dom'

export default function PostCard({ post }) {
  return (
    <article className="rounded border bg-white p-4 shadow-sm">
      <h3 className="text-xl font-semibold"><Link to={`/posts/${post.slug}`}>{post.title}</Link></h3>
      <p className="mt-2 text-gray-600">{post.excerpt}</p>
      <div className="mt-3 text-sm text-gray-500">Views: {post.viewCount} · Likes: {post.likeCount}</div>
    </article>
  )
}
