package sweforce.reactive.stream;

import sweforce.reactive.observer.DefaultSubject;
import sweforce.reactive.observer.Observer;
import sweforce.reactive.observer.Subject;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/2/12
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputEventStream<I> /*implements Subject<I>*/ {

    protected DefaultSubject<I> inputSubject = new DefaultSubject<I>();

    public void addObserver(Observer<I> o) {
        inputSubject.addObserver(o);
    }

    protected void notifyObservers(I v) {
        inputSubject.notifyObservers(v);
    }

    public <O> OutputEventStream<O> bind(final EventStreamFilter<O,I> eventFilter) {
        final OutputEventStream<O> o = new OutputEventStream<O>();

        o.addObserver(new Observer<O>(){
            public void update(O v) {
                if (eventFilter.accept(v))
                    InputEventStream.this.notifyObservers(eventFilter.filter(v));
            }
        });

        return o;
    }
}
