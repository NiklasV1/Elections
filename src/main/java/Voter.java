import java.util.Objects;

public class Voter implements Comparable<Voter> {
    String firstName;
    String lastName;
    int id;

    public Voter(String firstName, String lastName, int id) {
        if (firstName == null || lastName == null) {
            throw new NullPointerException();
        }
        if (id <= 0 || firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + " (" + id + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Voter otherVoter = (Voter) obj;
            return id == otherVoter.id;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Voter o) {
        return switch (lastName.compareTo(o.lastName)) {
            case -1 -> -1;
            case 1 -> 1;
            default -> switch (firstName.compareTo(o.firstName)) {
                case -1 -> -1;
                case 1 -> 1;
                default -> 0;
            };
        };
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }

    public Voter getClone() {
        return new Voter(firstName, lastName, id);
    }
}
