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
        votes.sort(new VoteComparator());
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
        // Deep clones to prevent mutability
        List<Vote> result = new ArrayList<>();

        for (Vote vote : votes) {
            result.add(vote.getClone());
        }

        return Collections.unmodifiableList(result);
    }

    public List<Voter> getVoters() {
        // Deep clones to prevent mutability
        List<Voter> result = new ArrayList<>();

        for (Vote vote : votes) {
            result.add(vote.getVoter().getClone());
        }

        Collections.sort(result);
        return Collections.unmodifiableList(result);
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
        Map<Party, Double> result = new HashMap<>();
        Map<Party, Integer> totalVotes = new HashMap<>();

        for (Party party : Party.values()) {
            totalVotes.put(party, 0);
        }

        for (Vote vote : votes) {
            totalVotes.put(vote.getParty(), totalVotes.get(vote.getParty()) + 1);
        }

        for (Party party : Party.values()) {
            if (!votes.isEmpty()){
                result.put(party, totalVotes.get(party).doubleValue() / votes.size());
            } else {
                result.put(party, 0d);
            }
        }

        return result;
    }

    public List<String> showDistribution() {
        Map<Party, Double> distribution = getDistribution();
        List<String> result = new ArrayList<>();

        for (Party p : Party.values()) {
            int percentagePoints = (int) Math.round(distribution.get(p) * 100);
            String partyName = p.toString();

            String distributionString = partyName + " ".repeat(Math.max(0, 18 - partyName.length()) + 1) + "#".repeat(percentagePoints);
            result.add(distributionString);
        }

        result.sort(Comparator.comparingInt(String::length));
        return result.reversed();
    }

    String getSecurityCode(Voter voter, Party party) {
        if (voter == null || party == null) {
            throw new NullPointerException();
        }
        return securityCodeFromName(voter.getFirstName() + voter.getLastName()) + (voter.getId() % 17) + (party.getColorCode() % 19);
    }

    public void manipulateVote(int voterId, Party party, String securityCode) throws VotingSecurityException {
        if (party == null || securityCode == null) {
            throw new NullPointerException();
        }

        Vote vote = getVote(new Voter("A", "A", voterId));
        if (vote == null) {
            throw new IllegalArgumentException();
        }

        String realSecurityCode = getSecurityCode(vote.getVoter(), party);
        if (!Objects.equals(realSecurityCode, securityCode)) {
            throw new VotingSecurityException("Wrong Security Code!");
        } else {
            vote.party = party;
        }
    }

    private String securityCodeFromName(String name) {
        StringBuilder result = new StringBuilder();
        int length = name.length() / 2;
        for (int i = 0; i < length; i++) {
            char a = name.charAt(i * 2);
            char b = name.charAt(i * 2 + 1);
            if (a >= 97 && b >= 97) {
                result.append(b).append(a);
            } else {
                result.append(a).append(b);
            }
        }
        if (name.length() % 2 == 1) {
            result.append(name.charAt(name.length() - 1));
        }

        return result.toString();
    }
}
