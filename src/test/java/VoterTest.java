import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoterTest {

    @Test
    void testConstructor() {
        // NullPointerException if inputs are empty
        assertThrows(NullPointerException.class, () -> new Voter(null, "", 1));
        assertThrows(NullPointerException.class, () -> new Voter("", null, 1));

        // IllegalArgumentException if the arguments are invalid
        assertThrows(IllegalArgumentException.class, () -> new Voter("", "Doe", 1));
        assertThrows(IllegalArgumentException.class, () -> new Voter("John", "", 1));
        assertThrows(IllegalArgumentException.class, () -> new Voter("John", "Doe", 0));
        assertThrows(IllegalArgumentException.class, () -> new Voter("John", "Doe", -1));
    }

    @Test
    void getFirstName() {
        var voter = new Voter("John", "Doe", 1);
        assertEquals("John", voter.getFirstName());
    }

    @Test
    void getLastName() {
        var voter = new Voter("John", "Doe", 1);
        assertEquals("Doe", voter.getLastName());
    }

    @Test
    void getId() {
        var voter = new Voter("John", "Doe", 1);
        assertEquals(1, voter.getId());
    }

    @Test
    void testToString() {
        var voter = new Voter("John", "Doe", 1);
        assertEquals("Doe, John (1)", voter.toString());
    }

    @Test
    void testEquals() {
        var voter1 = new Voter("John", "Doe", 1);
        var voter2 = new Voter("Jane", "Smith", 1);
        assertEquals(voter1, voter2);
    }

    @Test
    void compareTo() {
    }
}