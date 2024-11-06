import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class VotingMachineTest {

    @Test
    void testConstructorWithInputNullPointer() {
        assertThrows(NullPointerException.class, () -> new VotingMachine(null));
    }

    @Test
    void testConstructorWithInput() {
        Year year = Year.now();
        LocalDateTime start = LocalDateTime.of(year.getValue(), 10, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year.getValue(), 11, 8, 23, 59, 59);
        VotingMachine votingMachine = new VotingMachine(year);

        assertEquals(Year.now(), votingMachine.year);
        assertEquals(start, votingMachine.start);
        assertEquals(end, votingMachine.end);
    }

    @Test
    void testConstructorWithoutInput() {
        Year year = Year.now();
        LocalDateTime start = LocalDateTime.of(year.getValue(), 10, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(year.getValue(), 11, 8, 23, 59, 59);
        VotingMachine votingMachine = new VotingMachine();

        assertEquals(Year.now(), votingMachine.year);
        assertEquals(start, votingMachine.start);
        assertEquals(end, votingMachine.end);
    }

    @Test
    void testAddNullPointerException() {
        VotingMachine votingMachine = new VotingMachine();

        assertThrows(NullPointerException.class, () -> votingMachine.add(null));
    }

    @Test
    void testAddDateTimeException() {
        VotingMachine votingMachine = new VotingMachine();
        Voter voter = new Voter("John", "Doe", 1);
        Party party = Party.DEMOCRAT;

        // Too early
        LocalDateTime date1 = LocalDateTime.of(Year.now().getValue(), 9, 30, 23, 59, 59);
        // Too late
        LocalDateTime date2 = LocalDateTime.of(Year.now().getValue(), 11, 9, 0, 0, 0);
        // Just in time
        LocalDateTime date3 = LocalDateTime.of(Year.now().getValue(), 10, 1, 0, 0, 0);
        LocalDateTime date4 = LocalDateTime.of(Year.now().getValue(), 11, 8, 23, 59, 59);

        Vote vote1 = new Vote(voter, date1, party);
        Vote vote2 = new Vote(voter, date2, party);
        Vote vote3 = new Vote(voter, date3, party);
        Vote vote4 = new Vote(voter, date4, party);

        assertThrows(DateTimeException.class, () -> votingMachine.add(vote1));
        assertThrows(DateTimeException.class, () -> votingMachine.add(vote2));
        assertDoesNotThrow(() -> votingMachine.add(vote3));
        assertDoesNotThrow(() -> votingMachine.add(vote4));
    }

    @Test
    void testAddNewVote() {
        VotingMachine votingMachine = new VotingMachine();

        Voter voter = new Voter("John", "Doe", 1);
        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);
        Party party = Party.DEMOCRAT;
        Vote vote = new Vote(voter, date, party);

        votingMachine.add(vote);
        assertEquals(1, votingMachine.votes.size());
        assertEquals(vote, votingMachine.votes.getFirst());
    }

    @Test
    void testAddOverwrite() {
        Voter voter = new Voter("John", "Doe", 1);
        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);
        Vote vote1 = new Vote(voter, date, Party.DEMOCRAT);
        Vote vote2 = new Vote(voter, date, Party.REPUBLICAN);

        VoteComparator voteComparator = new VoteComparator();

        assertEquals(1, voteComparator.compare(vote2, vote1));

        VotingMachine votingMachine1 = new VotingMachine();
        VotingMachine votingMachine2 = new VotingMachine();

        // Should overwrite
        votingMachine1.add(vote1);
        assertEquals(Party.DEMOCRAT, votingMachine1.votes.getFirst().getParty());
        votingMachine1.add(vote2);
        assertEquals(Party.REPUBLICAN, votingMachine1.votes.getFirst().getParty());
        assertEquals(1, votingMachine1.votes.size());

        // Should not overwrite
        votingMachine2.add(vote2);
        assertEquals(Party.REPUBLICAN, votingMachine2.votes.getFirst().getParty());
        votingMachine2.add(vote1);
        assertEquals(Party.REPUBLICAN, votingMachine2.votes.getFirst().getParty());
        assertEquals(1, votingMachine2.votes.size());
    }

    @Test
    void addAll() {

    }

    @Test
    void getVotes() {
    }

    @Test
    void getVoters() {
    }

    @Test
    void getVote() {
    }

    @Test
    void getYear() {
    }

    @Test
    void getDistribution() {
    }

    @Test
    void showDistribution() {
    }

    @Test
    void getSecurityCode() {
    }

    @Test
    void manipulateVote() {
    }
}