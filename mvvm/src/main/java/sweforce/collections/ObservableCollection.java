package sweforce.collections;

import sweforce.event.EventNotifier;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 10/24/12
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ObservableCollection<E> extends Collection<E>, EventNotifier<CollectionEvent.Handler<E>> {


}
