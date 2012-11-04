package sweforce.reactive.stream;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/3/12
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class IdentityEventStreamFilter<X> implements EventStreamFilter<X,X> {

    public X filter(X x) {
        return x;
    }

    public boolean accept(X x) {
        return true;
    }
}
