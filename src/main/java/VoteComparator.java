import java.util.Comparator;

public class VoteComparator implements Comparator<Vote> {

    @Override
    public int compare(Vote o1, Vote o2) {
        return switch (o1.getDateTime().compareTo(o2.getDateTime())) {
            case -1 -> -1;
            case 1 -> 1;
            default -> switch (o1.getVoter().compareTo(o2.getVoter())) {
                case -1 -> -1;
                case 1 -> 1;
                default -> switch (o1.getParty().toString().compareTo(o2.getParty().toString())) {
                    case -1 -> -1;
                    case 1 -> 1;
                    default -> 0;
                };
            };
        };
    }
}
