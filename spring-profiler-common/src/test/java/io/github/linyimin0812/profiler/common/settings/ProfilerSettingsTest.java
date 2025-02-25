package io.github.linyimin0812.profiler.common.settings;

import org.junit.jupiter.api.*;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author linyimin
 * @date 2023/07/27 16:15
 **/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProfilerSettingsTest {

    @Test
    @Order(0)
    void loadProperties() {
        URL configurationURL = ProfilerSettingsTest.class.getClassLoader().getResource("spring-startup-analyzer.properties");
        assertNotNull(configurationURL);
        ProfilerSettings.loadProperties(configurationURL.getPath());
    }

    @Test
    @Order(1)
    void testGetProperty() {
        assertNull(ProfilerSettings.getProperty("key"));
        assertEquals("default", ProfilerSettings.getProperty("key", "default"));
        assertEquals("testValue3", ProfilerSettings.getProperty("testKey3"));

        assertNotEquals("/xxxxxx/", ProfilerSettings.getProperty("user.home"));
        assertNotEquals("/xxxxxx/", ProfilerSettings.getProperty("user.home", "/xxxxxx/"));

    }

    @Test
    @Order(2)
    void contains() {
        assertTrue(ProfilerSettings.contains("testKey2"));
        assertFalse(ProfilerSettings.contains("key"));
    }

    @Test
    @Order(3)
    void isNotBlank() {
        assertTrue(ProfilerSettings.isNotBlank("test"));
        assertFalse(ProfilerSettings.isNotBlank(""));
    }
}