package sweforce.gui.vaadin;

import com.vaadin.ui.UI;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.place.PlaceController;
import sweforce.gui.place.PlaceHistoryHandler;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/21/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class VaadinSilkModule extends BinderModule {

//    private final UI ui;
//
//    public VaadinSilkModule(UI ui) {
//        this.ui = ui;
//    }

    @Override
    protected void declare() {
        bind(PlaceHistoryHandler.Historian.class).to(new VaadinPageHistorian(UI.getCurrent().getPage()));
        bind(PlaceController.ConfirmationHandler.class).to(CurrentRootConfirmationHandler.class);
        bind(CurrentRootConfirmationHandler.class).toConstructor();
    }
}
