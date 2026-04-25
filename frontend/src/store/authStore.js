import { create } from 'zustand'

export const useAuthStore = create((set) => ({
  token: localStorage.getItem('token'),
  user: JSON.parse(localStorage.getItem('user') || 'null'),
  setAuth: (payload) => {
    localStorage.setItem('token', payload.accessToken)
    localStorage.setItem('user', JSON.stringify({ username: payload.username, role: payload.role }))
    set({ token: payload.accessToken, user: { username: payload.username, role: payload.role } })
  },
  logout: () => {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    set({ token: null, user: null })
  }
}))
