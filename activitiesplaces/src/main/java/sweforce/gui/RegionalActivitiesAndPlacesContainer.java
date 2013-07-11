package sweforce.gui;

import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.activity.registry.ActivityFactoryRegistryImpl;
import sweforce.gui.place.PrefixPlaceTokenizerRegistry;
import sweforce.gui.region.Region;
import sweforce.gui.region.RegionalActivityFactoryRegistry;
import sweforce.gui.region.RegionalDisplay;

import java.util.HashMap;
import java.util.Map;

/**
 TODO rename this class
 TODO replace name with region
 TODO ma

 application.setContent(container.getRootLayout())
 {
  attach() -> {
    presenter.start() -> {
        for each region the presenter has{
            getActivityManager(region).setDisplay(regionalDisplay.getDisplay(region))
            don't like it. have to implement attach->presenter.start for each layout
        }
    }
  }
 }


 application.setContent(container.getRootLayout())
 //in Application.attach(){
    presenter.start() -> {
        display.getDisplay(region)
        historyHandler.handleCurrentFragment();
    }
 }

 */
public class RegionalActivitiesAndPlacesContainer {

//    private PrefixPlaceTokenizerRegistry.Impl prefixPlaceTokenizerRegistry = new PrefixPlaceTokenizerRegistry.Impl();

    private RegionalActivityFactoryRegistry regionalActivityFactoryRegistry;

    private RegionalDisplay regionalDisplay;

    public RegionalActivitiesAndPlacesContainer(RegionalActivityFactoryRegistry regionalActivityFactoryRegistry, RegionalDisplay regionalDisplay) {
        this.regionalActivityFactoryRegistry = regionalActivityFactoryRegistry;
        this.regionalDisplay = regionalDisplay;
    }

    public void start(){
        //x.getA
    }

    //    public void plugin(Region region, ActivityFactoryRegistry.Plugin plugin){
//        plugin.configure(configureActivities(region));
//    }





//    public PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration<PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration>
//            getPrefixPlaceTokenizerConfiguration(){
//        return prefixPlaceTokenizerRegistry;
//    }

//    public ActivityFactoryRegistry.Configuration<ActivityFactoryRegistry.Configuration> configureActivities(Region region){
//        ActivityFactoryRegistryImpl activityFactoryRegistry =  namedActivityFactoryRegistries.get(region);
//        if (activityFactoryRegistry == null){
//            activityFactoryRegistry = new ActivityFactoryRegistryImpl();
//            namedActivityFactoryRegistries.put(region, activityFactoryRegistry);
//        }
//        return activityFactoryRegistry;
//    }


}
