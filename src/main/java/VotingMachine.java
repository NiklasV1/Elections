import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

public class VotingMachine {
    Year year;
    List<Vote> votes = new ArrayList<>();
    LocalDateTime start;
    LocalDateTime end;

    public VotingMachine(Year year) {
        if (year == null) {
            throw new NullPointerException();
        }
        this.year = year;
        start = LocalDateTime.of(year.getValue(), 10, 1, 0, 0, 0);
        end = LocalDateTime.of(year.getValue(), 11, 8, 23, 59, 59);
    }

    public VotingMachine() {
        this.year = Year.now();
        start = LocalDateTime.of(year.getValue(), 10, 1, 0, 0, 0);
        end = LocalDateTime.of(year.getValue(), 11, 8, 23, 59, 59);
    }

    public void add(Vote vote) {
        if (vote == null) {
            throw new NullPointerException();
        }
        if (vote.getDateTime().isBefore(start) || vote.getDateTime().isAfter(end)) {
            throw new DateTimeException("Date outside of accepted range!");
        }

        Vote previousVote = getVote(vote.getVoter());
        VoteComparator comparator = new VoteComparator();
        if (getVote(vote.getVoter()) == null || (comparator.compare(vote, previousVote)) > 0) {
            votes.remove(previousVote);
            votes.add(vote);
        }
    }

    public void addAll(Collection<Vote> votes) {
        if (votes == null) {
            throw new NullPointerException();
        }

        for (Vote vote : votes) {
            add(vote);
        }
    }

    public List<Vote> getVotes() {
        List<Vote> result = new ArrayList<>();

        for (Vote vote : votes) {
            result.add(vote.getClone());
        }

        result.sort(new VoteComparator());
        return result;
    }

    public List<Voter> getVoters() {
        List<Voter> result = new ArrayList<>();

        for (Vote vote : votes) {
            result.add(vote.getVoter().getClone());
        }

        Collections.sort(result);
        return result;
    }

    public Vote getVote(Voter voter) {
        if (voter == null) {
            throw new NullPointerException();
        }

        for (Vote vote : votes) {
            if (vote.getVoter().equals(voter)) {
                return vote;
            }
        }

        return null;
    }

    public Year getYear() {
        return year;
    }

    public Map<Party, Double> getDistribution() {


        // TODO
        return null;
    }

    public List<String> showDistribution() {
        // TODO
        return null;
    }

    String getSecurityCode(Voter voter, Party party) {
        if (voter == null || party == null) {
            throw new NullPointerException();
        }
        // TODO
        return null;
    }

    public void manipulateVote(int voterId, Party party, String securityCode) throws VotingSecurityException {
        if (party == null || securityCode == null) {
            throw new NullPointerException();
        }
        // TODO
    }
}
