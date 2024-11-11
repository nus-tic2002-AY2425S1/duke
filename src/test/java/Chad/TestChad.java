package Chad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestChad {

    private Chad chad;
    private static final String testFilePath = "./data/test_chad.txt";

    @BeforeEach
    void setUp() {
        chad = new Chad(testFilePath);
    }

    @Test
    void testConstructorInitialization() {
        assertNotNull(chad);
        assertNotNull(chad.tasks);
        assertNotNull(chad.ui);
        assertFalse(chad.isExit); // Should start with isExit false
    }

    @Test
    void testInitializeStorage() {
        chad.initializeStorage(testFilePath);
        assertNotNull(chad.storage);

    }

}
