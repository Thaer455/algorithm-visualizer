export function AlgorithmSelector() {
  return (
    <section className="panel selector-panel" aria-labelledby="selector-title">
      <div className="panel-heading">
        <span className="step-number" aria-hidden="true">01</span>
        <div>
          <h2 id="selector-title">Choose an algorithm</h2>
          <p>Start by picking a topic to explore.</p>
        </div>
      </div>

      <label className="field-label" htmlFor="algorithm-select">ALGORITHM</label>
      <select id="algorithm-select" className="algorithm-select" defaultValue="" disabled>
        <option value="">Algorithms are coming soon</option>
      </select>

      <div className="selector-empty" role="status">
        <span className="selector-empty__icon" aria-hidden="true">⌘</span>
        <p>Algorithm choices will appear here as they are added.</p>
      </div>
    </section>
  )
}
