const navigationItems = [
  { label: 'Overview', href: '#home', active: true },
  { label: 'Sorting', href: '#sorting', active: false },
  { label: 'Searching', href: '#searching', active: false },
  { label: 'Graphs', href: '#graphs', active: false },
]

export function AlgorithmNavigation() {
  return (
    <aside className="sidebar" aria-label="Workspace navigation">
      <p className="sidebar__label">LEARN</p>
      <nav className="sidebar-nav">
        {navigationItems.map(({ label, href, active }) => (
          <a
            className={`sidebar-nav__item${active ? ' sidebar-nav__item--active' : ''}`}
            href={href}
            aria-current={active ? 'page' : undefined}
            key={label}
          >
            <span className="sidebar-nav__indicator" aria-hidden="true" />
            {label}
          </a>
        ))}
      </nav>
      <div className="sidebar-note">
        <span className="sidebar-note__icon" aria-hidden="true">✳</span>
        <p>Curiosity is the first step to understanding.</p>
      </div>
    </aside>
  )
}
