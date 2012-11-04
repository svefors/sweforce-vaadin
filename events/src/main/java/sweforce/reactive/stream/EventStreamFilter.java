package sweforce.reactive.stream;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 11/2/12
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EventStreamFilter<X,Y> {

    Y filter(X x);

    boolean accept(X x);

}
