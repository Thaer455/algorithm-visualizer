export function AppHeader() {
  return (
    <header className="app-header">
      <a className="brand" href="#home" aria-label="AlgoLab home">
        <span className="brand__mark" aria-hidden="true">A</span>
        <span>Algo<span className="brand__accent">Lab</span></span>
      </a>
      <p className="app-header__tagline">A clearer way to learn algorithms</p>
      <a className="header-link" href="#workspace">Explore workspace <span aria-hidden="true">↗</span></a>
    </header>
  )
}
