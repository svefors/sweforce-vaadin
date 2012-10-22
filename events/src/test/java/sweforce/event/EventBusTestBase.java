package sweforce.event;

import java.util.HashSet;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 7/17/12
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class EventBusTestBase {

    /**
     * Handler implementation to allow for easy testing of whether the handler is being called.
     */
    protected class Adaptor implements FooEvent.Handler, BarEvent.Handler {

        public void onFoo(FooEvent event) {
            add(this);
        }

        public void onBar(BarEvent event) {
            add(this);
        }

        @Override
        public String toString() {
            return "adaptor 1";
        }
    }

    protected Adaptor adaptor1 = new Adaptor();

    private HashSet<Object> active = new HashSet<Object>();

    protected FooEvent.Handler fooHandler1 = new FooEvent.Handler() {
        public void onFoo(FooEvent event) {
            add(fooHandler1);
        }

        @Override
        public String toString() {
            return "fooHandler 1";
        }
    };

    protected FooEvent.Handler fooHandler2 = new FooEvent.Handler() {
        public void onFoo(FooEvent event) {
            add(fooHandler2);
        }

        @Override
        public String toString() {
            return "fooHandler 2";
        }
    };

    protected FooEvent.Handler fooHandler3 = new FooEvent.Handler() {
        public void onFoo(FooEvent event) {
            add(fooHandler3);
        }

        @Override
        public String toString() {
            return "fooHandler 3";
        }
    };

    protected BarEvent.Handler barHandler1 = new BarEvent.Handler() {

        public void onBar(BarEvent event) {
            add(barHandler1);
        }

        @Override
        public String toString() {
            return "barHandler 1";
        }
    };

    protected BarEvent.Handler barHandler2 = new BarEvent.Handler() {

        public void onBar(BarEvent event) {
            add(barHandler2);
        }

        @Override
        public String toString() {
            return "barHandler 2";
        }
    };

    protected BarEvent.Handler barHandler3 = new BarEvent.Handler() {

        public void onBar(BarEvent event) {
            add(barHandler3);
        }

        @Override
        public String toString() {
            return "barHandler 3";
        }
    };

    protected void add(Object handler) {
        active.add(handler);
    }

    protected void assertFired(Object... handler) {
        for (int i = 0; i < handler.length; i++) {
            assertTrue(handler[i] + " should have fired", active.contains(handler[i]));
        }
    }

    protected void assertNotFired(Object... handler) {
        for (int i = 0; i < handler.length; i++) {
            assertFalse(handler[i] + " should not have fired",
                    active.contains(handler[i]));
        }
    }

    protected void reset() {
        active.clear();
    }
}
