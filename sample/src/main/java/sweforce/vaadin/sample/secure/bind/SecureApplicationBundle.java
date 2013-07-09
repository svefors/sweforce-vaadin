package sweforce.vaadin.sample.secure.bind;

import com.vaadin.ui.Component;
import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.gui.activity.*;
import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.MainActivityMapper;
import sweforce.vaadin.sample.secure.ToolbarActivityMapper;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.SecurityFacade;
import sweforce.vaadin.security.login.LoginActivity;
import sweforce.vaadin.security.login.LoginPlace;
import sweforce.vaadin.security.logout.LogoutPlace;
import sweforce.vaadin.security.shiro.ShiroSecurityFacade;

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
        install(SecurePlaceControllerModule.class);
        install(HistorianModule.class);
        install(PlaceHistoryModule.class);
        install(PlaceHistoryModule.placePrefixMatcher("role1", new Role1Place.Tokenizer()));
        install(PlaceHistoryModule.placePrefixMatcher("role2", new Role2Place.Tokenizer()));
        install(PlaceHistoryModule.placePrefixMatcher("norole", new NorolePlace.Tokenizer()));
        install(PlaceHistoryModule.placePrefixMatcher("login", new LoginPlace.Tokenizer()));
        install(PlaceHistoryModule.placePrefixMatcher("logout", new LogoutPlace.Tokenizer()));

        install(ActivityMapperModule.class);
        install(LayoutModule.class);
        install(new ActivityManagerModule(Style1Layout.MyRegion.MAIN.toString()));
        install(new ActivityManagerModule(Style1Layout.MyRegion.TOOLBAR.toString()));

        //for security
        install(new BinderModule() {
            @Override
            protected void declare() {
                bind(SecurityFacade.class).to(ShiroSecurityFacade.class);
            }
        });


    }

    private static class ActivityManagerModule extends BinderModule {
        private final String name;

        private ActivityManagerModule(String name) {
            this.name = name;
        }

        @Override
        protected void declare() {
            bind(Name.named(name), ActivityManager.class).to(SingleThreadedActivityManager.class);
            injectingInto(Name.named(name), SingleThreadedActivityManager.class)
                    .bind(ActivityManager.class)
                    .to(Name.named(name), SingleThreadedActivityManager.class);
        }

    }

    private static class ActivityMapperModule extends BinderModule {
        @Override
        protected void declare() {
            bind(Name.named(Style1Layout.MyRegion.MAIN.toString()), ActivityMapper.class).to(MainActivityMapper.class);
            bind(Name.named(Style1Layout.MyRegion.TOOLBAR.toString()), ActivityMapper.class).to(ToolbarActivityMapper.class);
        }
    }

    private static class LayoutModule extends BinderModule {
        @Override
        protected void declare() {
            bind(Name.named("rootLayout"), Component.class).to(Style1Layout.class);
        }
    }

    public static final String ROOT_COMPONENT = "ROOT_COMPONENT";

    public static final Component getRootLayout(Injector injector) {
        return injector.resolve(Dependency.dependency(Component.class).named(Name.named(ROOT_COMPONENT)));
    }

}
