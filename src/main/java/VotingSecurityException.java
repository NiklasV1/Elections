public class VotingSecurityException extends Exception {
    public VotingSecurityException(String message) throws VotingSecurityException {
        throw new VotingSecurityException(message);
    }
}
