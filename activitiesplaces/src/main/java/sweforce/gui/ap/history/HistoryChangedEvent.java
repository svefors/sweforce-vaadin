package sweforce.gui.ap.history;

import sweforce.event.EventHandler;
import sweforce.event.Event;

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

    public String getPlaceToken() {
        return placeToken;
    }

    @Override
    public void dispatch(Handler handler) {
        handler.onHistoryChange(this);
    }

    public static interface Handler extends EventHandler {

        public void onHistoryChange(HistoryChangedEvent event);

    }
}
