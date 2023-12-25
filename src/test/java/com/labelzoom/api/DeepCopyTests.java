package com.labelzoom.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labelzoom.api.model.components.AComponent;
import com.labelzoom.api.model.components.CLabel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import uk.co.jemos.podam.api.*;

import java.lang.reflect.Modifier;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: This test was succeeding when I ran it, but since I don't think the ordering of fields can be guaranteed, I
 *  disabled the test so that it won't interrupt CI/CD. But this is something we should expand on in the future. The
 *  goal is to ensure that if a developer adds a new field to a component, that they also update the cloning / deep copy
 *  logic. May look to use reflection to randomize the data in each field of the component.
 */
public class DeepCopyTests
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Disabled
    void testSimpleClone()
    {
        final CLabel label = new CLabel();
        final CLabel clone = label.clone();
        assertNotSame(label, clone);

        final String labelSerialized = assertDoesNotThrow(() -> objectMapper.writeValueAsString(label));
        final String cloneSerialized = assertDoesNotThrow(() -> objectMapper.writeValueAsString(clone));
        assertEquals(labelSerialized, cloneSerialized);
    }

    @Test
    @Disabled
    void testMockClone()
    {
        final PodamFactory factory = new PodamFactoryImpl();
        final CLabel label = factory.manufacturePojo(CLabel.class);
        addRandomElements(label);
        final CLabel clone = label.clone(true);
        assertNotSame(label, clone);

        final String labelSerialized = assertDoesNotThrow(() -> objectMapper.writeValueAsString(label));
        final String cloneSerialized = assertDoesNotThrow(() -> objectMapper.writeValueAsString(clone));
        System.out.println(labelSerialized);
        System.out.println(cloneSerialized);
        assertEquals(labelSerialized, cloneSerialized);
    }

    private static void addRandomElements(final CLabel label)
    {
        final PodamFactory factory = new PodamFactoryImpl();
        final Reflections reflections = new Reflections("com.labelzoom.api.model.components", new SubTypesScanner());
        final Set<Class<? extends AComponent>> allClasses = reflections.getSubTypesOf(AComponent.class);
        for (final Class<?> clazz : allClasses) {
            if (clazz.isInterface()) continue;
            if (Modifier.isAbstract(clazz.getModifiers())) continue;
            label.addElement((AComponent) factory.manufacturePojo(clazz));
        }
    }
}
