import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VoteTest {

    @Test
    void testConstructor() {
        var voter = new Voter("John", "Doe", 1);
        LocalDateTime date = LocalDateTime.now();
        Party party = Party.DEMOCRAT;

        // NullPointerException for null arguments
        assertThrows(NullPointerException.class, () -> new Vote(null, date, party));
        assertThrows(NullPointerException.class, () -> new Vote(voter, null, party));
        assertThrows(NullPointerException.class, () -> new Vote(voter, date, null));
    }

    @Test
    void getVoter() {
        var voter = new Voter("John", "Doe", 1);
        LocalDateTime date = LocalDateTime.now();
        Party party = Party.DEMOCRAT;

        var vote = new Vote(voter, date, party);
        assertEquals(voter, vote.getVoter());
    }

    @Test
    void getDateTime() {
        var voter = new Voter("John", "Doe", 1);
        LocalDateTime date = LocalDateTime.now();
        Party party = Party.DEMOCRAT;

        var vote = new Vote(voter, date, party);
        assertEquals(date, vote.getDateTime());
    }

    @Test
    void getParty() {
        var voter = new Voter("John", "Doe", 1);
        LocalDateTime date = LocalDateTime.now();
        Party party = Party.DEMOCRAT;

        var vote = new Vote(voter, date, party);
        assertEquals(party, vote.getParty());
    }

    @Test
    void testToString() {
        var voter = new Voter("John", "Doe", 1);
        LocalDateTime date = LocalDateTime.of(2024, 11, 5, 18, 9, 20);
        Party party = Party.DEMOCRAT;

        var vote = new Vote(voter, date, party);
        assertEquals("2024-11-05 18:09:20: Doe, John (1) - Democratic Party", vote.toString());
    }

    @Test
    void testEquals() {
        var voter1 = new Voter("John", "Doe", 1);
        LocalDateTime date1 = LocalDateTime.of(2024, 11, 5, 18, 9, 20);
        Party party1 = Party.DEMOCRAT;
        var vote1 = new Vote(voter1, date1, party1);


        var voter2 = new Voter("John", "Doe", 1);
        LocalDateTime date2 = LocalDateTime.of(2024, 11, 5, 18, 9, 20);
        Party party2 = Party.DEMOCRAT;
        var vote2 = new Vote(voter2, date2, party2);

        assertEquals(vote1, vote2);
    }
}