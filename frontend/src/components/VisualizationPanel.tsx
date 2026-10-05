type VisualizationPanelProps = {
  backendMessage: string | null
  backendError: string | null
}

export function VisualizationPanel({
  backendMessage,
  backendError,
}: VisualizationPanelProps) {
  return (
    <section className="panel visualization-panel" id="workspace" aria-labelledby="visualization-title">
      <div className="panel-heading visualization-panel__heading">
        <span className="step-number" aria-hidden="true">02</span>
        <div>
          <h2 id="visualization-title">Visualization</h2>
          <p>Your algorithm will come to life here.</p>
        </div>
        <span className="preview-badge">PREVIEW</span>
      </div>

      <div className="visualization-empty" aria-label="Visualization area">
        <div className="visualization-empty__art" aria-hidden="true">
          <span className="visualization-empty__bar visualization-empty__bar--one" />
          <span className="visualization-empty__bar visualization-empty__bar--two" />
          <span className="visualization-empty__bar visualization-empty__bar--three" />
          <span className="visualization-empty__bar visualization-empty__bar--four" />
          <span className="visualization-empty__bar visualization-empty__bar--five" />
          <span className="visualization-empty__spark">✳</span>
        </div>
        <h3>A little space for big ideas</h3>
        <p>Pick an algorithm to see its steps visualized.</p>
      </div>

      <div className="backend-status" aria-live="polite">
        <span className={`backend-status__dot${backendError ? ' backend-status__dot--error' : ''}`} aria-hidden="true" />
        <span>Backend</span>
        {backendMessage ? (
          <span className="backend-status__message" role="status">{backendMessage}</span>
        ) : backendError ? (
          <span className="backend-status__message backend-status__message--error" role="alert">
            {backendError}
          </span>
        ) : (
          <span className="backend-status__message" role="status">Connecting…</span>
        )}
      </div>
    </section>
  )
}
