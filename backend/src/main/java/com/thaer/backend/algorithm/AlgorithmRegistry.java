package com.thaer.backend.algorithm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Provides immutable lookup of algorithm implementations by their identifiers.
 *
 * <p>The registry captures the supplied algorithms at construction time. Each
 * identifier must be non-null, non-blank, and unique. A lookup for a valid but
 * unregistered identifier returns {@link Optional#empty()}.
 */
public final class AlgorithmRegistry {

	private final Map<String, Algorithm<?, ?>> algorithmsById;

	/**
	 * Creates a registry containing the supplied algorithms.
	 *
	 * @param algorithms algorithms to register
	 * @throws NullPointerException if the collection or one of its algorithms is null
	 * @throws IllegalArgumentException if an algorithm identifier is null, blank, or duplicated
	 */
	public AlgorithmRegistry(Collection<? extends Algorithm<?, ?>> algorithms) {
		Objects.requireNonNull(algorithms, "algorithms must not be null");

		Map<String, Algorithm<?, ?>> indexedAlgorithms = new HashMap<>();
		for (Algorithm<?, ?> algorithm : algorithms) {
			Objects.requireNonNull(algorithm, "algorithm must not be null");
			String id = algorithm.id();
			if (id == null || id.isBlank()) {
				throw new IllegalArgumentException("Algorithm identifier must not be null or blank.");
			}
			if (indexedAlgorithms.putIfAbsent(id, algorithm) != null) {
				throw new IllegalArgumentException("Duplicate algorithm identifier: " + id);
			}
		}

		this.algorithmsById = Map.copyOf(indexedAlgorithms);
	}

	/**
	 * Finds an algorithm registered with the given identifier.
	 *
	 * @param id non-null, non-blank algorithm identifier to find
	 * @return the registered algorithm, or an empty optional if no algorithm has that identifier
	 * @throws IllegalArgumentException if the identifier is null or blank
	 */
	public Optional<Algorithm<?, ?>> findById(String id) {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("Algorithm identifier must not be null or blank.");
		}
		return Optional.ofNullable(algorithmsById.get(id));
	}
}
