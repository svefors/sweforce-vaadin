package sweforce.gui.vaadin;


import com.vaadin.server.Page;
import sweforce.event.HandlerRegistration;
import sweforce.gui.place.PlaceHistoryHandler;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A wrapper around a Page
 * User: sveffa
 * Date: 7/1/12
 * Time: 12:32 PM
 */
public class VaadinPageHistorian implements PlaceHistoryHandler.Historian, Page.UriFragmentChangedListener{

    private final Page page;

    private Collection<PlaceHistoryHandler.HistoryChangedEvent.Handler> handlers = new ArrayList<PlaceHistoryHandler.HistoryChangedEvent.Handler>();

    @Inject
    public VaadinPageHistorian(Page page) {
        this.page = page;
        page.addUriFragmentChangedListener(this);
    }



    @Override
    public void uriFragmentChanged(Page.UriFragmentChangedEvent fragmentChangedEvent) {
        PlaceHistoryHandler.HistoryChangedEvent historyChangedEvent = new PlaceHistoryHandler.HistoryChangedEvent(fragmentChangedEvent.getUriFragment());
        for (PlaceHistoryHandler.HistoryChangedEvent.Handler handler : handlers){
            handler.onHistoryChange(historyChangedEvent);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final PlaceHistoryHandler.HistoryChangedEvent.Handler handler) {
        handlers.add(handler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                VaadinPageHistorian.this.handlers.remove(handler);
            }
        };
    }

    @Override
    public String getToken() {
        return page.getUriFragment();
    }

    @Override
    public void newItem(String token) {
        page.setUriFragment(token);
    }


}
