import { useForm } from 'react-hook-form'
import { loginApi } from '../../api/authApi'
import { useAuthStore } from '../../store/authStore'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

export default function LoginPage() {
  const { register, handleSubmit } = useForm()
  const login = useAuthStore((s) => s.login)
  const navigate = useNavigate()

  const onSubmit = async (data) => {
    try {
      const res = await loginApi(data)
      login(res.data.data)
      toast.success('Đăng nhập thành công')
      navigate('/')
    } catch {
      toast.error('Sai thông tin đăng nhập')
    }
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="max-w-md mx-auto bg-white p-6 rounded-xl shadow-sm space-y-4">
      <h1 className="text-xl font-semibold">Login</h1>
      <input {...register('email')} className="w-full border p-2 rounded" placeholder="Email" />
      <input {...register('password')} type="password" className="w-full border p-2 rounded" placeholder="Password" />
      <button className="w-full bg-slate-900 text-white py-2 rounded">Login</button>
    </form>
  )
}
