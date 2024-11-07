import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class VotingConsole {
    public static void main(String[] args) {
        VotingMachine votingMachine = new VotingMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (mainMenu(votingMachine, scanner)) {
                case CREATE -> createMenu(votingMachine, scanner);
                case DISTRIBUTION -> distributionMenu(votingMachine);
                case ALLVOTES -> allVotesMenu(votingMachine);
                case MANIPULATE -> manipulateMenu(votingMachine, scanner);
                case EXIT -> {
                    System.out.println("bye bye!");
                    System.exit(0);
                }
                case null -> {}
            }
        }

    }

    public static Menus mainMenu(VotingMachine votingMachine, Scanner scanner) {
        System.out.println("Hello and welcome to VotingMachine " + votingMachine.getYear().getValue() + "!");
        System.out.println("[c]reate vote, show [d]istribution, show [a]ll, or e[x]it");
        return switch (scanner.nextLine()) {
            case "c" -> Menus.CREATE;
            case "d" -> Menus.DISTRIBUTION;
            case "a" -> Menus.ALLVOTES;
            case "m" -> Menus.MANIPULATE;
            case "x" -> Menus.EXIT;
            default -> null;
        };
    }

    public static void createMenu(VotingMachine votingMachine, Scanner scanner) {
        System.out.println("firstname:");
        String firstNameInput = scanner.nextLine();
        if (Objects.equals(firstNameInput, "")) {
            System.out.println("invalid input!");
            return;
        }

        System.out.println("lastname:");
        String lastNameInput = scanner.nextLine();
        if (Objects.equals(lastNameInput, "")) {
            System.out.println("invalid input!");
            return;
        }

        System.out.println("id:");
        String idInput = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (Exception e) {
            System.out.println("invalid input!");
            return;
        }
        if (id <= 0) {
            System.out.println("invalid input!");
            return;
        }
        Voter voter = new Voter(firstNameInput, lastNameInput, id);

        if (votingMachine.getVote(voter) != null) {
            System.out.println("vote for voter already exists!");
            return;
        }

        System.out.println("party: [r]epublican, [d]emocratic, [g]reen, [c]onstitution, [l]ibertarian");
        Party party = switch (scanner.nextLine()) {
            case "r" -> Party.REPUBLICAN;
            case "d" -> Party.DEMOCRAT;
            case "g" -> Party.GREEN;
            case "c" -> Party.CONSTITUTION;
            case "l" -> Party.LIBERTARIAN;
            default -> null;
        };

        if (party == null) {
            System.out.println("invalid input!");
            return;
        }

        votingMachine.add(new Vote(voter, LocalDateTime.now(), party));
        System.out.println("vote created");
    }

    public static void distributionMenu(VotingMachine votingMachine) {
        List<String> distribution = votingMachine.showDistribution();
        for (String result : distribution) {
            System.out.println(result);
        }
    }

    public static void allVotesMenu(VotingMachine votingMachine) {
        List<Vote> votes = votingMachine.getVotes();

        for (Vote vote : votes) {
            System.out.println(vote.toString());
        }
    }

    public static void manipulateMenu(VotingMachine votingMachine, Scanner scanner) {
        System.out.println("voter id:");
        String idInput = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(idInput);
        } catch (Exception e) {
            System.out.println("invalid input!");
            return;
        }
        if (id <= 0) {
            System.out.println("invalid input!");
            return;
        }

        System.out.println("party: [r]epublican, [d]emocratic, [g]reen, [c]onstitution, [l]ibertarian");
        Party party = switch (scanner.nextLine()) {
            case "r" -> Party.REPUBLICAN;
            case "d" -> Party.DEMOCRAT;
            case "g" -> Party.GREEN;
            case "c" -> Party.CONSTITUTION;
            case "l" -> Party.LIBERTARIAN;
            default -> null;
        };

        if (party == null) {
            System.out.println("invalid input!");
            return;
        }

        System.out.println("security code:");
        String securityCodeInput = scanner.nextLine();

        try {
            votingMachine.manipulateVote(id, party, securityCodeInput);
        } catch (Exception e) {
            if (e.getClass() == IllegalArgumentException.class) {
                System.out.println("voter doesn't exist!");
            } else if (e.getClass() == VotingSecurityException.class) {
                System.out.println("incorrect security code!");
            } else {
                System.out.println("invalid input!");
            }
        }
    }
}
