import { Link } from 'react-router-dom'

export default function PostCard({ post }) {
  return (
    <article className='bg-white p-4 rounded-lg shadow-sm'>
      <img src={post.thumbnail} className='h-40 w-full object-cover rounded' />
      <h3 className='font-semibold mt-3'><Link to={`/posts/${post.slug}`}>{post.title}</Link></h3>
      <p className='text-sm text-slate-600 mt-2'>{post.excerpt}</p>
    </article>
  )
}
