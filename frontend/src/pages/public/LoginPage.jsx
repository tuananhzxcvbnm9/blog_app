import { useForm } from 'react-hook-form'
import { loginApi } from '../../api/authApi'
import { useAuthStore } from '../../store/authStore'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

export default function LoginPage() {
  const { register, handleSubmit } = useForm()
  const { setAuth } = useAuthStore()
  const navigate = useNavigate()

  const onSubmit = async (values) => {
    const res = await loginApi(values)
    setAuth(res.data.data)
    toast.success('Đăng nhập thành công')
    navigate('/')
  }

  return <form onSubmit={handleSubmit(onSubmit)} className='max-w-md mx-auto bg-white p-5 rounded-lg space-y-3'><h1 className='text-xl font-semibold'>Login</h1><input className='w-full border p-2 rounded' placeholder='Username' {...register('username')} /><input type='password' className='w-full border p-2 rounded' placeholder='Password' {...register('password')} /><button className='bg-blue-600 text-white px-4 py-2 rounded'>Login</button></form>
}
