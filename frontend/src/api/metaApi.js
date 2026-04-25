import client from './client'

export const fetchCategories = () => client.get('/categories')
export const fetchTags = () => client.get('/tags')
