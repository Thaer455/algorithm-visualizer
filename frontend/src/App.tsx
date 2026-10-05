import { useEffect, useState } from 'react'
import { getBackendHealth } from './api/backendApi'
import { AlgorithmNavigation } from './components/AlgorithmNavigation'
import { AlgorithmSelector } from './components/AlgorithmSelector'
import { AppHeader } from './components/AppHeader'
import { VisualizationPanel } from './components/VisualizationPanel'
import './App.css'

function App() {
  const [backendMessage, setBackendMessage] = useState<string | null>(null)
  const [backendError, setBackendError] = useState<string | null>(null)

  useEffect(() => {
    const controller = new AbortController()

    getBackendHealth(controller.signal)
      .then(({ message }) => setBackendMessage(message))
      .catch((error: unknown) => {
        if (controller.signal.aborted) return

        setBackendError(
          error instanceof Error ? error.message : 'Unable to reach the backend.',
        )
      })

    return () => controller.abort()
  }, [])

  return (
    <div className="app-shell">
      <AppHeader />
      <div className="workspace">
        <AlgorithmNavigation />
        <main className="main-content">
          <section className="page-intro" aria-labelledby="page-title">
            <p className="eyebrow">ALGORITHM WORKSPACE</p>
            <h1 id="page-title">See how algorithms think.</h1>
            <p className="page-intro__description">
              Choose an algorithm and explore its steps in a visual workspace.
            </p>
          </section>

          <div className="workspace-grid">
            <AlgorithmSelector />
            <VisualizationPanel
              backendMessage={backendMessage}
              backendError={backendError}
            />
          </div>
        </main>
      </div>
    </div>
  )
}

export default App
