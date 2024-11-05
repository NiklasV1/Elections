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
        var voter = new Voter("john", "doe", 1);
    }

    @Test
    void getLastName() {
        var voter = new Voter("john", "doe", 1);

    }

    @Test
    void getId() {
        var voter = new Voter("john", "doe", 1);

    }

    @Test
    void testToString() {
        var voter = new Voter("john", "doe", 1);

    }

    @Test
    void testEquals() {
    }

    @Test
    void compareTo() {
    }
}