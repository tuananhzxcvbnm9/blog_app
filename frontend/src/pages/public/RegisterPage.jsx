import { useForm } from 'react-hook-form'
import { registerApi } from '../../api/authApi'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

export default function RegisterPage() {
  const { register, handleSubmit } = useForm()
  const navigate = useNavigate()

  const onSubmit = async (data) => {
    await registerApi(data)
    toast.success('Đăng ký thành công, vui lòng đăng nhập')
    navigate('/login')
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="max-w-md mx-auto bg-white p-6 rounded-xl shadow-sm space-y-4">
      <h1 className="text-xl font-semibold">Register</h1>
      <input {...register('username')} className="w-full border p-2 rounded" placeholder="Username" />
      <input {...register('fullName')} className="w-full border p-2 rounded" placeholder="Full name" />
      <input {...register('email')} className="w-full border p-2 rounded" placeholder="Email" />
      <input {...register('password')} type="password" className="w-full border p-2 rounded" placeholder="Password" />
      <button className="w-full bg-slate-900 text-white py-2 rounded">Register</button>
    </form>
  )
}
