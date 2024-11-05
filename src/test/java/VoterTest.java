import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<Voter> voters = new ArrayList<>();
        voters.add(new Voter("A", "A", 1));
        voters.add(new Voter("A", "B", 2));
        voters.add(new Voter("A", "A", 3));
        voters.add(new Voter("B", "A", 4));
        voters.add(new Voter("B", "B", 5));

        Collections.sort(voters);
        System.out.println("Sorting Test:");
        System.out.println(voters);

        assertEquals(1,voters.get(0).getId());
        assertEquals(3,voters.get(1).getId());
        assertEquals(4,voters.get(2).getId());
        assertEquals(2,voters.get(3).getId());
        assertEquals(5,voters.get(4).getId());
    }

    @Test
    void getClone() {
        var voter1 = new Voter("John", "Doe", 1);
        var voter2 = voter1.getClone();

        voter2.id = 2;

        assertEquals(1, voter1.getId());
        assertEquals(2, voter2.getId());
    }
}