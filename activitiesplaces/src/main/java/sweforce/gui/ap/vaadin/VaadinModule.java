package sweforce.gui.ap.vaadin;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import sweforce.gui.ap.history.Historian;
import sweforce.gui.ap.place.ConfirmationHandler;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/6/12
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class VaadinModule extends AbstractModule {

    private final UI root;

    public VaadinModule(UI root) {
        this.root = root;
    }

    @Override
    protected void configure() {
        bind(UI.class).toInstance(root);
        bind(ConfirmationHandler.class).to(RootConfirmationHandler.class);
        bind(Historian.class).to(VaadinPageHistorian.class);
    }

    @Provides
    Page providePage(UI root){
        return root.getPage();
    }
}
