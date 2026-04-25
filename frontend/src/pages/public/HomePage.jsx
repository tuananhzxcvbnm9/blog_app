import { useEffect, useState } from 'react'
import { postApi, taxonomyApi } from '../../api/services'
import PostCard from '../../components/PostCard'
import Pagination from '../../components/Pagination'

export default function HomePage() {
  const [posts, setPosts] = useState([])
  const [page, setPage] = useState(0)
  const [meta, setMeta] = useState({ totalPages: 0 })
  const [categories, setCategories] = useState([])
  const [tags, setTags] = useState([])
  const [filters, setFilters] = useState({ q: '', category: '', tag: '' })

  useEffect(() => {
    fetchPosts()
    taxonomyApi.categories().then((res) => setCategories(res.data.data))
    taxonomyApi.tags().then((res) => setTags(res.data.data))
  }, [page])

  const fetchPosts = async () => {
    const res = await postApi.list({ ...filters, page, size: 6 })
    setPosts(res.data.data.content)
    setMeta(res.data.data)
  }

  return (
    <div className="grid gap-6 md:grid-cols-4">
      <aside className="space-y-3 md:col-span-1">
        <input placeholder="Search title" className="w-full rounded border p-2" onChange={(e) => setFilters((s) => ({ ...s, q: e.target.value }))} />
        <select className="w-full rounded border p-2" onChange={(e) => setFilters((s) => ({ ...s, category: e.target.value }))}>
          <option value="">All categories</option>
          {categories.map((c) => <option key={c} value={c.toLowerCase()}>{c}</option>)}
        </select>
        <select className="w-full rounded border p-2" onChange={(e) => setFilters((s) => ({ ...s, tag: e.target.value }))}>
          <option value="">All tags</option>
          {tags.map((t) => <option key={t} value={t.toLowerCase().replace(' ', '-')}>{t}</option>)}
        </select>
        <button className="w-full rounded bg-black p-2 text-white" onClick={() => { setPage(0); fetchPosts() }}>Apply filter</button>
      </aside>
      <section className="space-y-4 md:col-span-3">
        {posts.length === 0 && <p>No post found.</p>}
        {posts.map((post) => <PostCard key={post.id} post={post} />)}
        <Pagination page={page} totalPages={meta.totalPages} onChange={setPage} />
      </section>
    </div>
  )
}
