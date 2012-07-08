package sweforce.gui.ap.history;

import sweforce.gui.event.HandlerRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/1/12
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Historian {


    /**
     * Gets the current history token.
     *
     * @return the initial token, or the empty string if none is present.
     */
    public String getToken();

    /**
       * Adds a new browser history entry.
       * Calling this method will cause
       * {@link HistoryChangedEvent.Handler#onHistoryChange(HistoryChangedEvent)}
       * to be called as well.
       *
       * @param historyToken the token to associate with the new history item
       */
    public void newItem(String historyToken);


    HandlerRegistration addValueChangeHandler(
            HistoryChangedEvent.Handler handler);

}
