import { api } from './client'

export const getPosts = (params) => api.get('/posts', { params })
export const getPost = (id) => api.get(`/posts/${id}`)
export const createPost = (payload) => api.post('/posts', payload)
