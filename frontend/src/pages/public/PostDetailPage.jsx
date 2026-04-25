import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { fetchPostBySlug } from '../../api/postApi'

export default function PostDetailPage() {
  const { slug } = useParams()
  const [post, setPost] = useState(null)

  useEffect(() => { fetchPostBySlug(slug).then((res) => setPost(res.data.data)) }, [slug])
  if (!post) return <p>Loading...</p>

  return <article className='bg-white rounded-lg p-6'><h1 className='text-3xl font-bold'>{post.title}</h1><img src={post.thumbnail} className='my-4 rounded' /><div className='prose max-w-none'>{post.content}</div></article>
}
