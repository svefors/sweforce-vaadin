package sweforce.gui.display.region;

import sweforce.gui.place.Place;

/**
 * Created with IntelliJ IDEA.
 * User: sveffa
 * Date: 2/26/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaceRegion {

    public final Class<? extends Place> placeClass;

    public final Region region;

    /**
     *
     * @param placeClass must not be null
     * @param region must not be null
     */
    public PlaceRegion(Class<? extends Place> placeClass, Region region) {
        this.placeClass = placeClass;
        this.region = region;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceRegion that = (PlaceRegion) o;

        if (!placeClass.equals(that.placeClass)) return false;
        if (!region.equals(that.region)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = placeClass.hashCode();
        result = 31 * result + region.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PlaceRegion");
        sb.append("{place=").append(placeClass);
        sb.append(", region=").append(region);
        sb.append('}');
        return sb.toString();
    }
}
