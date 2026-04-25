import client from './client'

export const fetchPosts = (params) => client.get('/posts', { params })
export const fetchPostBySlug = (slug) => client.get(`/posts/${slug}`)
export const createPost = (data) => client.post('/posts', data)
export const updatePost = (id, data) => client.put(`/posts/${id}`, data)
export const deletePost = (id) => client.delete(`/posts/${id}`)
