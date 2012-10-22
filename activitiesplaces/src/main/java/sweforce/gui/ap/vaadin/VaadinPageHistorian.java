package sweforce.gui.ap.vaadin;


import com.vaadin.server.Page;
import sweforce.event.HandlerRegistration;
import sweforce.gui.ap.history.Historian;
import sweforce.gui.ap.history.HistoryChangedEvent;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A wrapper around a Page
 * User: sveffa
 * Date: 7/1/12
 * Time: 12:32 PM
 */
public class VaadinPageHistorian implements Historian, Page.FragmentChangedListener{

    private final Page page;

    private Collection<HistoryChangedEvent.Handler> handlers = new ArrayList<HistoryChangedEvent.Handler>();

    @Inject
    public VaadinPageHistorian(Page page) {
        this.page = page;
        page.addFragmentChangedListener(this);
    }

    @Override
    public void fragmentChanged(Page.FragmentChangedEvent fragmentChangedEvent) {
        HistoryChangedEvent historyChangedEvent = new HistoryChangedEvent(fragmentChangedEvent.getFragment());
        for (HistoryChangedEvent.Handler handler : handlers){
            handler.onHistoryChange(historyChangedEvent);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final HistoryChangedEvent.Handler handler) {
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
        return page.getFragment();
    }

    @Override
    public void newItem(String token) {
        page.setFragment(token);
    }


}
