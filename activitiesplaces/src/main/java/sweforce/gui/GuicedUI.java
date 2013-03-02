package sweforce.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import sweforce.gui.ap.place.PlacesRunner;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 2/19/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GuicedUI extends UI {

    public static final String ROOT_COMPONENT = "Guiced.UI.layout";

    @Inject
    @Named(ROOT_COMPONENT)
    private Component component;

    private final Injector injector;
    protected GuicedUI() {
        injector =  Guice.createInjector(getModules());
        injector.injectMembers(this);
    }

    public abstract Module[] getModules();

    @Override
    protected final void init(VaadinRequest request) {
        this.setContent(component);
        PlacesRunner placesRunner = injector.getInstance(PlacesRunner.class);
        try {
            placesRunner.start();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }



}
