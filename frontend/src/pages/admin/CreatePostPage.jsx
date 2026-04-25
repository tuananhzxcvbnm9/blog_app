import { useForm } from 'react-hook-form'
import { createPost } from '../../api/postApi'
import { toast } from 'react-toastify'

export default function CreatePostPage() {
  const { register, handleSubmit } = useForm({ defaultValues: { status: 'DRAFT' } })

  const onSubmit = async (data) => {
    await createPost({ ...data, categoryId: null, tagIds: [] })
    toast.success('Tạo bài viết thành công')
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="max-w-3xl bg-white p-6 rounded-xl shadow-sm space-y-3">
      <h1 className="text-xl font-semibold">Create Post</h1>
      <input {...register('title')} className="w-full border p-2 rounded" placeholder="Title" />
      <input {...register('excerpt')} className="w-full border p-2 rounded" placeholder="Excerpt" />
      <input {...register('thumbnail')} className="w-full border p-2 rounded" placeholder="Thumbnail URL" />
      <textarea {...register('content')} className="w-full border p-2 rounded min-h-48" placeholder="Content" />
      <select {...register('status')} className="border p-2 rounded"><option value="DRAFT">DRAFT</option><option value="PUBLISHED">PUBLISHED</option></select>
      <button className="bg-slate-900 text-white px-4 py-2 rounded">Save</button>
    </form>
  )
}
