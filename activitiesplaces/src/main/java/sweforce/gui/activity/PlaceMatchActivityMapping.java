package sweforce.gui.activity;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/25/13
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceMatchActivityMapping  {

    private final PlaceMatch placeMatch;

    private final Class<? extends Activity> activityClass;

    public PlaceMatchActivityMapping(PlaceMatch placeMatch, Class<? extends Activity> activityClass) {
        this.placeMatch = placeMatch;
        this.activityClass = activityClass;
    }

    public PlaceMatch getPlaceMatch() {
        return placeMatch;
    }

    public Class<? extends Activity> getActivityClass() {
        return activityClass;
    }
}
