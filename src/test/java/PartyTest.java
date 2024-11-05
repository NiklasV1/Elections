import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

    @Test
    void testColorCodes() {
        assertEquals(0xff0000, Party.REPUBLICAN.getColorCode());
        assertEquals(0x0000ff, Party.DEMOCRAT.getColorCode());
        assertEquals(0x008000, Party.GREEN.getColorCode());
        assertEquals(0xffffff, Party.CONSTITUTION.getColorCode());
        assertEquals(0xffd700, Party.LIBERTARIAN.getColorCode());
    }

    @Test
    void testStringRepresentation() {
        assertEquals("Republican Party", Party.REPUBLICAN.toString());
        assertEquals("Democratic Party", Party.DEMOCRAT.toString());
        assertEquals("Green Party", Party.GREEN.toString());
        assertEquals("Constitution Party", Party.CONSTITUTION.toString());
        assertEquals("Libertarian Party", Party.LIBERTARIAN.toString());
    }
}