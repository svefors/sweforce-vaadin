package sweforce.gui.event;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResettableEventBusTest extends EventBusTestBase {

    @Test
    public void testSimple() {
        CountingEventBus wrapped = new CountingEventBus();
        ResettableEventBus subject = new ResettableEventBus(wrapped);

        Class<? extends Event> type = FooEvent.class;

        assertEquals(0, wrapped.getHandlerCount(type));

        subject.addHandler(FooEvent.class, fooHandler1);
//    subject.addHandlerToSource(type, "baker", fooHandler2);
        subject.addHandler(FooEvent.class, fooHandler3);

        assertEquals(2, wrapped.getHandlerCount(type));

        subject.fireEvent(new FooEvent());
        assertFired(fooHandler1, fooHandler3);
//    assertNotFired(fooHandler2);

        reset();

//    subject.fireEventFromSource(new FooEvent(), "baker");
//    assertFired(fooHandler1, fooHandler2, fooHandler3);

        reset();

        subject.removeHandlers();
        assertEquals(0, wrapped.getHandlerCount(type));

        subject.fireEvent(new FooEvent());
        assertNotFired(fooHandler1, fooHandler3);
    }

    @Test
    public void testNestedResetInnerFirst() {
        CountingEventBus wrapped = new CountingEventBus();
        ResettableEventBus wideScope = new ResettableEventBus(wrapped);
        ResettableEventBus narrowScope = new ResettableEventBus(wideScope);

        Class<? extends Event> type = FooEvent.class;

        wideScope.addHandler(FooEvent.class, fooHandler1);
        narrowScope.addHandler(FooEvent.class, fooHandler2);

        wrapped.fireEvent(new FooEvent());
        assertFired(fooHandler1, fooHandler2);

        reset();

        /*
        * When I remove handlers from the narrow resettable, it should have no
        * effect on handlers registered with the wider instance.
        */

        narrowScope.removeHandlers();

        wrapped.fireEvent(new FooEvent());
        assertFired(fooHandler1);
        assertNotFired(fooHandler2);
    }

    @Test
    public void testNestedResetOuterFirst() {
        CountingEventBus wrapped = new CountingEventBus();
        ResettableEventBus wideScope = new ResettableEventBus(wrapped);
        ResettableEventBus narrowScope = new ResettableEventBus(wideScope);

        Class<? extends Event> type = FooEvent.class;

        wideScope.addHandler(FooEvent.class, fooHandler1);
        narrowScope.addHandler(FooEvent.class, fooHandler2);

        wrapped.fireEvent(new FooEvent());
        assertFired(fooHandler1, fooHandler2);

        reset();

        /*
        * When I remove handlers from the first resettable, handlers registered by
        * the narrower scoped one that wraps it should also be severed.
        */

        wideScope.removeHandlers();

        wrapped.fireEvent(new FooEvent());
        assertNotFired(fooHandler1);
        assertNotFired(fooHandler2);
    }

    @Test
    public void testManualRemoveMemory() {
        SimpleEventBus eventBus = new SimpleEventBus();
        ResettableEventBus subject = new ResettableEventBus(eventBus);



        HandlerRegistration registration1 = subject.addHandler(FooEvent.class, fooHandler1);
        HandlerRegistration registration2 = subject.addHandler(FooEvent.class, fooHandler2);
        HandlerRegistration registration3 = subject.addHandler(FooEvent.class, fooHandler3);

        registration1.removeHandler();
        registration2.removeHandler();
        registration3.removeHandler();

        /*
        * removing handlers manually should remove registration from the internal
        * set.
        */

        assertEquals(0, subject.getRegistrationSize());

        subject.removeHandlers();

        // Expect nothing to happen. Especially no exceptions.
        registration1.removeHandler();
    }

    @Test
    public void testNestedRemoveMemory() {
        SimpleEventBus eventBus = new SimpleEventBus();
        ResettableEventBus wideScope = new ResettableEventBus(eventBus);
        ResettableEventBus narrowScope = new ResettableEventBus(wideScope);


        wideScope.addHandler(FooEvent.class, fooHandler1);
        narrowScope.addHandler(FooEvent.class, fooHandler2);
        narrowScope.addHandler(FooEvent.class, fooHandler3);

        narrowScope.removeHandlers();
        wideScope.removeHandlers();

        /*
        * Internal registeration should be empty after calling removeHandlers
        */

        assertEquals(0, wideScope.getRegistrationSize());
        assertEquals(0, narrowScope.getRegistrationSize());

        wideScope.addHandler(FooEvent.class, fooHandler1);
        narrowScope.addHandler(FooEvent.class, fooHandler2);

        /*
        * Reverse remove order
        */

        wideScope.removeHandlers();
        narrowScope.removeHandlers();

        assertEquals(0, wideScope.getRegistrationSize());
        assertEquals(0, narrowScope.getRegistrationSize());
    }
}
