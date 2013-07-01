package sweforce.gui.region;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import sweforce.gui.activity.Activity;
import sweforce.gui.activity.ActivityMapper;
import sweforce.gui.place.Place;


/**

 */
public abstract class RegionPlacesModule extends AbstractModule {

    protected abstract void configure(PlacesRegionsActivitiesMappings wizard);

    @Override
    protected void configure() {
//        configure(new PlacesRegionsActivitiesMappings(this.binder()));
    }


}
