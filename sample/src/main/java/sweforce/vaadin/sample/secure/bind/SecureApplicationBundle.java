package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.bind.BinderModule;
import se.jbee.inject.bootstrap.BootstrapperBundle;
import sweforce.vaadin.layout.style1.Style1Layout;
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

        ActivityRegionModule mainActivityRegionModule = new ActivityRegionModule(Style1Layout.MyRegion.MAIN);
        ActivityRegionModule leftActivityRegionModule = new ActivityRegionModule(Style1Layout.MyRegion.SPLIT_LEFT);
        ActivityRegionModule rightActivityRegionModule = new ActivityRegionModule(Style1Layout.MyRegion.SPLIT_RIGHT);
        ActivityRegionModule toolbarActivityRegionModule = new ActivityRegionModule(Style1Layout.MyRegion.TOOLBAR);

        install(mainActivityRegionModule);
        install(mainActivityRegionModule.newActivityMapping(Role1Activity.ActivityMapper.class));
        install(mainActivityRegionModule.newActivityMapping(Role2Activity.ActivityMapper.class));
        install(mainActivityRegionModule.newActivityMapping(NoroleActivity.ActivityMapper.class));
        install(mainActivityRegionModule.newActivityMapping(LoginActivity.class));

        install(leftActivityRegionModule);
        install(rightActivityRegionModule);

        install(toolbarActivityRegionModule);
        install(toolbarActivityRegionModule.newActivityMapping(MenuActivity.class));

        //for security
        install(new BinderModule() {
            @Override
            protected void declare() {

                bind(SecurityFacade.class).to(ShiroSecurityFacade.class);
            }
        });


    }

}
