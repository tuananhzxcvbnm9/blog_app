import { useForm } from 'react-hook-form'
import { registerApi } from '../../api/authApi'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

export default function RegisterPage() {
  const { register, handleSubmit } = useForm()
  const navigate = useNavigate()

  const onSubmit = async (values) => {
    await registerApi(values)
    toast.success('Đăng ký thành công, hãy đăng nhập')
    navigate('/login')
  }

  return <form onSubmit={handleSubmit(onSubmit)} className='max-w-md mx-auto bg-white p-5 rounded-lg space-y-3'><h1 className='text-xl font-semibold'>Register</h1><input className='w-full border p-2 rounded' placeholder='Username' {...register('username')} /><input className='w-full border p-2 rounded' placeholder='Email' {...register('email')} /><input className='w-full border p-2 rounded' placeholder='Full name' {...register('fullName')} /><input type='password' className='w-full border p-2 rounded' placeholder='Password' {...register('password')} /><button className='bg-blue-600 text-white px-4 py-2 rounded'>Register</button></form>
}
