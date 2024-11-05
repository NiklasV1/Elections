import java.time.Year;
import java.util.*;

public class VotingMachine {
    Year year;
    List<Vote> votes = new ArrayList<>();

    public VotingMachine(Year year) {
        if (year == null) {
            throw new NullPointerException();
        }
        this.year = year;
    }

    public VotingMachine() {
        this.year = Year.now();
    }

    public void add(Vote vote) {
        if (vote == null) {
            throw new NullPointerException();
        }
        // TODO
    }

    public void addAll(Collection<Vote> votes) {
        if (votes == null) {
            throw new NullPointerException();
        }
        // TODO
    }

    public List<Vote> getVotes() {
        List<Vote> result = new ArrayList<>();

        for (Vote vote : votes) {
            result.add(vote.getClone());
        }

        return result;
    }

    public List<Voter> getVoters() {
        List<Voter> result = new ArrayList<>();

        for (Vote vote : votes) {
            result.add(vote.getVoter().getClone());
        }

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
}
