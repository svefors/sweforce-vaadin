package sweforce.vaadin.sample.secure.bind;

import com.vaadin.ui.Component;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.event.EventModule;
import sweforce.gui.place.PlaceModule;
import sweforce.gui.place.PrefixPlaceTokenizerRegistry;
import sweforce.gui.region.Region;
import sweforce.gui.region.RegionalActivityFactoryRegistry;
import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.PlaceConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/5/13
 * Time: 2:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecureApplicationBundle extends BootstrapperBundle {

    @Override
    protected void bootstrap() {
        install(EventModule.class);
        install(SecurityModule.class);
        install(HistorianModule.class);
        install(PlaceModule.class);
        install(LayoutModule.class);
        install(PlacesModule.class);
        install(ActivityRegistryModule.class);
    }

    private static class ActivityRegistryModule extends BinderModule {
        @Override
        protected void declare() {
            autobind(RegionalActivityFactoryRegistry.class);
        }
    }

    private static class MainSectionModule extends BinderModule {
        @Override
        protected void declare() {

        }
    }


    private static class LayoutModule extends BinderModule {
        @Override
        protected void declare() {
            bind(Style1Layout.class).toConstructor();
            multibind(Region.class).to(Style1Layout.MyRegion.MAIN);
            multibind(Region.class).to(Style1Layout.MyRegion.TOOLBAR);
            multibind(Region.class).to(Style1Layout.MyRegion.SPLIT_LEFT);
            multibind(Region.class).to(Style1Layout.MyRegion.SPLIT_RIGHT);
            bind(Name.named("rootLayout"), Component.class).to(Style1Layout.class);
        }
    }

    private static class PlacesModule extends BinderModule {
        @Override
        protected void declare() {
            multibind(PrefixPlaceTokenizerRegistry.Contributor.class).to(PlaceConfiguration.class);
        }
    }

    public static final String ROOT_COMPONENT = "ROOT_COMPONENT";

    public static final Component getRootLayout(Injector injector) {
        return injector.resolve(Dependency.dependency(Component.class).named(Name.named(ROOT_COMPONENT)));
    }

}
