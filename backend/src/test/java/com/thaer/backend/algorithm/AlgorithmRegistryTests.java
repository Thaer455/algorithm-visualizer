package com.thaer.backend.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class AlgorithmRegistryTests {

	@Test
	void constructsRegistryAndFindsAlgorithmByIdentifier() {
		Algorithm<String, String> algorithm = new StubAlgorithm("sort");
		AlgorithmRegistry registry = new AlgorithmRegistry(List.of(algorithm));

		assertSame(algorithm, registry.findById("sort").orElseThrow());
	}

	@Test
	void returnsEmptyWhenIdentifierIsNotRegistered() {
		AlgorithmRegistry registry = new AlgorithmRegistry(List.of(new StubAlgorithm("sort")));

		assertEquals(Optional.empty(), registry.findById("search"));
	}

	@Test
	void acceptsAnEmptyCollection() {
		AlgorithmRegistry registry = new AlgorithmRegistry(List.of());

		assertEquals(Optional.empty(), registry.findById("sort"));
	}

	@Test
	void rejectsDuplicateIdentifiers() {
		assertThrows(IllegalArgumentException.class,
				() -> new AlgorithmRegistry(List.of(
						new StubAlgorithm("sort"),
						new StubAlgorithm("sort"))));
	}

	@Test
	void rejectsNullRegisteredIdentifier() {
		assertThrows(IllegalArgumentException.class,
				() -> new AlgorithmRegistry(List.of(new StubAlgorithm(null))));
	}

	@Test
	void rejectsBlankRegisteredIdentifier() {
		assertThrows(IllegalArgumentException.class,
				() -> new AlgorithmRegistry(List.of(new StubAlgorithm("  "))));
	}

	@Test
	void rejectsNullLookupIdentifier() {
		AlgorithmRegistry registry = new AlgorithmRegistry(List.of(new StubAlgorithm("sort")));

		assertThrows(IllegalArgumentException.class, () -> registry.findById(null));
	}

	@Test
	void rejectsBlankLookupIdentifier() {
		AlgorithmRegistry registry = new AlgorithmRegistry(List.of(new StubAlgorithm("sort")));

		assertThrows(IllegalArgumentException.class, () -> registry.findById(" "));
	}

	@Test
	void registrationsDoNotChangeWhenSourceCollectionIsModified() {
		Algorithm<String, String> registered = new StubAlgorithm("sort");
		Algorithm<String, String> addedLater = new StubAlgorithm("search");
		List<Algorithm<?, ?>> source = new ArrayList<>(List.of(registered));
		AlgorithmRegistry registry = new AlgorithmRegistry(source);

		source.add(addedLater);

		assertSame(registered, registry.findById("sort").orElseThrow());
		assertEquals(Optional.empty(), registry.findById("search"));
	}

	private static final class StubAlgorithm implements Algorithm<String, String> {

		private final String id;

		private StubAlgorithm(String id) {
			this.id = id;
		}

		@Override
		public String id() {
			return id;
		}

		@Override
		public String name() {
			return "Stub Algorithm";
		}

		@Override
		public String execute(String input) {
			return input;
		}
	}
}
