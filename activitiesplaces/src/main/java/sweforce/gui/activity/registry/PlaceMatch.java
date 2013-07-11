package sweforce.gui.activity.registry;

import sweforce.gui.place.Place;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/10/13
 * Time: 7:19 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlaceMatch {

    public abstract boolean matches(Place place);



    /**
     * if they are equal. equal only applies to not null objects.
     *
     * @param place
     * @return
     */
    public static PlaceMatch eq(final Place place) {
        return new PlaceMatch() {
            @Override
            public boolean matches(Place place1) {
                if (place == null || place == null)
                    return false;
                return place.equals(place1);
            }
        };

    }

    /**
     * if they are the same. can only be true for not null objects
     *
     * @param place
     * @return
     */
    public static PlaceMatch same(final Place place) {
        return new PlaceMatch() {
            @Override
            public boolean matches(Place place1) {
                if (place == null || place == null)
                    return false;
                return place == place1;
            }
        };
    }

    public static PlaceMatch nullz() {
        return new PlaceMatch() {
            @Override
            public boolean matches(Place place) {
                return place == null;
            }
        };
    }

    public static PlaceMatch Clazz(final Class<? extends Place> placeClass) {
        return new PlaceMatch() {
            @Override
            public boolean matches(Place place) {
                return placeClass.equals(place.getClass());
            }
        };
    }
}
