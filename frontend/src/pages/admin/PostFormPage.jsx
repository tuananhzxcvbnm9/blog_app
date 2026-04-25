import { useForm } from 'react-hook-form'
import { useNavigate, useParams } from 'react-router-dom'
import { postApi } from '../../api/services'

export default function PostFormPage() {
  const { id } = useParams()
  const { register, handleSubmit } = useForm({ defaultValues: { status: 'DRAFT' } })
  const navigate = useNavigate()

  const onSubmit = async (data) => {
    const payload = { ...data, tagIds: [], categoryId: null, featured: false }
    if (id) await postApi.update(id, payload)
    else await postApi.create(payload)
    navigate('/admin/posts')
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-3 rounded bg-white p-4 shadow">
      <h1 className="text-2xl font-bold">{id ? 'Edit' : 'Create'} Post</h1>
      <input {...register('title')} placeholder="Title" className="w-full rounded border p-2" />
      <input {...register('excerpt')} placeholder="Excerpt" className="w-full rounded border p-2" />
      <textarea {...register('content')} placeholder="Content" className="h-40 w-full rounded border p-2" />
      <input {...register('thumbnail')} placeholder="Thumbnail URL" className="w-full rounded border p-2" />
      <select {...register('status')} className="w-full rounded border p-2"><option value="DRAFT">DRAFT</option><option value="PUBLISHED">PUBLISHED</option></select>
      <button className="rounded bg-black px-4 py-2 text-white">Save</button>
    </form>
  )
}
