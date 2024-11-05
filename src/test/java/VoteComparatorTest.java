import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoteComparatorTest {

    @Test
    void compare() {
        List<Vote> votes = new ArrayList<>();
        Voter voter1 = new Voter("A", "A", 1);
        Voter voter2 = new Voter("A", "A", 2);
        Voter voter3 = new Voter("A", "B", 3);
        Voter voter4 = new Voter("A", "B", 4);

        LocalDateTime date1 = LocalDateTime.of(2024, 11, 5, 18, 9, 20);
        LocalDateTime date2 = LocalDateTime.of(2025, 11, 5, 18, 9, 20);

        votes.add(new Vote(voter1, date2, Party.DEMOCRAT));
        votes.add(new Vote(voter2, date1, Party.DEMOCRAT));
        votes.add(new Vote(voter3, date2, Party.DEMOCRAT));
        votes.add(new Vote(voter4, date2, Party.REPUBLICAN));

        votes.sort(new VoteComparator());
        System.out.println("Sorting Test:");
        System.out.println(votes);

        assertEquals(2, votes.get(0).getVoter().getId());
        assertEquals(1, votes.get(1).getVoter().getId());
        assertEquals(3, votes.get(2).getVoter().getId());
        assertEquals(4, votes.get(3).getVoter().getId());
    }
}