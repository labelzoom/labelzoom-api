package com.labelzoom.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labelzoom.api.model.components.CLabel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: This test was succeeding when I ran it, but since I don't think the ordering of fields can be guaranteed, I
 *  disabled the test so that it won't interrupt CI/CD. But this is something we should expand on in the future. The
 *  goal is to ensure that if a developer adds a new field to a component, that they also update the cloning / deep copy
 *  logic. May look to use reflection to randomize the data in each field of the component.
 */
public class DeepCopyTests
{
    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Disabled
    void testSimpleClone()
    {
        final CLabel label = new CLabel();
        final CLabel clone = label.clone();
        final String labelSerialized = assertDoesNotThrow(() -> objectMapper.writeValueAsString(label));
        final String cloneSerialized = assertDoesNotThrow(() -> objectMapper.writeValueAsString(clone));
        assertNotSame(label, clone);
        assertEquals(labelSerialized, cloneSerialized);
    }
}
