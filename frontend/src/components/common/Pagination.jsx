export default function Pagination({ page, totalPages, onChange }) {
  return (
    <div className='flex gap-2 my-4'>
      <button className='px-3 py-1 border rounded' disabled={page <= 0} onClick={() => onChange(page - 1)}>Prev</button>
      <span>Page {page + 1}/{Math.max(totalPages, 1)}</span>
      <button className='px-3 py-1 border rounded' disabled={page + 1 >= totalPages} onClick={() => onChange(page + 1)}>Next</button>
    </div>
  )
}
