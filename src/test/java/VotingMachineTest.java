import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

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
    void addAllNullPointer() {
        VotingMachine votingMachine = new VotingMachine();
        assertThrows(NullPointerException.class, () -> votingMachine.addAll(null));
    }

    @Test
    void addAll() {
        Voter voter1 = new Voter("A", "A", 1);
        Voter voter2 = new Voter("B", "B", 2);
        Voter voter3 = new Voter("C", "C", 3);
        Voter voter4 = new Voter("D", "D", 4);
        Voter voter5 = new Voter("E", "E", 5);

        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);

        Collection<Vote> votes = new ArrayList<>();
        votes.add(new Vote(voter1, date, Party.DEMOCRAT));
        votes.add(new Vote(voter2, date, Party.DEMOCRAT));
        votes.add(new Vote(voter3, date, Party.DEMOCRAT));
        votes.add(new Vote(voter4, date, Party.DEMOCRAT));
        votes.add(new Vote(voter5, date, Party.DEMOCRAT));

        VotingMachine votingMachine = new VotingMachine();

        votingMachine.addAll(votes);

        assertEquals(5, votingMachine.votes.size());
        for (int i = 0; i < votingMachine.votes.size(); i++) {
            assertEquals(i + 1, votingMachine.votes.get(i).getVoter().getId());
        }
    }

    @Test
    void getVotes() {
        Voter voter1 = new Voter("A", "A", 1);
        Voter voter2 = new Voter("B", "B", 2);

        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);

        Collection<Vote> votes = new ArrayList<>();
        Vote vote1 = new Vote(voter1, date, Party.DEMOCRAT);
        Vote vote2 = new Vote(voter2, date, Party.REPUBLICAN);

        votes.add(vote1);
        votes.add(vote2);

        VotingMachine votingMachine = new VotingMachine();

        votingMachine.addAll(votes);

        List<Vote> voteList = votingMachine.getVotes();
        assertTrue(voteList.contains(vote1));
        assertTrue(voteList.contains(vote2));

        Voter voter3 = new Voter("C", "C", 3);
        Vote vote3 = new Vote(voter3, date, Party.GREEN);

        assertThrows(UnsupportedOperationException.class, () -> voteList.add(vote3));

        voteList.getFirst().party = Party.LIBERTARIAN;
        assertNotEquals(votingMachine.votes.getFirst().party, voteList.getFirst().party);
    }

    @Test
    void getVoters() {
        Voter voter1 = new Voter("A", "A", 1);
        Voter voter2 = new Voter("B", "B", 2);

        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);

        Collection<Vote> votes = new ArrayList<>();
        Vote vote1 = new Vote(voter1, date, Party.DEMOCRAT);
        Vote vote2 = new Vote(voter2, date, Party.REPUBLICAN);

        votes.add(vote1);
        votes.add(vote2);

        VotingMachine votingMachine = new VotingMachine();

        votingMachine.addAll(votes);

        List<Voter> voterList = votingMachine.getVoters();
        assertTrue(voterList.contains(voter1));
        assertTrue(voterList.contains(voter2));

        Voter voter3 = new Voter("C", "C", 3);

        assertThrows(UnsupportedOperationException.class, () -> voterList.add(voter3));

        voterList.getFirst().id = 4;
        assertNotEquals(votingMachine.votes.getFirst().voter.id, voterList.getFirst().id);
    }

    @Test
    void getVoteNullPointer() {
        VotingMachine votingMachine = new VotingMachine();
        assertThrows(NullPointerException.class, () -> votingMachine.getVote(null));
    }

    @Test
    void getVote() {
        Voter voter1 = new Voter("A", "A", 1);
        Voter voter2 = new Voter("B", "B", 2);

        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);

        Vote vote1 = new Vote(voter1, date, Party.DEMOCRAT);

        VotingMachine votingMachine = new VotingMachine();
        votingMachine.add(vote1);

        assertEquals(vote1, votingMachine.getVote(voter1));
        assertNull(votingMachine.getVote(voter2));
    }

    @Test
    void getYear() {
        VotingMachine votingMachine = new VotingMachine();
        assertEquals(Year.now(), votingMachine.getYear());
    }

    @Test
    void getDistribution() {
        Voter voter1 = new Voter("A", "A", 1);
        Voter voter2 = new Voter("B", "A", 2);
        Voter voter3 = new Voter("C", "A", 3);
        Voter voter4 = new Voter("D", "A", 4);
        Voter voter5 = new Voter("E", "A", 5);
        Voter voter6 = new Voter("F", "A", 6);
        Voter voter7 = new Voter("G", "A", 7);

        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);

        VotingMachine votingMachine = new VotingMachine();

        votingMachine.add(new Vote(voter1, date, Party.DEMOCRAT));
        votingMachine.add(new Vote(voter2, date, Party.DEMOCRAT));
        votingMachine.add(new Vote(voter3, date, Party.DEMOCRAT));
        votingMachine.add(new Vote(voter4, date, Party.REPUBLICAN));
        votingMachine.add(new Vote(voter5, date, Party.REPUBLICAN));
        votingMachine.add(new Vote(voter6, date, Party.CONSTITUTION));
        votingMachine.add(new Vote(voter7, date, Party.LIBERTARIAN));

        Map<Party, Double> distribution = votingMachine.getDistribution();

        assertEquals(3d / 7d, distribution.get(Party.DEMOCRAT));
        assertEquals(2d / 7d, distribution.get(Party.REPUBLICAN));
        assertEquals(1d / 7d, distribution.get(Party.CONSTITUTION));
        assertEquals(1d / 7d, distribution.get(Party.LIBERTARIAN));
        assertEquals(0d / 7d, distribution.get(Party.GREEN));
    }

    @Test
    void showDistribution() {
        Voter voter1 = new Voter("A", "A", 1);
        Voter voter2 = new Voter("B", "A", 2);
        Voter voter3 = new Voter("C", "A", 3);
        Voter voter4 = new Voter("D", "A", 4);
        Voter voter5 = new Voter("E", "A", 5);
        Voter voter6 = new Voter("F", "A", 6);
        Voter voter7 = new Voter("G", "A", 7);

        LocalDateTime date = LocalDateTime.of(Year.now().getValue(), 10, 10, 12, 0, 0);

        VotingMachine votingMachine = new VotingMachine();

        votingMachine.add(new Vote(voter1, date, Party.DEMOCRAT));
        votingMachine.add(new Vote(voter2, date, Party.DEMOCRAT));
        votingMachine.add(new Vote(voter3, date, Party.DEMOCRAT));
        votingMachine.add(new Vote(voter4, date, Party.REPUBLICAN));
        votingMachine.add(new Vote(voter5, date, Party.REPUBLICAN));
        votingMachine.add(new Vote(voter6, date, Party.CONSTITUTION));
        votingMachine.add(new Vote(voter7, date, Party.LIBERTARIAN));

        List<String> distribution = votingMachine.showDistribution();
        System.out.println(distribution);

        assertEquals("Democratic Party   ###########################################", distribution.get(0));
        assertEquals("Republican Party   #############################", distribution.get(1));
        assertEquals("Libertarian Party  ##############", distribution.get(2));
        assertEquals("Constitution Party ##############", distribution.get(3));
        assertEquals("Green Party        ", distribution.get(4));
    }

    @Test
    void getSecurityCode() {
        VotingMachine votingMachine = new VotingMachine();
        Voter voter1 = new Voter("Max", "Mustermann", 10001);
        Voter voter2 = new Voter("Barack", "Obama", 125);

        assertThrows(NullPointerException.class, () -> votingMachine.getSecurityCode(null, Party.REPUBLICAN));
        assertThrows(NullPointerException.class, () -> votingMachine.getSecurityCode(voter1, null));

        assertEquals("MaxMsuetmrnan52", votingMachine.getSecurityCode(voter1, Party.REPUBLICAN));
        assertEquals("BaarkcObmaa68", votingMachine.getSecurityCode(voter2, Party.DEMOCRAT));
    }

    @Test
    void manipulateVote() {
    }
}