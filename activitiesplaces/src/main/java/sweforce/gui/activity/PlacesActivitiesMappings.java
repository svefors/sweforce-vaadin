package sweforce.gui.activity;

import com.google.inject.Binder;
import com.google.inject.MembersInjector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import sweforce.gui.place.Place;
import sweforce.gui.place.PlaceHistoryMapper;
import sweforce.gui.place.PlaceTokenizer;
import sweforce.gui.place.PlaceTokenizerUtil;

/**
 * usage:
 * .at(LoginPlace.class).run(LoginActivity.class)
 * or
 * .at(LoginPlace.class).run(new LoginActivity())
 *
 * will resolve the place prefix -> PlaceTokenizer and bind the activity
 */
public class PlacesActivitiesMappings {

//        private final Binder binder;

    final MapBinder<Class<? extends Place>, Activity> classActivityMapBinder;

    final MapBinder<String, PlaceTokenizer<? extends Place>> stringPlaceTokenizerMapBinder;

    public PlacesActivitiesMappings(Binder binder) {
//            this.binder = binder;
        this.classActivityMapBinder = MapBinder.newMapBinder(binder,
                new TypeLiteral<Class<? extends Place>>() {
                },
                new TypeLiteral<Activity>() {
                }
        );
//        MembersInjector<PlaceHistoryMapper> placeHistoryMapperInjector =  binder.getMembersInjector(PlaceHistoryMapper.class);

        this.stringPlaceTokenizerMapBinder = MapBinder.newMapBinder(binder,
                new TypeLiteral<String>() {
                },
                new TypeLiteral<PlaceTokenizer<? extends Place>>() {
                }
        );
    }

    /**
     * will create a binding for:
     * place prefix -> placeTokenizer, and return a configuration object for adding place configuration
     *
     * @param place
     * @return
     */
    public KnownPlace at(final Class<? extends Place> place) {
        PlaceTokenizerUtil.bind(place, stringPlaceTokenizerMapBinder);

        return new KnownPlace() {
            @Override
            public PlacesActivitiesMappings run(Activity activity) {
                classActivityMapBinder.addBinding(place).toInstance(activity);
                return PlacesActivitiesMappings.this;
            }

            @Override
            public PlacesActivitiesMappings run(Class<? extends Activity> activity) {
                classActivityMapBinder.addBinding(place).to(activity);
                return PlacesActivitiesMappings.this;
            }

            @Override
            public <A extends Provider> PlacesActivitiesMappings useProvider(Class<? extends Provider<? extends Activity>> provider) {
                classActivityMapBinder.addBinding(place).toProvider(provider);
                return PlacesActivitiesMappings.this;
            }
        };
    }

    public interface KnownPlace {

        public PlacesActivitiesMappings run(Activity activity);

        public PlacesActivitiesMappings run(Class<? extends Activity> activity);

        public <A extends Provider> PlacesActivitiesMappings useProvider(Class<? extends Provider<? extends Activity>> provider);

    }

}
