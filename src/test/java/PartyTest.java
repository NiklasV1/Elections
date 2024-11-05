import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

    @Test
    void testColorCodes() {
        assertEquals(0xff0000, Party.REPUBLICAN.getColorCode());
    }
}