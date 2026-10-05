package com.thaer.backend.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ExecutionStepTests {

	@Test
	void storesSequencePositionOperationIdentifierAndOperationData() {
		ExecutionStep step = new ExecutionStep(
				2,
				"compare",
				Map.of("leftIndex", "1", "rightIndex", "2"));

		assertEquals(2, step.sequencePosition());
		assertEquals("compare", step.operationId());
		assertEquals(Map.of("leftIndex", "1", "rightIndex", "2"), step.operationData());
	}

	@Test
	void copiesOperationDataAndExposesItAsUnmodifiable() {
		Map<String, String> sourceData = new HashMap<>();
		sourceData.put("item", "before");

		ExecutionStep step = new ExecutionStep(0, "update", sourceData);
		sourceData.put("item", "after");

		assertEquals("before", step.operationData().get("item"));
		assertThrows(UnsupportedOperationException.class,
				() -> step.operationData().put("item", "changed"));
	}

	@Test
	void rejectsNegativeSequencePosition() {
		assertThrows(IllegalArgumentException.class,
				() -> new ExecutionStep(-1, "compare", Map.of()));
	}

	@Test
	void rejectsBlankOperationIdentifier() {
		assertThrows(IllegalArgumentException.class,
				() -> new ExecutionStep(0, " ", Map.of()));
	}
}
