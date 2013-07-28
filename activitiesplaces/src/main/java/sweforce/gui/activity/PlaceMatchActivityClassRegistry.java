package sweforce.gui.activity;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 7/25/13
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceMatchActivityClassRegistry {

    private final Map<PlaceMatch, Class<? extends Activity>> placeMatchClassMap;

    public PlaceMatchActivityClassRegistry(Map<PlaceMatch, Class<? extends Activity>> placeMatchClassMap) {
        this.placeMatchClassMap = placeMatchClassMap;
    }


}
