package sweforce.reactive.observer;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/2/12
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Observer<X> {
    void update(X v);
}
