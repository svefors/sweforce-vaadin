package sweforce.gui.vaadin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 2/19/13
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GuicedUI extends UI {

    public static final String ROOT_COMPONENT = "GuicedUI.root.component";

    @Inject
    @RootComponent
    private Component component;

    private final Injector injector;

    protected GuicedUI() {
        injector = Guice.createInjector(getModules());
        injector.injectMembers(this);
    }

    public abstract Module[] getModules();

    @Override
    protected final void init(VaadinRequest request) {
        this.setContent(component);
    }

    @Qualifier
    @Target({FIELD, PARAMETER, METHOD})
    @Retention(RUNTIME)
    public static @interface RootComponent {

    }

}
