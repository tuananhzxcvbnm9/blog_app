import { create } from 'zustand'

const saved = JSON.parse(localStorage.getItem('auth') || '{}')

export const useAuthStore = create((set) => ({
  token: saved.token || null,
  user: saved.user || null,
  login: (authData) => set(() => {
    const next = { token: authData.accessToken, user: { username: authData.username, roles: authData.roles } }
    localStorage.setItem('auth', JSON.stringify(next))
    return next
  }),
  logout: () => set(() => {
    localStorage.removeItem('auth')
    return { token: null, user: null }
  }),
}))
