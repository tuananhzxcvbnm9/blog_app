import { useForm } from 'react-hook-form'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { authApi } from '../../api/services'
import { useAuthStore } from '../../store/authStore'

export default function LoginPage() {
  const { register, handleSubmit } = useForm()
  const setAuth = useAuthStore((s) => s.setAuth)
  const navigate = useNavigate()

  const onSubmit = async (data) => {
    try {
      const res = await authApi.login(data)
      const payload = res.data.data
      setAuth(payload.accessToken, { id: payload.userId, email: payload.email, username: payload.username, roles: payload.roles })
      toast.success('Đăng nhập thành công')
      navigate('/')
    } catch {
      toast.error('Đăng nhập thất bại')
    }
  }

  return <form onSubmit={handleSubmit(onSubmit)} className="mx-auto max-w-md space-y-3 rounded bg-white p-4 shadow"><h1 className="text-2xl font-bold">Login</h1><input {...register('email')} placeholder="Email" className="w-full rounded border p-2" /><input {...register('password')} type="password" placeholder="Password" className="w-full rounded border p-2" /><button className="w-full rounded bg-black p-2 text-white">Login</button></form>
}
