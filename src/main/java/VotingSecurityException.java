public class VotingSecurityException extends Exception {
    String message;

    public VotingSecurityException(String message) {
        this.message = message;
    }
}