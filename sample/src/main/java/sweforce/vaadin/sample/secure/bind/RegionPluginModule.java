package sweforce.vaadin.sample.secure.bind;

import se.jbee.inject.Name;
import se.jbee.inject.bind.BinderModule;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlacesWithPrefixPlaceHistoryMapperImpl;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.PlaceTokenizerUtil;
import sweforce.gui.region.Region;

/**
* Created with IntelliJ IDEA.
* User: sveffa
* Date: 7/5/13
* Time: 2:00 AM
* To change this template use File | Settings | File Templates.
*/
public class RegionPluginModule extends BinderModule {
    private final Region region;

    private final Class<? extends ActivityMapper> activityMapper;

    private final Class<? extends Place> placeClass;

    public RegionPluginModule(Region region, Class<? extends Place> placeClass, Class<? extends ActivityMapper> activityMapper) {
        this.activityMapper = activityMapper;
        this.region = region;
        this.placeClass = placeClass;
    }

    @Override
    protected final void declare() {
        multibind(Name.named(region.toString()), ActivityMapper.class).to(activityMapper);
        Class<? extends PlaceTokenizer> placeTokenizerClass =
                PlaceTokenizerUtil.getDeclaredPlaceTokenizerClass(placeClass);
        String prefix = "apa";//PlaceTokenizerUtil.getPrefixAnnotationValue(placeTokenizerClass);
        try {
            multibind(PlacesWithPrefixPlaceHistoryMapperImpl.PrefixPlaceTokenizerMapping.class).to(
                    new PlacesWithPrefixPlaceHistoryMapperImpl.PrefixPlaceTokenizerMapping(
                            prefix, placeTokenizerClass.newInstance()));
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }


}
