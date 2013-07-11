package sweforce.gui;

import sweforce.event.EventBus;
import sweforce.event.SimpleEventBus;
import sweforce.gui.activity.ActivityManager;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.activity.SingleThreadedActivityManager;
import sweforce.gui.activity.registry.ActivityFactoryRegistry;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.PrefixPlaceTokenizerRegistry;
import sweforce.gui.region.Region;

import java.util.HashMap;
import java.util.Map;

/**
 TODO rename this class
 TODO replace name with region
 TODO ma

 Container container = new RegionalActivitiesAndPlacesContainer();
 PrefixPlaceTokenizerConfiguration prefixConfiguration =
    container.getPrefixPlaceTokenizerConfiguration();
 prefixConfiguration.prefix("login").tokenizer(new LoginPlace.Tokenizer())
 prefixConfiguration.prefix("logout").tokenizer(new LogoutPlace.Tokenizer())


 application.setContent(container.getRootLayout())
 {
  attach() -> presenter.start()
 }
 */
public class RegionalActivitiesAndPlacesContainer {

    private EventBus eventBus = createEventBus();

    private PrefixPlaceTokenizerRegistry.Impl prefixPlaceTokenizerRegistry = new PrefixPlaceTokenizerRegistry.Impl();

    private Map<Region, ActivityFactoryRegistry.Impl> namedActivityFactoryRegistries =
            new HashMap<Region, ActivityFactoryRegistry.Impl>();

    public PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration<PrefixPlaceTokenizerRegistry.PrefixPlaceTokenizerConfiguration>
            getPrefixPlaceTokenizerConfiguration(){
        return prefixPlaceTokenizerRegistry;
    }

    public ActivityMapper activityMapperWithName(Region name){
        return namedActivityFactoryRegistries.get(name);
    }

    protected EventBus createEventBus() {
        return eventBus;
    }

    protected ActivityManager createActivityManager(Region  name){
        return new SingleThreadedActivityManager(activityMapperWithName(name), eventBus);
    }


}
