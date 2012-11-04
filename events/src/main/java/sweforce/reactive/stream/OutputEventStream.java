package sweforce.reactive.stream;

import sweforce.reactive.observer.DefaultSubject;
import sweforce.reactive.observer.Observer;
import sweforce.reactive.observer.Subject;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/2/12
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutputEventStream<O> implements Subject<O> {

    protected DefaultSubject<O> subject = new DefaultSubject<O>();

    public void addObserver(Observer<O> o) {
        subject.addObserver(o);
    }

    public void notifyObservers(O v) {
        subject.notifyObservers(v);
    }

    public void send(O v) {
        notifyObservers(v);
    }
}
