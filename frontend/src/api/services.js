import client from './client'

export const authApi = {
  login: (payload) => client.post('/auth/login', payload),
  register: (payload) => client.post('/auth/register', payload),
}

export const postApi = {
  list: (params) => client.get('/posts', { params }),
  detail: (slug) => client.get(`/posts/${slug}`),
  create: (payload) => client.post('/posts', payload),
  update: (id, payload) => client.put(`/posts/${id}`, payload),
}

export const taxonomyApi = {
  categories: () => client.get('/categories'),
  tags: () => client.get('/tags'),
}

export const commentApi = {
  listByPost: (postId, params) => client.get(`/comments/post/${postId}`, { params }),
  add: (postId, payload) => client.post(`/comments/post/${postId}`, payload),
}

export const userApi = {
  profile: () => client.get('/users/me'),
  updateProfile: (payload) => client.put('/users/me', payload),
  users: (params) => client.get('/users', { params }),
}
