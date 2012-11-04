package sweforce.reactive.observer;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/2/12
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Subject<X> {

    void addObserver(Observer<X> o);

    void notifyObservers(X v);

//    public static class DefaultSubject<X> implements Subject<X> {
//        List<Observer> observers = new LinkedList<Observer>();
//
//        public void addObserver(Observer<X> o) {
//            observers.add(o);
//        }
//
//        public void notifyObservers(X v) {
//            for (Observer o : observers) {
//                o.update(v);
//            }
//        }
//    }
}
