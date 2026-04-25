export default function Pagination({ page, totalPages, onChange }) {
  return (
    <div className="mt-4 flex items-center gap-2">
      <button disabled={page <= 0} onClick={() => onChange(page - 1)} className="rounded border px-3 py-1 disabled:opacity-50">Prev</button>
      <span>Page {page + 1}/{Math.max(totalPages, 1)}</span>
      <button disabled={page >= totalPages - 1} onClick={() => onChange(page + 1)} className="rounded border px-3 py-1 disabled:opacity-50">Next</button>
    </div>
  )
}
