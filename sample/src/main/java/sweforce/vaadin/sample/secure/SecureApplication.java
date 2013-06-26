/*
 * Copyright 2012 Mats Svefors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package sweforce.vaadin.sample.secure;

import com.google.inject.Module;
import com.google.inject.name.Names;
import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sweforce.gui.place.DefaultPlace;
import sweforce.gui.place.Place;
import sweforce.gui.vaadin.GuicedUI;
import sweforce.gui.place.PlaceHistoryModule;
import sweforce.gui.vaadin.VaadinModule;
import sweforce.gui.guice.RegionPlacesModule;
import sweforce.vaadin.layout.style1.Style1Layout;
import sweforce.vaadin.sample.secure.menu.MenuActivity;
import sweforce.vaadin.sample.secure.norole.NoroleActivity;
import sweforce.vaadin.sample.secure.norole.NorolePlace;
import sweforce.vaadin.sample.secure.role1.Role1Activity;
import sweforce.vaadin.sample.secure.role1.Role1Place;
import sweforce.vaadin.sample.secure.role2.Role2Activity;
import sweforce.vaadin.sample.secure.role2.Role2Place;
import sweforce.vaadin.security.SecureMvpModule;
import sweforce.vaadin.security.shiro.ShiroSecurityModule;

/**
 * Sample Application
 */
public class SecureApplication extends GuicedUI {

    private static Logger logger = LoggerFactory.getLogger(SecureApplication.class);

    @Override
    public Module[] getModules() {
        return new Module[]{
                new VaadinModule(this),
                new ShiroSecurityModule(),
                new SecureMvpModule(),
                new PlaceHistoryModule(),
                //in(region).at(place).invoke(Activity)
                //at(RegistrationPlace.class).in(MenuRegion).do(Activity.class)
                new RegionPlacesModule() {
                    @Override
                    protected void configurePlaces() {
                        bindPlace(NorolePlace.class).in(Style1Layout.Region.MAIN).to(NoroleActivity.class);
                        bindPlace(NorolePlace.class).in(Style1Layout.Region.TOOLBAR).to(MenuActivity.class);

                        bindPlace(Role1Place.class).in(Style1Layout.Region.MAIN).to(Role1Activity.class);
                        bindPlace(Role1Place.class).in(Style1Layout.Region.TOOLBAR).to(MenuActivity.class);

                        bindPlace(Role2Place.class).in(Style1Layout.Region.MAIN).to(Role2Activity.class);
                        bindPlace(Role2Place.class).in(Style1Layout.Region.TOOLBAR).to(MenuActivity.class);

                        bind(Component.class).annotatedWith(GuicedUI.RootComponent.class).to(Style1Layout.class);
                        bind(Place.class).annotatedWith(DefaultPlace.class).toInstance(new NorolePlace());

                    }
                }
        };
    }
    /*


    (NorolePlace.class, Style1Layout.Region.MAIN), NoroleActivity.class

    NorolePlace.class, Style1Layout.Region.MAIN, NoroleActivity.class
    NorolePlace.class, Style1Layout.Region.TOOLBAR, MenuActivity.class

    Alt1:
    Region -> (Place -> Provider)
    only one multibinding

    Alt2:
    named
    Region -> (ActivityMapper)

    Region -> Set(ActivityMappings)
     */


//    private RegionPlacesModule myModule = new  RegionPlacesModule(){
//
//        @Override
//        protected void configure(Config config) {
//            config
//                    .forPlace(NorolePlace.class)
//                    .inRegion(Style1Layout.Region.MAIN)
//                    .useActivity(NoroleActivity.class)
//                    .inRegion(Style1Layout.Region.TOOLBAR)
//                    .useActivity(MenuActivity.class)
//
//                    .forPlace(Role1Place.class)
//                    .inRegion(Style1Layout.Region.MAIN)
//                    .useActivity(Role1Activity.class)
//                    .inRegion(Style1Layout.Region.TOOLBAR)
//                    .useActivity(MenuActivity.class)
//
//                    .forPlace(Role2Place.class)
//                    .inRegion(Style1Layout.Region.MAIN)
//                    .useActivity(Role2Activity.class)
//                    .inRegion(Style1Layout.Region.TOOLBAR)
//                    .useActivity(MenuActivity.class)
//
//                    .forPlace(LoginPlace.class)
//                    .inRegion(Style1Layout.Region.MAIN)
//                    .useActivity(LoginActivity.class)
//
//                    .forPlace(LogoutPlace.class)
//                    .inRegion(Style1Layout.Region.MAIN)
//                    .useActivity(LoginActivity.class)
//
//                    ;
//            config.defaultPlaceInstance(new NorolePlace());
//            bind(Component.class).annotatedWith(Names.named(GuicedUI.ROOT_COMPONENT)).to(Style1Layout.class);
//        }
//
//
//
//        /*
//
//        injector.getInstance(Key.get(String.class, Names.named(Style1Layout.Style1Region.TOOLBAR.toString())));
//
//        injector.getInstance(Key.get(String.class, Names.named(Style1Layout.Style1Region.MAIN.toString())));
//                placeActivityMap.put(NorolePlace.class, menuActivity);
//        placeActivityMap.put(Role1Place.class, menuActivity);
//        placeActivityMap.put(Role2Place.class, menuActivity);
//         */
//    };


////    @Override
////    protected void init(VaadinRequest request) {
//////        this.getApplication().setRootPreserved(true);
////        injector = Guice.createInjector(new VaadinModule(this),
////                new ShiroSecurityModule(),
////                new SecureMvpModule(),
////                new PlaceHistoryModule(),
////                myModule);
////
////
////        EventBus eventBus = injector.getInstance(EventBus.class);
//
//
//
//
//
//        Style1Layout style1Layout = new Style1Layout();
//
//        this.setContent(style1Layout);
//
//        RegionalActivityMappings regionalActivityMappings = new RegionalActivityMappings(injector);
//
//
////        regionalActivityMappings.forPlace()
//
//        RegionalDisplayActivityManager regionalDisplayActivityManager =
//                new RegionalDisplayActivityManager(style1Layout);
//
//        regionalDisplayActivityManager.registerActivityMapper(
//                injector.getInstance(EventBus.class),
//                Style1Layout.Style1Region.MAIN,
//                injector.getInstance(CenterActivityMapper.class)
//        );
//
//        regionalDisplayActivityManager.registerActivityMapper(
//                injector.getInstance(EventBus.class),
//                Style1Layout.Style1Region.TOOLBAR,
//                injector.getInstance(NorthActivityMapper.class)
//        );
//
//        final SingleThreadedActivityManager centerActivityManager = new SingleThreadedActivityManager(
//                injector.getInstance(CenterActivityMapper.class),
//                injector.getInstance(EventBus.class)
//        );
//
//        centerActivityManager.setDisplay(style2Layout.getCenterDisplay());
//
//        final ActivityManager northActivityManager = new ActivityManager(
//                injector.getInstance(NorthActivityMapper.class),
//                injector.getInstance(EventBus.class)
//        );
//
//        northActivityManager.setDisplay(style2Layout.getHeaderDisplay());
//
//        PlacesRunner placesRunner = injector.getInstance(PlacesRunner.class);
//        try {
//            placesRunner.start();
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }

}
