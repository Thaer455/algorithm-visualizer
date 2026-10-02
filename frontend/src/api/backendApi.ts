export type BackendHealthResponse = {
  message: string
}

export async function getBackendHealth(
  signal?: AbortSignal,
): Promise<BackendHealthResponse> {
  const response = await fetch('/api/health', { signal })

  if (!response.ok) {
    throw new Error(`Backend request failed (${response.status}).`)
  }

  const body: unknown = await response.json()

  if (
    typeof body !== 'object' ||
    body === null ||
    !('message' in body) ||
    typeof body.message !== 'string'
  ) {
    throw new Error('Backend returned an unexpected response.')
  }

  return { message: body.message }
}
