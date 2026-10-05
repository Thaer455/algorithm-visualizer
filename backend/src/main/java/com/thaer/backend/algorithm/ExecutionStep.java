package com.thaer.backend.algorithm;

import java.util.Map;
import java.util.Objects;

/**
 * Describes one observable event in an algorithm execution.
 *
 * <p>The sequence position is zero-based. The operation identifier names the
 * kind of event, while the operation data carries its operation-specific
 * scalar values. The data map is copied and made immutable during construction.
 * This model contains no transport, framework, or visualization types.
 *
 * @param sequencePosition zero-based position of this step in its execution
 * @param operationId stable, non-blank identifier for the operation
 * @param operationData immutable operation-specific string values
 */
public record ExecutionStep(
		int sequencePosition,
		String operationId,
		Map<String, String> operationData) {

	public ExecutionStep {
		if (sequencePosition < 0) {
			throw new IllegalArgumentException("Sequence position must not be negative.");
		}

		Objects.requireNonNull(operationId, "operationId must not be null");
		if (operationId.isBlank()) {
			throw new IllegalArgumentException("Operation identifier must not be blank.");
		}

		operationData = Map.copyOf(Objects.requireNonNull(
				operationData, "operationData must not be null"));
	}
}
