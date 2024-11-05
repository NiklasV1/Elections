import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotingSecurityExceptionTest {

    @Test
    void testVotingSecurityException() {
        VotingSecurityException exception = new VotingSecurityException("test");
        assertEquals("test", exception.message);
    }
}