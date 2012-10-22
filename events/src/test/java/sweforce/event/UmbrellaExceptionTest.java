package sweforce.event;

import org.junit.Test;
import sweforce.event.UmbrellaException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class UmbrellaExceptionTest {
    @Test
    public void testNone() {
        // Why?
        try {
            throw new UmbrellaException(Collections.<Throwable>emptySet());
        } catch (UmbrellaException e) {
            assertNull(e.getCause());
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testOne() {
        Set<Throwable> causes = new HashSet<Throwable>();
        String message = "Just me";
        RuntimeException theOne = new RuntimeException(message);
        causes.add(theOne);

        try {
            throw new UmbrellaException(causes);
        } catch (UmbrellaException e) {
            assertSame(theOne, e.getCause());
            assertEquals(UmbrellaException.ONE + message, e.getMessage());
        }
    }

    @Test
    public void testSome() {
        Set<Throwable> causes = new HashSet<Throwable>();
        String oneMessage = "one";
        RuntimeException oneException = new RuntimeException(oneMessage);
        causes.add(oneException);

        String twoMessage = "two";
        RuntimeException twoException = new RuntimeException(twoMessage);
        causes.add(twoException);

        try {
            throw new UmbrellaException(causes);
        } catch (UmbrellaException e) {
            // A bit non-deterministic for a unit test, but I've checked both paths --
            // rjrjr
            if (e.getCause() == oneException) {
                assertCauseMatchesFirstMessage(e, oneMessage, twoMessage);
            } else if (e.getCause() == twoException) {
                assertCauseMatchesFirstMessage(e, twoMessage, oneMessage);
            } else {
                fail("Expected one of the causes and its message");
            }
        }
    }

    private void assertCauseMatchesFirstMessage(UmbrellaException e, String firstMessage,
                                                String otherMessage) {
        assertTrue("Cause should be first message", e.getMessage().startsWith(
                2 + UmbrellaException.MULTIPLE + firstMessage));
        assertTrue("Should also see the other message", e.getMessage().contains(otherMessage));
    }
}
