package com.bsj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StaticUtilsTest {

    @Test
    void range() {
        try (MockedStatic<StaticUtils> staticUtils = Mockito.mockStatic(StaticUtils.class)) {
            staticUtils.when(() -> StaticUtils.range(2, 6)).thenReturn(Arrays.asList(10, 11, 12));
            Assertions.assertTrue(StaticUtils.range(2,6).contains(10));
        }
    }

    @Test
    void getName() {
        try (MockedStatic<StaticUtils> staticUtils = Mockito.mockStatic(StaticUtils.class)) {
            staticUtils.when(StaticUtils::getName).thenReturn("Tom");
            Assertions.assertEquals("Tom",StaticUtils.getName());
        }
    }
}