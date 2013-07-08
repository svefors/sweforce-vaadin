package sweforce.vaadin.sample.secure.bind;

import com.vaadin.ui.UI;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.PlaceHistoryHandler;
import sweforce.gui.vaadin.VaadinPageHistorian;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:01 AM
* To change this template use File | Settings | File Templates.
*/
public class HistorianModule extends BinderModule {

    @Override
    protected void declare() {
        //TODO: fix UI.getCurrent call
        bind(PlaceHistoryHandler.Historian.class).to(new VaadinPageHistorian(UI.getCurrent().getPage()));
    }
}
