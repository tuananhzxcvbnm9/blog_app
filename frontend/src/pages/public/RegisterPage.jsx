import { useForm } from 'react-hook-form'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { authApi } from '../../api/services'

export default function RegisterPage() {
  const { register, handleSubmit } = useForm()
  const navigate = useNavigate()

  const onSubmit = async (data) => {
    try {
      await authApi.register(data)
      toast.success('Đăng ký thành công')
      navigate('/login')
    } catch {
      toast.error('Đăng ký thất bại')
    }
  }

  return <form onSubmit={handleSubmit(onSubmit)} className="mx-auto max-w-md space-y-3 rounded bg-white p-4 shadow"><h1 className="text-2xl font-bold">Register</h1><input {...register('username')} placeholder="Username" className="w-full rounded border p-2" /><input {...register('email')} placeholder="Email" className="w-full rounded border p-2" /><input {...register('password')} type="password" placeholder="Password" className="w-full rounded border p-2" /><button className="w-full rounded bg-black p-2 text-white">Register</button></form>
}
