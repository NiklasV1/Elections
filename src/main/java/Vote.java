package jpp.exam.elections;

import java.text.Format;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Vote {
    Voter voter;
    LocalDateTime date;
    Party party;

    public Vote(Voter voter, LocalDateTime date, Party party) {
        if (voter == null || date == null || party == null) {
            throw new NullPointerException();
        }
        this.voter = voter;
        this.date = date;
        this.party = party;
    }

    public Voter getVoter() {
        return voter;
    }

    public LocalDateTime getDateTime() {
        return date;
    }

    public Party getParty() {
        return party;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ": " + voter.toString() + " - " + party.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Vote otherVote = (Vote) obj;
            return voter == otherVote.voter && date == otherVote.date && party == otherVote.party;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voter, date, party);
    }
}
