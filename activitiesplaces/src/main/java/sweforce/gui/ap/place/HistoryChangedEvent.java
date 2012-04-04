package sweforce.gui.ap.place;

import sweforce.gui.event.Event;
import sweforce.gui.event.EventHandler;

/**
 * Created by IntelliJ IDEA.
 * User: sveffa
 * Date: 4/4/12
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoryChangedEvent implements Event<HistoryChangedEvent.Handler> {

    private final String placeToken;

    public HistoryChangedEvent(String placeToken) {
        this.placeToken = placeToken;
    }

    @Override
    public void dispatch(Handler handler) {
        handler.onHistoryChange(this.placeToken);
    }

    public static interface Handler extends EventHandler{

        public void onHistoryChange(String placeToken);

    }
}
